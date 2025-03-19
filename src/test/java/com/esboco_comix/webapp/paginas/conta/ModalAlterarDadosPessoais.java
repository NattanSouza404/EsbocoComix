package com.esboco_comix.webapp.paginas.conta;

import java.time.LocalDate;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModalAlterarDadosPessoais {
    private WebDriver driver;

    private WebElement form;
    private WebElement botaoAbrirModal;
    private WebElement botaoEnviar;

    public ModalAlterarDadosPessoais(WebDriver webDriver){
        this.driver = webDriver;
        
        this.form = webDriver.findElement(By.id("alterar-dados-pessoais"));
        this.botaoAbrirModal = webDriver.findElement(By.id("btn-editar-cadastro"));
        this.botaoEnviar = form.findElement(By.className("botao-salvar"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form.findElement(By.name(nome)).clear();
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
        Thread.sleep(1000);
    }

    public void preencherInput(String nome, LocalDate data){
        this.form.findElement(By.name(nome)).clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '"+data.toString()+"';", this.form.findElement(By.name(nome)));
    }

    public void enviar() throws InterruptedException {
        this.botaoEnviar.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }
}
