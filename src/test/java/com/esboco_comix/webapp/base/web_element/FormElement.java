package com.esboco_comix.webapp.base.web_element;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;

public class FormElement {

    private WebDriver driver;

    @Getter
    private WebElement form;

    public FormElement(WebDriver driver, WebElement webElement){
        this.driver = driver;
        this.form = webElement;
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form.findElement(By.name(nome)).clear();
        Thread.sleep(200);
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void preencherInputData(String nome, LocalDate data) throws InterruptedException{
        this.form.findElement(By.name(nome)).clear();
        Thread.sleep(200);
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '"+data.toString()+"';", this.form.findElement(By.name(nome)));
    }

    public void selecionarInputTrueOrFalse(String nome, boolean valor) {
        WebElement dropdown = form.findElement(By.name(nome));
        Select select = new Select(dropdown);
        select.selectByValue(String.valueOf(valor));
    }

    public void preencherInputSelect(String nome, String valorInput) {
        WebElement dropdown = form.findElement(By.name(nome));
        Select select = new Select(dropdown);
        select.selectByValue(valorInput);
    }

}
