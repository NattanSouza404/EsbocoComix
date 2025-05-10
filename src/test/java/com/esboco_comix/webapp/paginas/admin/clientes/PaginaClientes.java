package com.esboco_comix.webapp.paginas.admin.clientes;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaClientes extends AbstractPagina {

    public PaginaClientes(WebDriver driver, WebDriverWait wait) throws Exception {
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

    public void resetarFiltros() {
        //TODO colocar para resetar todos os filtros

        new Select(
                scrollToElement(driver.findElement(By.name("genero")))
        ).selectByIndex(0);
    }

}
