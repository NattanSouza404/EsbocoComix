package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;

public class PaginaConta extends AbstractPagina {

    public PaginaConta(WebDriver driver, WebDriverWait wait){
        super(driver, wait, "http://localhost:8080/conta?idcliente=1");
    }

    public void logar() throws InterruptedException {
        abrir();
    }

    public void trocarSecao(SecoesConta secao) throws InterruptedException {
        scrollToElement(
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(secao.getIdBotao())))
        ).click();
        sleep();
    }

    public void abrirModal(ModaisConta modal){
        scrollToElement(
            driver.findElement(By.cssSelector(modal.getSeletorBotao()))
        ).click();
    }

    public void preencherInputModal(ModaisConta modal, String nome, String valor){
        driver.findElement(By.cssSelector(modal.getSeletorForm())).findElement(By.name(nome)).clear();
        driver.findElement(By.cssSelector(modal.getSeletorForm())).findElement(By.name(nome)).sendKeys(valor);
    }

    public void preencherInputSelectModal(ModaisConta modal, String nome, String valor) {
        preencherInputSelect(
            driver.findElement(By.cssSelector(modal.getSeletorForm())),
            nome, valor
        );
    }

    public void preencherInputDataModal(ModaisConta modal, String nome, LocalDate data){
        WebElement form = driver.findElement(By.cssSelector(modal.getSeletorForm()));

        form.findElement(By.name(nome)).clear();
        preencherInput(form, nome, data);
    }

    public void enviar(ModaisConta modal) throws InterruptedException {
        WebElement form = driver.findElement(By.cssSelector(modal.getSeletorForm()));
        form.findElement(By.className("botao-salvar")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        sleep();
        alert.dismiss();
    }

    public void editarEndereco(int index) {
        scrollToElement(
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(ModaisConta.ALTERAR_ENDERECO.getSeletorBotao())
                )).get(index)
        ).click();
    }

    public void removerEndereco(int index) throws InterruptedException {
        scrollToElement(
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".endereco-conta .btn-deletar")
                )).get(index)
        ).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        sleep();
        alert.dismiss();
    }

    public void removerCartao(int index) throws InterruptedException {
        scrollToElement(
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".cartao-credito-conta .btn-deletar")
                )).get(index)
        ).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        sleep();
        alert.dismiss();
    }


}
