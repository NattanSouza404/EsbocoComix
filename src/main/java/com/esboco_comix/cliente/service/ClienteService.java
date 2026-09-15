package com.esboco_comix.cliente.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.esboco_comix.cliente.dao.CartaoCreditoDAO;
import com.esboco_comix.cliente.dao.ClienteDAO;
import com.esboco_comix.cliente.dao.EnderecoDAO;
import com.esboco_comix.cliente.dominio.CriptografadorSenha;
import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Senha;
import com.esboco_comix.cliente.dto.AlterarSenhaDTO;
import com.esboco_comix.cliente.dto.AtualizarClienteDTO;
import com.esboco_comix.cliente.dto.AtualizarStatusCadastroDTO;
import com.esboco_comix.cliente.dto.CadastrarClienteDTO;
import com.esboco_comix.cliente.mapper.ClienteDTOMapper;
import com.esboco_comix.core.dao.TransactionExecutor;
import com.esboco_comix.core.security.CriptografadorSenhaPBKDF2;

public class ClienteService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    private final CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    private final TransactionExecutor transactionManager = new TransactionExecutor();

    private final ClienteDTOMapper clienteMapper = new ClienteDTOMapper();

    private final CriptografadorSenha criptografador = new CriptografadorSenhaPBKDF2();

    public CadastrarClienteDTO inserir(CadastrarClienteDTO dto) {
        Senha senhaNova = new Senha(dto.senhaNova());
        Senha senhaConfirmacao = new Senha(dto.senhaConfirmacao());

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new IllegalArgumentException("Senha e senha de confirmação devem ser iguais!");
        }

        Cliente clienteToAdd = Cliente.builder()
            .nome(dto.nome())
            .genero(dto.genero())
            .dataNascimento(dto.dataNascimento())
            .cpf(new Cpf(dto.cpf()))
            .email(new Email(dto.email()))
            .telefone(dto.telefone())
        .build();

        clienteToAdd.definirSenha(senhaNova, criptografador);

        return transactionManager.execute(conn -> {
            Cliente clienteInserido = clienteDAO.inserir(conn, clienteToAdd);

            List<Endereco> enderecosInseridos = new ArrayList<>();
            for (Endereco e : dto.enderecos()) {
                e.validar();
                e.setIdCliente(clienteInserido.getId());
                enderecosInseridos.add(enderecoDAO.inserir(conn, e));
            }

            List<CartaoCredito> cartoesCredito = new ArrayList<>();
            for (CartaoCredito c: dto.cartoesCredito()){
                c.validar();
                c.setIdCliente(clienteInserido.getId());
                cartoesCredito.add(cartaoCreditoDAO.inserir(conn, c));
            }

            return CadastrarClienteDTO.builder()
                .nome(clienteInserido.getNome())
                .genero(clienteInserido.getGenero())
                .dataNascimento(clienteInserido.getDataNascimento())
                .cpf(clienteInserido.getCpf().valor())
                .email(clienteInserido.getEmail().valor())
                .enderecos(enderecosInseridos)
                .cartoesCredito(cartoesCredito)
            .build();
        });
    }

    public List<Cliente> consultarTodos() {
        return clienteDAO.consultarTodos();
    }

    public List<Cliente> consultarTodos(HttpServletRequest req) {
        return clienteDAO.consultarTodos(clienteMapper.mapearToFiltrarClienteDTO(req));
    }

    public Cliente consultarByID(int id) {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(AtualizarClienteDTO c) {
        return clienteDAO.atualizar(
            clienteMapper.mapearToCliente(c)
        );
    }

    public Cliente atualizarSenha(AlterarSenhaDTO dto) {
        Cliente cliente = clienteDAO.consultarByIdComHashSalt(dto.getIdCliente());

        cliente.alterarSenha(
            new Senha(dto.getSenhaAntiga()),
            new Senha(dto.getSenhaNova()),
            new Senha(dto.getSenhaConfirmacao()),
            criptografador
        );

        return clienteDAO.atualizarSenha(cliente);
    }

    public Cliente atualizarStatusCadastro(AtualizarStatusCadastroDTO dto) {
        return clienteDAO.atualizarStatusCadastro(
            dto.id(), dto.isAtivo()
        );
    }

    public Cliente consultarByIDPedido(int idPedido) {
        return clienteDAO.consultarByIDPedido(idPedido);
    }

}
