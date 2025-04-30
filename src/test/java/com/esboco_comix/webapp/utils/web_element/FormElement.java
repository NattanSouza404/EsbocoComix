package com.esboco_comix.webapp.utils.web_element;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import lombok.Getter;

public class FormElement {

    @Getter
    private WebElement form;

    public FormElement(WebElement webElement){
        this.form = webElement;
    }

    public void preencherInput(String nome, String valorInput) throws InterruptedException{
        this.form.findElement(By.name(nome)).clear();
        Thread.sleep(200);
        this.form.findElement(By.name(nome)).sendKeys(valorInput);
    }

    public void preencherInputData(String nome, LocalDate data, WebDriver driver) throws InterruptedException{
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
