package com.esboco_comix.webapp.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractTeste {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractTeste(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract void abrir() throws InterruptedException;

    public void fechar() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }
}
