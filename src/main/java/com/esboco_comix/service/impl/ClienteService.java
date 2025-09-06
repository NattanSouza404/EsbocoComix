package com.esboco_comix.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.esboco_comix.dao.impl.cliente.ClienteDAO;
import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.dto.FiltrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.service.validador.impl.CartaoCreditoValidador;
import com.esboco_comix.service.validador.impl.ClienteValidador;
import com.esboco_comix.service.validador.impl.EnderecoValidador;
import com.esboco_comix.service.validador.impl.SenhaValidador;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteService {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EnderecoService enderecoService = new EnderecoService();
    private final CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
    private final SenhaValidador senhaValidador = new SenhaValidador();

    private final ClienteValidador clienteValidador = new ClienteValidador();
    private final EnderecoValidador enderecoValidador = new EnderecoValidador();
    private final CartaoCreditoValidador cartaoCreditoValidador = new CartaoCreditoValidador();

    public CadastrarClienteDTO inserir(CadastrarClienteDTO pedido) {
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

        CadastrarClienteDTO pedidoInserido = new CadastrarClienteDTO();
        pedidoInserido.setCliente(clienteInserido);
        pedidoInserido.setEnderecos(enderecosInseridos);
        pedidoInserido.setCartoesCredito(cartoesCredito);

        return pedidoInserido;
    }

    public List<Cliente> consultarTodos() {
        return clienteDAO.consultarTodos();
    }

    public List<Cliente> consultarTodos(HttpServletRequest req) {
        return clienteDAO.consultarTodos(mapearToFiltrarClienteDTO(req));
    }

    public Cliente consultarByID(int id) {
        return clienteDAO.consultarByID(id);
    }

    public Cliente atualizar(Cliente c) {
        return clienteDAO.atualizar(c);
    }

    public Cliente atualizarSenha(AlterarSenhaDTO pedido) {
        Cliente c = pedido.getCliente();
        Cliente clienteInserido = clienteDAO.consultarHashSaltPorID(c.getId());

        senhaValidador.validar(pedido);

        String hashGuardado = clienteInserido.getHashSenha();
        String saltGuardado = clienteInserido.getSaltSenha();

        senhaValidador.validarSenhaAntiga(pedido.getSenhaNova(), hashGuardado, saltGuardado);

        inserirNovoHash(c, pedido.getSenhaNova());
        return clienteDAO.atualizarSenha(c);
    }

    public Cliente atualizarStatusCadastro(Cliente c) {
        return clienteDAO.atualizarStatusCadastro(c);
    }

    private void inserirNovoHash(Cliente c, String senhaNova) {
        String saltSenha = CriptografadorSenha.generateSalt();
        c.setHashSenha(CriptografadorSenha.hashSenha(senhaNova, saltSenha));
        c.setSaltSenha(saltSenha);
    }

    private FiltrarClienteDTO mapearToFiltrarClienteDTO(HttpServletRequest req) {
        FiltrarClienteDTO filtro = new FiltrarClienteDTO();
        
        String nome = req.getParameter("nome");
        if (!nome.isBlank()){
            filtro.setNome(nome);
        }

        String cpf = req.getParameter("cpf");
        if (!cpf.isBlank()){
            filtro.setCpf(cpf);
        }

        String dataNascimento = req.getParameter("dataNascimento");
        if (!dataNascimento.isBlank()){
            filtro.setDataNascimento(LocalDate.parse(dataNascimento));
        }

        String genero = req.getParameter("genero");
        if (!genero.isBlank()){
            filtro.setGenero(Genero.valueOf(genero));
        }

        String email = req.getParameter("email");
        if (!email.isBlank()){
            filtro.setEmail(email);
        }

        String ranking = req.getParameter("ranking");
        if (!ranking.isBlank()){
            filtro.setRanking(Integer.parseInt(ranking));
        }

        String isAtivo = req.getParameter("isAtivo");
        if (!isAtivo.isBlank()){
            filtro.setIsAtivo(Boolean.valueOf(isAtivo));
        }

        return filtro;
    }

    public Cliente consultarByIDPedido(int idPedido) {
        return clienteDAO.consultarByIDPedido(idPedido);
    }

}
