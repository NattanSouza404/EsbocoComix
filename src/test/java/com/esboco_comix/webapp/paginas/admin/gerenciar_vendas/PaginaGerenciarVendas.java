package com.esboco_comix.webapp.paginas.admin.gerenciar_vendas;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaGerenciarVendas extends AbstractPagina {
    public PaginaGerenciarVendas(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/admin/gerenciarVendas");
    }

    public void mudarStatus(String status) throws InterruptedException {
        WebElement tr = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#tabela-pedidos tr"))
        );

        WebElement select = tr.findElement(By.tagName("select"));
        select.sendKeys(status);
        sleep();

        tr.findElement(By.tagName("button")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        if (status.equals("Devolução concluída") || status.equals("Troca concluída")){
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            sleep();
        }

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

    public void mudarStatusItem(int index, String status) throws InterruptedException {
        WebElement tr = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector("#tabela-pedido-troca tr"))).get(index);

        WebElement select = tr.findElement(By.tagName("select"));
        select.sendKeys(status);
        sleep();

        tr.findElement(By.tagName("button")).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        if (status.equals("Devolução concluída") || status.equals("Troca concluída")){
            wait.until(ExpectedConditions.alertIsPresent()).accept();
            sleep();
        }

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }
}
