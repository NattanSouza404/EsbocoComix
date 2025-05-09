package com.esboco_comix.webapp.paginas.cliente.conta;

import org.openqa.selenium.*;

import java.time.LocalDate;

public class ModalAlterarDadosPessoais {
    private WebDriver driver;

    private WebElement form;
    private WebElement botaoAbrirModal;
    private WebElement botaoEnviar;

    public ModalAlterarDadosPessoais(WebDriver webDriver){
        this.driver = webDriver;
    }

    public void abrirModal(){
        this.botaoAbrirModal = driver.findElement(By.id("btn-editar-cadastro"));
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form = driver.findElement(By.id("alterar-dados-pessoais"));
        this.form.findElement(By.name(nome)).clear();
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
        Thread.sleep(1000);
    }

    public void preencherInput(String nome, LocalDate data){
        this.form = driver.findElement(By.id("alterar-dados-pessoais"));
        this.form.findElement(By.name(nome)).clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '"+data.toString()+"';", this.form.findElement(By.name(nome)));
    }

    public void enviar() throws InterruptedException {
        this.form = driver.findElement(By.id("alterar-dados-pessoais"));
        this.botaoEnviar = form.findElement(By.className("botao-salvar"));
        this.botaoEnviar.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }
}
