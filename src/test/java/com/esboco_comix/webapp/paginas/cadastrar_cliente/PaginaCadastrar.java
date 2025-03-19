package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.webdriver.DriverTeste;

public class PaginaCadastrar {

    private static String URL = "http://localhost:8080/cadastrar.html"; 

    private WebDriver driver;

    private FormDadosPessoais formDadosPessoais;
    private SecaoEnderecos secaoEnderecos;

    private WebElement botaoEnviarCadastro;

    public PaginaCadastrar(){
        this.driver = new DriverTeste();
        driver.get(URL);
        this.formDadosPessoais = new FormDadosPessoais(driver);
        this.secaoEnderecos = new SecaoEnderecos(driver);
        this.botaoEnviarCadastro = driver.findElement(By.id("botao-enviar-cadastro"));
    }

    public void fechar() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }

    public void preencher(PedidoCadastrarCliente pedido) throws InterruptedException {
        preencherCliente(pedido);
        preencherEnderecos(pedido.getEnderecos());
    }

    public void preencherCliente(PedidoCadastrarCliente pedido) throws InterruptedException {
        formDadosPessoais.preencherCliente(pedido);
    }

    public void preencherEnderecos(List<Endereco> enderecos) throws InterruptedException {
        this.secaoEnderecos.preencherEnderecos(enderecos);
    }

    public void adicionarNovoEndereco(){
        this.secaoEnderecos.adicionarNovoEndereco();
    }

    public void enviarCadastro() throws InterruptedException {
        botaoEnviarCadastro.click();
        Thread.sleep(500);

        Alert alert = driver.switchTo().alert();
        Thread.sleep(500);

        alert.accept();
        Thread.sleep(1000);

        alert.dismiss();
        Thread.sleep(500);
    }

}
