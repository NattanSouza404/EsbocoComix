package com.esboco_comix.webapp.fluxo_venda.index;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.esboco_comix.webapp.utils.AbstractTeste;

public class MudarStatus extends AbstractTeste {

    public MudarStatus(WebDriver driver) {
        super(driver);
    }

    @Override
    public void abrir() throws InterruptedException {
        driver.get("http://localhost:8080/admin/gerenciarVendas");
    }

    public void mudarStatus(String status) throws InterruptedException{
        WebElement tr = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("#orderTable tr"))
        );

        WebElement select = tr.findElement(By.tagName("select"));
        select.sendKeys(status);
        Thread.sleep(5000);

        WebElement btn = tr.findElement(By.tagName("button"));
        btn.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
    
}
