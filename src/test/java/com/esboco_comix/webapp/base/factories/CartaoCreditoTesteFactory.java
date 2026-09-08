package com.esboco_comix.webapp.base.factories;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;

public class CartaoCreditoTesteFactory {
    public static CartaoCredito criar(){
        CartaoCredito c = new CartaoCredito();
        
        c.setNumero("1111222233334444");
        c.setCodigoSeguranca("111");
        c.setNomeImpresso("JORGE DOS SANTOS");
        c.setBandeiraCartao(BandeiraCartao.AMERICAN_EXPRESS);
        c.setPreferencial(true);

        return c;
    }

    public static List<CartaoCredito> criarListaCartoes() {
        CartaoCredito c = new CartaoCredito();

        c.setNumero("1111222233334444");
        c.setCodigoSeguranca("111");
        c.setNomeImpresso("JORGE DOS SANTOS");
        c.setBandeiraCartao(BandeiraCartao.AMERICAN_EXPRESS);
        c.setPreferencial(true);

        List<CartaoCredito> cartoes = new ArrayList<>();
        cartoes.add(c);

        return cartoes;
    }
}
