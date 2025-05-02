package com.esboco_comix.webapp.fluxo_venda.index;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.esboco_comix.webapp.utils.AbstractTeste;

public class PedirTroca extends AbstractTeste {
    
    public PedirTroca(WebDriver driver) {
        super(driver);
    }

    @Override
    public void abrir() throws InterruptedException{
        driver.get("http://localhost:8080/conta?idcliente=45");
        Thread.sleep(3000);
        driver.get("http://localhost:8080/minhasCompras");
    }

    public void pedirTroca() {
        WebElement btnTroca = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("botao-troca")));
        btnTroca.click();

        WebElement btnPedirTroca = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".botao-enviar-pedido-troca")));
        
        btnPedirTroca.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }
    
}
