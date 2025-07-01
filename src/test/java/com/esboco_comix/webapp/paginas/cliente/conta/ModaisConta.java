package com.esboco_comix.webapp.paginas.cliente.conta;

import lombok.Getter;

@Getter
public enum ModaisConta {
    ALTERAR_DADOS_PESSOAIS("#btn-editar-cadastro", "#alterar-dados-pessoais"),
    ALTERAR_SENHA("#btn-alterar-senha", "#alterar-senha"),

    ADICIONAR_CARTAO_CREDITO("#btn-adicionar-cartao","#adicionar-cartao-credito"),
    ADICIONAR_ENDERECO("#btn-adicionar-endereco", "#adicionar-endereco"),

    ALTERAR_ENDERECO(".endereco-conta .btn-atualizar", "#alterar-endereco"),
    ALTERAR_CARTAO_CREDITO(".cartao-credito-conta .btn-atualizar", "#alterar-cartao-credito");

    private final String seletorBotao;
    private final String seletorForm;

    ModaisConta(String seletorBotao, String seletorForm){
        this.seletorBotao = seletorBotao;
        this.seletorForm = seletorForm;
    }
}
