package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Cliente;

public class FormDadosPessoais {

    private WebDriver driver;
    private WebElement form;
    private WebElement campoData;
        
    public FormDadosPessoais(WebDriver webDriver){
        this.driver = webDriver;
        this.form = webDriver.findElement(By.id("cadastrar-dados-pessoais"));
        this.campoData = form.findElement(By.name("dataNascimento"));
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        Thread.sleep(100);
        form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void preencherCliente(PedidoCadastrarCliente pedido) throws InterruptedException {
        Cliente c = pedido.getCliente();

        preencherInput("nome", c.getNome());
        preencherInput("cpf", c.getCpf());
        preencherInput("email", c.getEmail());
        preencherInput("tipoTelefone", c.getTelefone().getTipo());
        preencherInput("ddd", c.getTelefone().getDdd());
        preencherInput("numero", c.getTelefone().getNumero());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '2013-02-03';", this.campoData);

        preencherInput("senhaNova", pedido.getSenhaNova());
        preencherInput("senhaConfirmacao", pedido.getSenhaConfirmacao());
    }

}
