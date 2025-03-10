package com.esboco_comix.service;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;
import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.dao.impl.ClienteDAO;
import com.esboco_comix.model.entidades.BandeiraCartao;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private EnderecoService enderecoService = new EnderecoService();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();

    private ClienteValidador clienteValidador = new ClienteValidador();

    public PedidoCadastrarCliente inserir(PedidoCadastrarCliente pedido) throws Exception {
        Cliente clienteToAdd = pedido.getCliente();
        clienteValidador.validar(clienteToAdd, pedido);
        inserirNovoHash(clienteToAdd, pedido.getSenhaNova());

        Cliente clienteInserido = clienteDAO.inserir(pedido.getCliente());

        List<Endereco> enderecosInseridos = new ArrayList<>();
        for (Endereco e : pedido.getEnderecos()) {
            e.setIdCliente(clienteInserido.getId());
            enderecosInseridos.add(enderecoService.inserir(e));
        }

        List<CartaoCredito> cartoesCredito = new ArrayList<>();
        for (CartaoCredito c: pedido.getCartoesCredito()){
            c.setIdCliente(clienteInserido.getId());
            BandeiraCartao bc = cartaoCreditoService.consultarBandeiraCartao(c.getBandeiraCartao()); 
            c.setBandeiraCartao(bc);
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

    public Cliente consultarByID(int id) throws Exception {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(Cliente c) throws Exception {
        return clienteDAO.atualizar(c);
    }

    public Cliente atualizarSenha(PedidoAlterarSenha pedido) throws Exception {
        Cliente c = pedido.getCliente();

        Cliente clienteInserido = clienteDAO.consultarHashSaltPorID(c.getId()); 
        String hashGuardado = clienteInserido.getHashSenha();
        String saltGuardado = clienteInserido.getSaltSenha();

        validarSenha(pedido.getSenhaNova(), hashGuardado, saltGuardado);
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

    private void validarSenha(String senha, String hash, String salt) throws Exception {
        String hashNovo = CriptografadorSenha.hashSenha(senha, salt); 
        if (!hashNovo.equals(hash)){
            throw new Exception("Senha antiga não consta com senha inserida pelo usuário!");
        }
    }

    private void inserirNovoHash(Cliente c, String senhaNova) throws Exception {
        String saltSenha = CriptografadorSenha.generateSalt();
        c.setHashSenha(CriptografadorSenha.hashSenha(senhaNova, saltSenha));
        c.setSaltSenha(saltSenha);
    }

}
