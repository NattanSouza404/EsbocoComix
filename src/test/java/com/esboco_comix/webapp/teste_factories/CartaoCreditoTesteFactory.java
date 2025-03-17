package com.esboco_comix.webapp.teste_factories;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.enuns.BandeiraCartao;

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
}
