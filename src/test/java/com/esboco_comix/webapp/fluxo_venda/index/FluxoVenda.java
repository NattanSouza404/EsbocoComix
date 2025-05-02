package com.esboco_comix.webapp.fluxo_venda.index;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.esboco_comix.webapp.utils.AbstractTeste;

public class FluxoVenda extends AbstractTeste {

    private static String URL = "http://localhost:8080/";
    private List<WebElement> cartoesProduto = new ArrayList<>();
    
    public FluxoVenda(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public void abrir() throws InterruptedException {
        driver.get(URL);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card")));
        cartoesProduto = driver.findElements(By.cssSelector(".card")); 
    }

    public void adicionarItemAoCarrinho(int index) throws InterruptedException{
        cartoesProduto.get(index).findElement(By.cssSelector("button")).click();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        Thread.sleep(500);
    }

    public void abrirCarrinho() throws InterruptedException {
        driver.findElement(By.id("link-carrinho")).click();
        Thread.sleep(3000);
    }

    public void abrirConta() throws InterruptedException {
        driver.get("http://localhost:8080/conta?idcliente=45");
        Thread.sleep(3000);
    }

    public void irParaCompra() {
        driver.findElement(By.id("link-compra")).click();
    }

    public void inserirValorCartao(int index, double valor) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#cartoes input")));
        input.sendKeys(String.valueOf(valor));
        Thread.sleep(3000);
    }

    public void realizarPedido() {
        WebElement botao = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn-enviar-pedido")));
        botao.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

}
