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

    public void abrirChatIA() throws InterruptedException {
        scrollToElement(
            driver.findElement(By.id("abrir-chat-ia"))
        ).click();
        sleep();
    }

    public void enviarMensagemChatIA(String mensagem) throws InterruptedException {
        scrollToElement(
            driver.findElement(By.className("caixa-mensagem-usuario"))
        ).sendKeys(mensagem);
        sleep();

        scrollToElement(
            driver.findElement(By.cssSelector(".modal-footer .btn-enviar"))
        ).click();
        sleep(4000);
    }

    public void toggleFiltroCategoria(String categoria) throws InterruptedException{
        scrollToElement(
            driver.findElement(By.id(categoria))
        ).click();
        sleep();
    }

    public void pesquisar() throws InterruptedException {
        scrollToElement(
            driver.findElement(By.id("btn-buscar-quadrinho"))
        ).click();
        
        scrollToElement(
            driver.findElement(By.name("grupoPrecificacao"))
        );

        sleep();
    }

    public void adicionarFiltro(String nome, String valor) throws InterruptedException {
        WebElement form = driver.findElement(By.id("busca-quadrinhos"));
        
        WebElement filtro = scrollToElement(
            form.findElement(By.name(nome))
        );
        filtro.clear();
        filtro.sendKeys(valor);

        sleep();
    }

}
