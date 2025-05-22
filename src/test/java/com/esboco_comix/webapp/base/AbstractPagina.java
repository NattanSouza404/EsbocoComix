package com.esboco_comix.webapp.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public abstract class AbstractPagina {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String url;

    public AbstractPagina(WebDriver driver, WebDriverWait wait, String url){
        this.driver = driver;
        this.wait = wait;
        this.url = url;
    }

    public void abrir() throws InterruptedException {
        driver.get(url);
        sleep();
    }

    protected void sleep() throws InterruptedException {
        Thread.sleep(1500);
    }

    protected WebElement scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }

    protected void preencherInputSelect(WebElement form, String nome, String valorInput) {
        new Select(
            scrollToElement(
                form.findElement(By.name(nome))
            )
        ).selectByValue(valorInput);
    }

    protected void preencherInput(WebElement form, String nome, LocalDate data) {
        scrollToElement(
            form.findElement(By.name(nome))
        ).clear();

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = '"+data.toString()+"';",
            scrollToElement(form.findElement(By.name(nome)))
        );
    }

    protected void preencherSelectTrueOrFalse(WebElement form, String nome, boolean valor) {
        new Select(
            scrollToElement(
                form.findElement(By.name(nome))
            )
        ).selectByValue(String.valueOf(valor));
    }
}
