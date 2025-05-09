package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.web_element.FormElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModalAlterarSenha {

    private WebDriver driver;

    private FormElement form;

    public ModalAlterarSenha(WebDriver webDriver){
        this.driver = webDriver;
    }

    public void abrirModal(){
        driver.findElement(By.id("btn-alterar-senha")).click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form = new FormElement(driver, driver.findElement(By.id("alterar-senha")));
        this.form.preencherInput(nome, valorInput);
    }

    public void enviar() throws InterruptedException {
        form.getForm().findElement(By.className("botao-salvar")).click();
        Thread.sleep(1000);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }
}
