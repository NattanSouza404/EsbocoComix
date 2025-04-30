package com.esboco_comix.webapp.crud_cliente.clientes;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.crud_cliente.cadastrar_cliente.FormEndereco;

public class SecaoEnderecos {
    private List<FormEndereco> forms;
    private WebElement botaoAdicionarEndereco;

    public SecaoEnderecos(WebDriver webDriver){
        List<WebElement> formsEndereco = webDriver.findElements(By.className("endereco"));

        this.forms = new ArrayList<>();
        
        for (WebElement element : formsEndereco) {
            forms.add(new FormEndereco(element));
        }

        this.botaoAdicionarEndereco = webDriver.findElement(By.cssSelector("#footer-secao-endereco button"));
    }

    public void adicionarNovoEndereco(){
        this.botaoAdicionarEndereco.click();
    }

    public void preencherEnderecos(List<Endereco> enderecos) throws InterruptedException {
        for (int i = 0; i < enderecos.size(); i++){
            forms.get(i).preencher(enderecos.get(i));
        }
    }
}
