package com.esboco_comix.webapp.paginas.cliente.carrinho;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaCarrinho extends AbstractPagina {

    public PaginaCarrinho(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/carrinho");
    }

    public void irParaCompra() throws InterruptedException {
        wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("link-compra"))
        ).click();
        sleep();
    }

    public void atualizarItemCarrinho(int index, int quantidade) throws InterruptedException {
        WebElement linhaItem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector(".item-carrinho")
        )).get(index);

        linhaItem.findElement(By.cssSelector("input")).clear();
        linhaItem.findElement(By.cssSelector("input")).sendKeys(String.valueOf(quantidade));
        sleep();

        linhaItem.findElement(By.className("btn-atualizar")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void removerItemCarrinho(int index) throws InterruptedException {
        WebElement linhaItem = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".item-carrinho")
        )).get(index);

        linhaItem.findElement(By.className("btn-deletar")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
}
