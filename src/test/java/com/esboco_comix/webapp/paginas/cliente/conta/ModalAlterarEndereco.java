package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.web_element.FormElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ModalAlterarEndereco {

    private WebDriver driver;

    private WebElement botaoAbrirModal;

    private List<WebElement> forms;
    private FormElement form;

    public ModalAlterarEndereco(WebDriver webDriver){
        this.driver = webDriver;

        //this.forms = webDriver.findElements(By.className("endereco"));
        //this.form = new FormElement(webDriver, forms.get(0));
        //this.botaoAbrirModal = webDriver.findElement(By.id("btn-alterar-endereco"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void adicionarEndereco() {
        driver.findElement(By.cssSelector("#footer-secao-endereco button")).click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form.preencherInput(nome, valorInput);
    }

    public void atualizar() throws InterruptedException{
        this.form.getForm().findElement(By.cssSelector(".btn-atualizar")).click();
        Thread.sleep(1500);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }

    public void deletarEndereco() throws InterruptedException {
        Thread.sleep(1000);
        this.forms.get(1).findElement(By.cssSelector(".btn-remover")).click();
        Thread.sleep(1500);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }

}
