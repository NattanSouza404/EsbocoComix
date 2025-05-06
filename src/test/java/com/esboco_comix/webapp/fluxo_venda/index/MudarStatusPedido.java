package com.esboco_comix.webapp.fluxo_venda.index;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.esboco_comix.webapp.utils.BaseTest;

public class MudarStatusPedido extends BaseTest {

    public MudarStatusPedido(WebDriver driver) {
        super(driver);
    }

    public void mudarStatus(String status) throws InterruptedException{
        WebElement tr = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("#orderTable tr"))
        );

        WebElement select = tr.findElement(By.tagName("select"));
        select.sendKeys(status);
        sleep();

        tr.findElement(By.tagName("button")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
    
}
