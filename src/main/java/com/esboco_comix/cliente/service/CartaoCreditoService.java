package com.esboco_comix.cliente.service;

import java.util.List;

import com.esboco_comix.cliente.dao.CartaoCreditoDAO;
import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dto.AtualizarCartaoCreditoDTO;
import com.esboco_comix.cliente.dto.CadastrarCartaoCreditoDTO;

public class CartaoCreditoService {
    private CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    public CartaoCredito inserir(CadastrarCartaoCreditoDTO dto) {
        var cartao = CartaoCredito.builder()
            .numero(dto.numero())
            .nomeImpresso(dto.nomeImpresso())
            .codigoSeguranca(dto.codigoSeguranca())
            .isPreferencial(dto.isPreferencial())
            .idCliente(dto.idCliente())
        .build();

        return cartaoCreditoDAO.inserir(cartao);
    }

    public List<CartaoCredito> consultarByIDCliente(int id) {
        return cartaoCreditoDAO.consultarByIDCliente(id);
    }

    public CartaoCredito consultarByID(int id) {
        return cartaoCreditoDAO.consultarByID(id);
    }

    public CartaoCredito atualizar(AtualizarCartaoCreditoDTO dto){
        var cartaoCredito = cartaoCreditoDAO.consultarByID(dto.id());

        cartaoCredito.atualizar(
            dto.numero(),
            dto.nomeImpresso(),
            dto.bandeiraCartao(),
            dto.codigoSeguranca(),
            dto.isPreferencial()
        );

        return cartaoCreditoDAO.atualizar(cartaoCredito);
    }

    public void deletar(CartaoCredito c) {
        cartaoCreditoDAO.inativar(c);
    }

}
