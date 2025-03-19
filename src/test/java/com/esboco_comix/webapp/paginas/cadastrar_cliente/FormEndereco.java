package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.WebElement;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.utils.FormElement;

public class FormEndereco {
    private FormElement form;

    public FormEndereco(WebElement form){
        this.form = new FormElement(form);
    }

    public void preencher(Endereco e) throws InterruptedException {
        form.preencherInput("fraseCurta", e.getFraseCurta());

        form.selecionarInputTrueOrFalse("isResidencial", e.getIsResidencial());
        form.selecionarInputTrueOrFalse("isEntrega", e.getIsEntrega());
        form.selecionarInputTrueOrFalse("isCobranca", e.getIsCobranca());

        form.preencherInput("bairro", e.getBairro());
        form.preencherInput("cidade", e.getCidade());
        form.preencherInput("estado", e.getEstado());
        form.preencherInput("pais", e.getPais());
        form.preencherInput("cep", e.getCep());
        form.preencherInput("observacoes", e.getObservacoes());
        form.preencherInput("logradouro", e.getLogradouro());
        form.preencherInput("tipoLogradouro", e.getTipoLogradouro());
        form.preencherInput("tipoResidencial", e.getTipoResidencial());
        form.preencherInput("numero", e.getNumero());
    }

}
