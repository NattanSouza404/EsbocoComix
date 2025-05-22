package com.esboco_comix.webapp.paginas.cliente.conta;

import lombok.Getter;

@Getter
public enum SecoesConta {
    DADOS_PESSOAIS("trocar-para-dados-pessoais"),
    ENDERECO("trocar-para-endereco"),
    CARTAO_CREDITO("trocar-para-cartao"),
    CUPOM("trocar-para-cupom");

    private final String idBotao;

    SecoesConta(String idBotao){
        this.idBotao = idBotao;
    }
}
