package com.esboco_comix.webapp.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPagina {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPagina(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public abstract void abrir();

    public void fechar(){
        driver.quit();
        DriverManager.cleanup();
    }

    protected void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

    protected WebElement scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));

        return element;
    }
}
