package com.esboco_comix.webapp.paginas.cliente;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.base.web_element.FormElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormEndereco {
    private FormElement form;

    public FormEndereco(WebDriver webDriver, WebElement form){
        this.form = new FormElement(webDriver, form);
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
        form.preencherInput("tipoLogradouro", e.getTipoLogradouro().name());
        form.preencherInput("tipoResidencial", e.getTipoResidencial().name());
        form.preencherInput("numero", e.getNumero());
    }

}
