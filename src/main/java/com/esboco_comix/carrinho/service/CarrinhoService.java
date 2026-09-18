package com.esboco_comix.carrinho.service;

import com.esboco_comix.carrinho.dominio.Carrinho;
import com.esboco_comix.carrinho.dto.ItemCarrinhoDTO;
import com.esboco_comix.carrinho.mapper.CarrinhoMapper;

public class CarrinhoService {

    private final CarrinhoMapper mapper = new CarrinhoMapper();

    public Carrinho adicionar(Carrinho carrinho, ItemCarrinhoDTO dto) {
        // TODO: adicionar verificação do estoque
        carrinho.adicionar(mapper.toItemCarrinho(dto));
        return carrinho;
    }

    public Carrinho atualizarQuantidade(Carrinho carrinho, ItemCarrinhoDTO dto) {
        carrinho.atualizarQuantidade(mapper.toItemCarrinho(dto));

        return carrinho;
    }

    public void deletar(Carrinho carrinho, ItemCarrinhoDTO dto) {
        carrinho.deletar(mapper.toItemCarrinho(dto));
    }

}
