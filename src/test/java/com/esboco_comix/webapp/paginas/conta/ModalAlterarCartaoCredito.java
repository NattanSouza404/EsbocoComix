package com.esboco_comix.webapp.paginas.conta;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ModalAlterarCartaoCredito {

    private WebDriver driver;

    private List<WebElement> forms;
    private WebElement botaoAbrirModal;
    
    private WebElement botaoAdicionarCartao;

    public ModalAlterarCartaoCredito(WebDriver webDriver){
        this.driver = webDriver;
        
        this.forms = webDriver.findElements(By.id("alterar-cartao-credito"));
        this.botaoAbrirModal = webDriver.findElement(By.id("btn-alterar-cartao-credito"));
        this.botaoAdicionarCartao = webDriver.findElement(By.cssSelector("#footer-secao-cartao-credito button"));
    }

    public void abrirModal(){
        this.botaoAbrirModal.click();
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        Thread.sleep(100);
        this.forms.get(0).findElement(By.name(nome)).clear();
        this.forms.get(0).findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void adicionarNovoCartao() {
        this.botaoAdicionarCartao.click();
    }

    public void preencherInputSelect(String nome, String valorInput) {
        WebElement dropdown = forms.get(0).findElement(By.name(nome));
        Select select = new Select(dropdown);
        select.selectByValue(valorInput);
    }

    public void inserir() throws InterruptedException {
        this.forms.get(0).findElement(By.cssSelector(".btn-atualizar")).click();
        Thread.sleep(1500);

        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }

    public void deletarCartaoCredito() throws InterruptedException {
        this.forms.get(0).findElement(By.cssSelector(".btn-remover")).click();
        Thread.sleep(1500);
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1500);
        alert.dismiss();
    }

    

    
}
