package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.esboco_comix.model.entidades.Endereco;

public class FormEndereco {
    private WebElement form;

    public FormEndereco(WebElement form){
        this.form = form;
    }

    public void preencherInput(String nome, String valorInput){
        form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    private void selecionarInputTrueOrFalse(String nome, boolean valor) {
        WebElement webElement = form.findElement(By.name(nome));
        Select select = new Select(webElement);

        select.selectByValue(String.valueOf(valor));
    }

    public void preencher(Endereco e) {
        preencherInput("fraseCurta", e.getFraseCurta());

        selecionarInputTrueOrFalse("isResidencial", e.getIsResidencial());
        selecionarInputTrueOrFalse("isEntrega", e.getIsEntrega());
        selecionarInputTrueOrFalse("isCobranca", e.getIsCobranca());

        preencherInput("bairro", e.getBairro());
        preencherInput("cidade", e.getCidade());
        preencherInput("estado", e.getEstado());
        preencherInput("pais", e.getPais());
        preencherInput("cep", e.getCep());
        preencherInput("observacoes", e.getObservacoes());
        preencherInput("logradouro", e.getLogradouro());
        preencherInput("tipoLogradouro", e.getTipoLogradouro());
        preencherInput("tipoResidencial", e.getTipoResidencial());
        preencherInput("numero", e.getNumero());
    }

}
