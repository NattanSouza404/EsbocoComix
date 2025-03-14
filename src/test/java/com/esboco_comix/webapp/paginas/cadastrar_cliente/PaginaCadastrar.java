package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.WebDriver;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.webdriver.DriverTeste;

public class PaginaCadastrar {

    private static String URL = "http://localhost:8080/cadastrar.html"; 

    private WebDriver driver;

    private FormDadosPessoais formDadosPessoais;
    
    public PaginaCadastrar(){
        this.driver = new DriverTeste();
        driver.get(URL);
        this.formDadosPessoais = new FormDadosPessoais(driver);
    }

    public void fechar(){
        driver.close();
    }

    public void preencherCliente(Cliente c) throws InterruptedException {
        formDadosPessoais.preencherCliente(c);
    }
}
