package com.esboco_comix.webapp.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.esboco_comix.model.entidades.Cliente;

public class PaginaCadastrar {

    private static String URL = "http://localhost:8080/cadastrar.html";  

    public void preencherCliente(Cliente c) throws InterruptedException{
        WebDriver driver = new EdgeDriver();
        driver.get(URL);

        FormDadosPessoais form = new FormDadosPessoais(driver);
        form.preencherInput("nome", c.getNome());
        form.preencherInput("cpf", c.getCpf());

        driver.close();
    }

    public WebElement getFormularioDadosPessoais(WebDriver driver){
        return driver.findElement(By.id("form-cadastrar-dados-pessoais"));
    }
}
