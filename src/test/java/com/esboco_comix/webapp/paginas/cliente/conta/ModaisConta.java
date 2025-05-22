package com.esboco_comix.webapp.paginas.cliente.conta;

import lombok.Getter;

@Getter
public enum ModaisConta {
    ALTERAR_DADOS_PESSOAIS("#btn-editar-cadastro", "#alterar-dados-pessoais"),
    ALTERAR_SENHA("#btn-alterar-senha", "#alterar-senha"),

    ALTERAR_ENDERECO(".endereco-conta .btn-atualizar", "#alterar-endereco"),

    ADICIONAR_CARTAO_CREDITO("#btn-adicionar-cartao","#adicionar-cartao-credito");

    private final String seletorBotao;
    private final String seletorForm;

    ModaisConta(String seletorBotao, String seletorForm){
        this.seletorBotao = seletorBotao;
        this.seletorForm = seletorForm;
    }
}
