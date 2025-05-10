package com.esboco_comix.webapp.paginas.cliente.compra;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaCompra extends AbstractPagina {

    public PaginaCompra(WebDriver driver, WebDriverWait wait){
        super(driver, wait, "http://localhost:8080/compra");
    }

    public void adicionarOutroCartao() throws InterruptedException {
        scrollToElement(
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-adicionar-cartao")))
        ).click();
        sleep();
    }

    public void inserirValorCartao(int index, double valor) throws InterruptedException {
        WebElement inputValor = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("#cartoes input")
            )
        ).get(index);

        scrollToElement(inputValor).clear();
        scrollToElement(inputValor).sendKeys(String.valueOf(valor));

        sleep();
    }

    public void realizarPedido() throws InterruptedException {
        scrollToElement(
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-enviar-pedido")))
        ).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
}
