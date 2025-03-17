package com.esboco_comix.service;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;
import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.dao.impl.ClienteDAO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.service.validador.CartaoCreditoValidador;
import com.esboco_comix.service.validador.ClienteValidador;
import com.esboco_comix.service.validador.EnderecoValidador;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EnderecoService enderecoService = new EnderecoService();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    private ClienteValidador clienteValidador = new ClienteValidador();
    private EnderecoValidador enderecoValidador = new EnderecoValidador();
    private CartaoCreditoValidador cartaoCreditoValidador = new CartaoCreditoValidador();

    public PedidoCadastrarCliente inserir(PedidoCadastrarCliente pedido) throws Exception {
        clienteValidador.validarCadastro(pedido);
        for (Endereco e : pedido.getEnderecos()) {
            enderecoValidador.validar(e);
        }

        for (CartaoCredito c : pedido.getCartoesCredito()) {
            cartaoCreditoValidador.validar(c);
        }

        Cliente clienteToAdd = pedido.getCliente();
        inserirNovoHash(clienteToAdd, pedido.getSenhaNova());
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

        PedidoCadastrarCliente pedidoInserido = new PedidoCadastrarCliente();
        pedidoInserido.setCliente(clienteInserido);
        pedidoInserido.setEnderecos(enderecosInseridos);
        pedidoInserido.setCartoesCredito(cartoesCredito);

        return pedidoInserido;
    }

    public List<Cliente> consultarTodos() throws Exception {
        return clienteDAO.consultarTodos();
    }

    public List<Cliente> consultarTodos(Cliente filtro) throws Exception {
        return clienteDAO.consultarTodos(filtro);
    }

    public Cliente consultarByID(int id) throws Exception {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(Cliente c) throws Exception {
        return clienteDAO.atualizar(c);
    }

    public Cliente atualizarSenha(PedidoAlterarSenha pedido) throws Exception {
        Cliente c = pedido.getCliente();
        Cliente clienteInserido = clienteDAO.consultarHashSaltPorID(c.getId());

        clienteValidador.validarSenhas(pedido.getSenhaNova(), pedido.getSenhaConfirmacao());

        String hashGuardado = clienteInserido.getHashSenha();
        String saltGuardado = clienteInserido.getSaltSenha();
        clienteValidador.validarSenhaAntiga(pedido.getSenhaNova(), hashGuardado, saltGuardado);

        inserirNovoHash(c, pedido.getSenhaNova());
        return clienteDAO.atualizarSenha(c);
    }

    public Cliente atualizarStatusCadastro(Cliente c) throws Exception {
        return clienteDAO.atualizarStatusCadastro(c);
    }

    public void deletar(Cliente c) throws Exception {
        clienteDAO.deletar(c);
        for (Endereco e : c.getEnderecos()) {
            enderecoService.deletar(e);
        }
    }

    private void inserirNovoHash(Cliente c, String senhaNova) throws Exception {
        String saltSenha = CriptografadorSenha.generateSalt();
        c.setHashSenha(CriptografadorSenha.hashSenha(senhaNova, saltSenha));
        c.setSaltSenha(saltSenha);
    }

}
