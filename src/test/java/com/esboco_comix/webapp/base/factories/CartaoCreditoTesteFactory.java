package com.esboco_comix.webapp.base.factories;

import java.util.Arrays;
import java.util.List;

import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.esboco_comix.cliente.dto.CadastrarCartaoCreditoDTO;

public class CartaoCreditoTesteFactory {
    public static CadastrarCartaoCreditoDTO criar(){
        return CadastrarCartaoCreditoDTO.builder()
            .numero("1111222233334444")
            .codigoSeguranca("111")
            .nomeImpresso("JORGE DOS SANTOS")
            .bandeiraCartao(BandeiraCartao.AMERICAN_EXPRESS)
            .isPreferencial(true)
        .build();
    }

    public static List<CadastrarCartaoCreditoDTO> criarListaCartoes() {
        return Arrays.asList(criar());
    }
}
