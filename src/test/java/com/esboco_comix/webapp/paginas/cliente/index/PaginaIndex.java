package com.esboco_comix.webapp.paginas.cliente.index;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PaginaIndex extends AbstractPagina {
    public PaginaIndex(WebDriver driver, WebDriverWait wait){
        super(driver, wait, "http://localhost:8080/");
    }

    public void adicionarItemAoCarrinho(int index) throws InterruptedException{
        List<WebElement> cards = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".card")));

        if (index < 0 || index >= cards.size()) {
            throw new IllegalArgumentException("Índice fora dos limites: " + index);
        }

        WebElement card = cards.get(index);
        WebElement button = card.findElement(By.cssSelector("button"));

        scrollToElement(button).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();
    }

}
