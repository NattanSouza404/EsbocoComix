package com.esboco_comix.webapp.paginas.cliente.minhas_compras;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaMinhasCompras extends AbstractPagina {

    public PaginaMinhasCompras(WebDriver driver, WebDriverWait wait){
        super(driver, wait, "http://localhost:8080/minhasCompras");
    }

    public void pedirTroca(int index) throws InterruptedException {
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("botao-troca"))
        ).get(index).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void pedirDevolucao(int index) throws InterruptedException {
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("botao-devolucao"))
        ).get(index).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void pedirTrocaItem(int index) throws InterruptedException {
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("botao-troca-item"))
        ).get(index).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void pedirDevolucaoItem(int index) throws InterruptedException {
        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("botao-devolucao-item"))
        ).get(index).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
}
