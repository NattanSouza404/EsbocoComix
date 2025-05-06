package com.esboco_comix.webapp.fluxo_venda.index;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.esboco_comix.webapp.utils.BaseTest;

public class FluxoVenda extends BaseTest {

    private static String URL = "http://localhost:8080/";
    
    public FluxoVenda(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public void abrir(String url) throws InterruptedException {
        driver.get(URL);
    }

    public void adicionarItemAoCarrinho(int index) throws InterruptedException{
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card"))) 
            .get(index).findElement(By.cssSelector("button")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void irParaCompra() throws InterruptedException {
        driver.findElement(By.id("link-compra")).click();
        sleep();
    }

    public void inserirValorCartao(int index, double valor) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cartoes input")))
            .sendKeys(String.valueOf(valor));
        sleep();
    }

    public void realizarPedido() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-enviar-pedido")))
            .click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

}
