package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.model.entidades.Cliente;

public class FormDadosPessoais {

    private WebElement form;

    public FormDadosPessoais(WebDriver webDriver){
        this.form = webDriver.findElement(By.id("cadastrar-dados-pessoais"));
    }

    public void preencherInput(String nome, String valorInput){
        form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void preencherCliente(Cliente c) {
        preencherInput("nome", c.getNome());
        preencherInput("cpf", c.getCpf());
        preencherInput("email", c.getEmail());
        preencherInput("tipoTelefone", c.getTelefone().getTipo());
        preencherInput("ddd", c.getTelefone().getDdd());
        preencherInput("numero", c.getTelefone().getNumero());
        preencherInput("dataNascimento", String.valueOf(c.getDataNascimento()));
    }

}
