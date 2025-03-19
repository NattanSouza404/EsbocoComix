package com.esboco_comix.webapp.paginas.conta;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        Thread.sleep(100);
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
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
