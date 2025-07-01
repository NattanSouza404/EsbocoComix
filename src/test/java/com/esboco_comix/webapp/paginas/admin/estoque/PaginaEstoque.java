package com.esboco_comix.webapp.paginas.admin.estoque;

import com.esboco_comix.model.entidades.EntradaEstoque;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.base.AbstractPagina;

public class PaginaEstoque extends AbstractPagina {

    public PaginaEstoque(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/admin/estoque");
    }

    public void realizarEntradaEstoque(int index, EntradaEstoque entradaEstoque) throws InterruptedException {
        scrollToElement(
            driver.findElements(By.className("btn-entrada-estoque")).get(index)
        ).click();
        sleep();

        WebElement form = scrollToElement(driver.findElement(By.id("form-entrada-estoque")));

        form.findElement(By.name("quantidade"))
            .sendKeys(String.valueOf(entradaEstoque.getQuantidade()));
        sleep();

        form.findElement(By.name("valorCusto"))
            .sendKeys(String.valueOf(entradaEstoque.getValorCusto()));
        sleep();

        form.findElement(By.name("fornecedor"))
            .sendKeys(entradaEstoque.getFornecedor());
        sleep();

        if (entradaEstoque.getDataEntrada() != null){
            form.findElement(By.name("dataPadrao"))
                .click();
            sleep();

            preencherInput(form, "dataEntrada", entradaEstoque.getDataEntrada());
            sleep();
        }

        scrollToElement(
            driver.findElement(By.id("btn-enviar-entrada-estoque"))
        ).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

}
