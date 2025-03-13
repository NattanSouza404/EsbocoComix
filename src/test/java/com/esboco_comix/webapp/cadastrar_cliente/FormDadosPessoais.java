package com.esboco_comix.webapp.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormDadosPessoais {

    private WebElement form;

    public FormDadosPessoais(WebDriver webDriver){
        this.form = webDriver.findElement(By.id("form-cadastrar-dados-pessoais"));
    }

    public void preencherInput(String nome, String valorInput){
        form.findElement(By.name(nome)).sendKeys(valorInput);
    }

}
