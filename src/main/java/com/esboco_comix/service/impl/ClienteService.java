package com.esboco_comix.service.impl;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.esboco_comix.dao.impl.cliente.ClienteDAO;
import com.esboco_comix.mapper.ClienteDTOMapper;
import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.value_objects.Senha;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EnderecoService enderecoService = new EnderecoService();
    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    private final ClienteDTOMapper clienteMapper = new ClienteDTOMapper();

    public CadastrarClienteDTO inserir(CadastrarClienteDTO pedido) {
        Senha senhaNova = new Senha(pedido.getSenhaNova());
        Senha senhaConfirmacao = new Senha(pedido.getSenhaConfirmacao());

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new IllegalArgumentException("Senha e senha de confirmação devem ser iguais!");
        }

        pedido.getCliente().validar();

        for (Endereco e : pedido.getEnderecos()) {
            e.validar();
        }

        for (CartaoCredito c : pedido.getCartoesCredito()) {
            c.validar();
        }

        Cliente clienteToAdd = pedido.getCliente();
        String saltSenha = CriptografadorSenha.generateSalt();
        clienteToAdd.setHashSenha(CriptografadorSenha.hashSenha(new Senha(pedido.getSenhaNova()), saltSenha));
        clienteToAdd.setSaltSenha(saltSenha);
        clienteToAdd.setRanking(0);
        
        Cliente clienteInserido = clienteDAO.inserir(pedido.getCliente());

        List<Endereco> enderecosInseridos = new ArrayList<>();
        for (Endereco e : pedido.getEnderecos()) {
            e.setIdCliente(clienteInserido.getId());
            enderecosInseridos.add(enderecoService.inserir(e));
        }

        List<CartaoCredito> cartoesCredito = new ArrayList<>();
        for (CartaoCredito c: pedido.getCartoesCredito()){
            c.setIdCliente(clienteInserido.getId());
            cartoesCredito.add(cartaoCreditoService.inserir(c));
        }

        return CadastrarClienteDTO.builder()
            .cliente(clienteInserido)
            .enderecos(enderecosInseridos)
            .cartoesCredito(cartoesCredito)
        .build();
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

    public Cliente atualizar(Cliente c) {
        c.validar();
        return clienteDAO.atualizar(c);
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
