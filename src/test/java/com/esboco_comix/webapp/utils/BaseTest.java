package com.esboco_comix.webapp.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseTest(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void abrir(String url) throws InterruptedException {
        driver.get(url);
    }

    public void fechar(){
        driver.close();
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }
}
