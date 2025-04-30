package com.esboco_comix.webapp.crud_cliente.conta;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.esboco_comix.webapp.utils.web_element.FormElement;

public class ModalAlterarSenha {

    private WebDriver driver;

    private FormElement form;
    private WebElement botaoAbrirModal;
    private WebElement botaoEnviar;

    public ModalAlterarSenha(WebDriver webDriver){
        this.driver = webDriver;
        
        this.form = new FormElement(webDriver.findElement(By.id("alterar-senha")));
        this.botaoAbrirModal = webDriver.findElement(By.id("btn-alterar-senha"));
        this.botaoEnviar = form.getForm().findElement(By.className("botao-salvar"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form.preencherInput(nome, valorInput);
    }

    public void enviar() throws InterruptedException {
        this.botaoEnviar.click();
        Thread.sleep(1000);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }
}
