package com.esboco_comix.webapp.paginas.admin.clientes;

import com.esboco_comix.dto.FiltrarClienteDTO;
import com.esboco_comix.webapp.base.AbstractPagina;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaClientes extends AbstractPagina {

    public PaginaClientes(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "http://localhost:8080/admin/clientes");
    }

    public void consultarPorFiltro(FiltrarClienteDTO filtro) throws Exception {
        if (filtro.getGenero() != null){
            adicionarFiltro("genero", filtro.getGenero().name());
        }
        if (filtro.getNome() != null){
            adicionarFiltro("nome", filtro.getNome());
        }
        sleep();

        scrollToElement(
            driver.findElement(By.cssSelector("#filtro-clientes > button"))
        ).click();
        sleep();

        resetarFiltros();
    }

    private void adicionarFiltro(String name, String valor) throws InterruptedException {
        scrollToElement(driver.findElement(By.name(name))).sendKeys(valor);
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

    public void logarComoNovoCliente() throws InterruptedException {
        scrollToElement(
            driver.findElements(By.className("btn-editar")).getLast()
        ).click();
        sleep();
    }

    public void inativarNovoCliente() throws InterruptedException {
        scrollToElement(
            driver.findElements(By.className("btn-inativar")).getLast()
        ).click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        sleep();

        scrollToElement(
            driver.findElements(By.className("btn-inativar")).getLast()
        );
        sleep();
    }

    public void adicionarCupomPromocional(double valor) throws InterruptedException {
        scrollToElement(
                driver.findElement(By.cssSelector(".btn-adicionar-cupom"))
        ).click();
        sleep();

        WebElement form = driver.findElement(By.id("form-adicionar-cupom-promocional"));

        WebElement inputValor = scrollToElement(
            form.findElement(By.cssSelector("[name=\"valor\"]"))
        );

        inputValor.clear();
        inputValor.sendKeys(String.valueOf(valor));

        scrollToElement(
            form.findElement(By.cssSelector("button"))
        ).click();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }
}
