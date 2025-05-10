package com.esboco_comix.webapp.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void sleep() throws InterruptedException {
        Thread.sleep(1500);
    }

    protected WebElement scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }
}
