package com.esboco_comix.webapp.paginas.conta;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.controller.pedidos.PedidoAlterarSenha;

public class ModalAlterarSenha {

    private WebDriver driver;

    private WebElement form;
    private WebElement botaoAbrirModal;
    private WebElement botaoEnviar;

    public ModalAlterarSenha(WebDriver webDriver){
        this.driver = webDriver;
        
        this.form = webDriver.findElement(By.id("alterar-senha"));
        this.botaoAbrirModal = webDriver.findElement(By.id("btn-alterar-senha"));
        this.botaoEnviar = form.findElement(By.className("botao-salvar"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput){
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void preencher(PedidoAlterarSenha pedido){
        preencherInput("senhaAntiga", pedido.getSenhaAntiga());
        preencherInput("senhaNova", pedido.getSenhaNova());
        preencherInput("senhaConfirmacao", pedido.getSenhaConfirmacao());
    }

    public void enviar(){
        this.botaoEnviar.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        alert.dismiss();
    }
}
