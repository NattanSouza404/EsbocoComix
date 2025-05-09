package com.esboco_comix.webapp.paginas.admin.gerenciar_vendas;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaGerenciarVendas extends AbstractPagina {
    public PaginaGerenciarVendas(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void abrir() {
        driver.get("http://localhost:8080/admin/gerenciarVendas");
    }

    public void mudarStatus(String status) throws InterruptedException {
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
