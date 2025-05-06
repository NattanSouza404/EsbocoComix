package com.esboco_comix.webapp.fluxo_venda.index;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.esboco_comix.webapp.utils.BaseTest;

public class PedirTrocaDevolucao extends BaseTest {
    
    public PedirTrocaDevolucao(WebDriver driver) {
        super(driver);
    }

    public void pedirTroca() throws InterruptedException {
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.className("botao-troca"))
        ).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void pedirDevolucao() throws InterruptedException {
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.className("botao-devolucao"))
        ).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
    
}
