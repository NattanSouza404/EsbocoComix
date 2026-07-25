package com.esboco_comix.cliente.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.esboco_comix.cliente.dao.CartaoCreditoDAO;
import com.esboco_comix.cliente.dao.ClienteDAO;
import com.esboco_comix.cliente.dao.EnderecoDAO;
import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.value_objects.Senha;
import com.esboco_comix.cliente.dto.AlterarSenhaDTO;
import com.esboco_comix.cliente.dto.AtualizarClienteDTO;
import com.esboco_comix.cliente.dto.CadastrarClienteDTO;
import com.esboco_comix.cliente.mapper.ClienteDTOMapper;
import com.esboco_comix.core.dao.transaction.TransactionExecutor;
import com.esboco_comix.core.utils.CriptografadorSenha;

public class ClienteService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    private final CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    private final TransactionExecutor transactionManager = new TransactionExecutor();

    private final ClienteDTOMapper clienteMapper = new ClienteDTOMapper();

    public CadastrarClienteDTO inserir(CadastrarClienteDTO pedido) {
        Senha senhaNova = new Senha(pedido.getSenhaNova());
        Senha senhaConfirmacao = new Senha(pedido.getSenhaConfirmacao());

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new IllegalArgumentException("Senha e senha de confirmação devem ser iguais!");
        }

        String saltSenha = CriptografadorSenha.generateSalt();

        Cliente clienteToAdd = pedido.getCliente();
        clienteToAdd.setHashSenha(
            CriptografadorSenha.hashSenha(new Senha(pedido.getSenhaNova()), saltSenha)
        );
        clienteToAdd.setSaltSenha(saltSenha);
        clienteToAdd.setRanking(0);
        clienteToAdd.validar();

        return transactionManager.execute(conn -> {
            Cliente clienteInserido = clienteDAO.inserir(conn, clienteToAdd);

            List<Endereco> enderecosInseridos = new ArrayList<>();
            for (Endereco e : pedido.getEnderecos()) {
                e.validar();
                e.setIdCliente(clienteInserido.getId());
                enderecosInseridos.add(enderecoDAO.inserir(conn, e));
            }

            List<CartaoCredito> cartoesCredito = new ArrayList<>();
            for (CartaoCredito c: pedido.getCartoesCredito()){
                c.validar();
                c.setIdCliente(clienteInserido.getId());
                cartoesCredito.add(cartaoCreditoDAO.inserir(conn, c));
            }

            return CadastrarClienteDTO.builder()
                .cliente(clienteInserido)
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
        Cliente clienteToUpdate = clienteMapper.mapearToCliente(c);
        clienteToUpdate.validar();
        return clienteDAO.atualizar(clienteToUpdate);
    }

    public Cliente atualizarSenha(AlterarSenhaDTO dto) {
        Cliente cliente = clienteDAO.consultarHashSaltPorID(dto.getIdCliente());

        Senha senhaNova = new Senha(dto.getSenhaNova());
        Senha senhaConfirmacao = new Senha(dto.getSenhaConfirmacao());

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new IllegalArgumentException(
                "Senha e senha de confirmação devem ser iguais!"
            );
        }

        String hashGuardado = cliente.getHashSenha();
        String saltGuardado = cliente.getSaltSenha();
        String hashNovo = CriptografadorSenha.hashSenha(senhaNova, saltGuardado);

        if (!hashNovo.equals(hashGuardado)){
            throw new IllegalArgumentException(
                "Senha antiga não consta com senha inserida pelo usuário!"
            );
        }

        String saltSenha = CriptografadorSenha.generateSalt();
        cliente.setHashSenha(CriptografadorSenha.hashSenha(senhaNova, saltSenha));
        cliente.setSaltSenha(saltSenha);

        return clienteDAO.atualizarSenha(cliente);
    }

    public Cliente atualizarStatusCadastro(Cliente c) {
        return clienteDAO.atualizarStatusCadastro(c);
    }

    public Cliente consultarByIDPedido(int idPedido) {
        return clienteDAO.consultarByIDPedido(idPedido);
    }

}
