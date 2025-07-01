package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;

public class PaginaConta extends AbstractPagina {

    public PaginaConta(WebDriver driver, WebDriverWait wait){
        super(driver, wait, "http://localhost:8080/conta");
    }

    public void logar() throws InterruptedException {
        driver.get(url+"?idcliente=1");
        sleep();
    }

    public void trocarSecao(SecoesConta secao) throws InterruptedException {
        sleep();
        scrollToElement(
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(secao.getIdBotao())))
        ).click();
        sleep();
    }

    public void mostrarCupons() throws InterruptedException{
        trocarSecao(SecoesConta.CUPOM);
        List<WebElement> cuponsConta = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cupom-conta"))
        );

        for (var cupom : cuponsConta){
            scrollToElement(cupom);
            sleep(200);
        }
    }

    public void abrirModal(ModaisConta modal) throws InterruptedException {
        sleep();
        scrollToElement(
            driver.findElement(By.cssSelector(modal.getSeletorBotao()))
        ).click();
    }

    public void abrirModalAtualizar(ModaisConta modal) throws InterruptedException {
        sleep();
        scrollToElement(
                driver.findElements(By.cssSelector(modal.getSeletorBotao())).getLast()
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
        sleep();
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

    public void removerNovoEndereco() throws InterruptedException{
        int ultimoIndex = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".endereco-conta .btn-deletar"))
        ).size() - 1;

        removerEndereco(ultimoIndex);
    }

    public void removerNovoCartao() throws InterruptedException{
        int ultimoIndex = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".cartao-credito-conta .btn-deletar"))
        ).size() - 1;

        removerCartao(ultimoIndex);
    }
}
