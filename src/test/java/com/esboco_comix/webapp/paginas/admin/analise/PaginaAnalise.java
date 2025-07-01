package com.esboco_comix.webapp.paginas.admin.analise;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.base.AbstractPagina;

public class PaginaAnalise extends AbstractPagina {

    public PaginaAnalise(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/admin/analise");
    }

    public void scrollarGraficos() throws InterruptedException {
        driver.findElements(By.className("grafico-linha"))
            .forEach(grafico ->  {
                scrollToElement(grafico);
                try {
                    sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public void colocarDataInicio(LocalDate data) throws InterruptedException {
        WebElement form = scrollToElement(
            driver.findElement(By.className("form-filtros"))
        );

        preencherInput(form,"dataInicio", data);
        sleep();
    }

    public void colocarDataFinal(LocalDate data) throws InterruptedException {
        WebElement form = scrollToElement(
            driver.findElement(By.className("form-filtros"))
        );

        preencherInput(form,"dataFinal", data);
        sleep();
    }

    public void filtrarPorData() throws InterruptedException {
        scrollToElement(
            driver.findElement(By.id("btn-pesquisar"))
        ).click();

        sleep();
    }
    
}
