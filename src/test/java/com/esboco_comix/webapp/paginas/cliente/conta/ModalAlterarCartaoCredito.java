package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.web_element.FormElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ModalAlterarCartaoCredito {

    private WebDriver driver;

    private List<WebElement> forms;
    private FormElement form;

    private WebElement botaoAbrirModal;
    
    private WebElement botaoAdicionarCartao;

    public ModalAlterarCartaoCredito(WebDriver webDriver){
        this.driver = webDriver;
        
//        this.forms = webDriver.findElements(By.id("alterar-cartao-credito"));
//        //this.form = new FormElement(webDriver, forms.get(0));
//        this.botaoAbrirModal = webDriver.findElement(By.id("btn-alterar-cartao-credito"));
//        this.botaoAdicionarCartao = webDriver.findElement(By.cssSelector("#footer-secao-cartao-credito button"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException {
        this.form.preencherInput(nome, valorInput);
    }

    public void adicionarNovoCartao() {
        this.botaoAdicionarCartao.click();
    }

    public void preencherInputSelect(String nome, String valorInput) {
        form.preencherInputSelect(nome, valorInput);
    }

    public void inserir() throws InterruptedException {
        this.form.getForm().findElement(By.cssSelector(".btn-atualizar")).click();
        Thread.sleep(1500);

        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }

    public void deletarCartaoCredito() throws InterruptedException {
        this.form.getForm().findElement(By.cssSelector(".btn-remover")).click();
        Thread.sleep(1500);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }
    
}
