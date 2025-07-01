package com.esboco_comix.webapp.paginas.admin.clientes;

import com.esboco_comix.webapp.base.AbstractPagina;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaClientes extends AbstractPagina {

    public PaginaClientes(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/admin/clientes");
    }

    public void adicionarFiltro(String name, String valor) throws InterruptedException {
        scrollToElement(driver.findElement(By.name(name))).sendKeys(valor);
        sleep();
    }

    public void consultarPorFiltro() throws Exception {
        scrollToElement(
            driver.findElement(By.cssSelector("#filtro-clientes > button"))
        ).click();
        sleep();
    }

    public void resetarFiltros() throws InterruptedException {
        new Select(
            scrollToElement(driver.findElement(By.name("genero")))
        ).selectByIndex(0);

        new Select(
            scrollToElement(driver.findElement(By.name("isAtivo")))
        ).selectByIndex(0);

        List<WebElement> inputs = driver.findElements(
            By.cssSelector("#filtro-clientes input")
        );

        for (WebElement i : inputs) {
            scrollToElement(i).clear();
        }

        sleep();
    }

    public void visualizarPedidosCliente(int index) throws InterruptedException {
        scrollToElement(
            driver.findElements(By.className("btn-transacoes")).get(index)
        ).click();

        sleep();
    }
}
