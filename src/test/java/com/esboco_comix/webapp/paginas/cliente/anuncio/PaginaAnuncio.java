package com.esboco_comix.webapp.paginas.cliente.anuncio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.base.AbstractPagina;

public class PaginaAnuncio extends AbstractPagina {

    public PaginaAnuncio(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/anuncio");
    }

    public void abrir(int idQuadrinho) {
        driver.get(url+"?id="+idQuadrinho);
    }

    public void adicionarItemAoCarrinho(int quantidade) throws InterruptedException{
        WebElement secaoAdicionarItem = scrollToElement(
            driver.findElement(By.id("adicionar-carrinho"))
        );

        WebElement input = secaoAdicionarItem.findElement(By.cssSelector("input"));
        
        input.clear();
        input.sendKeys(String.valueOf(quantidade));
        sleep();

        secaoAdicionarItem.findElement(By.cssSelector("button"))
            .click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

}
