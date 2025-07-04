package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Endereco;
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

    public void preencherModalEndereco(ModaisConta modal, Endereco e) {
        preencherInputModal(modal, "fraseCurta", e.getFraseCurta());

        WebElement form = scrollToElement(
                driver.findElement(By.cssSelector(modal.getSeletorForm()))
        );

        preencherSelectTrueOrFalse(form,"isResidencial", e.getIsResidencial());
        preencherSelectTrueOrFalse(form,"isEntrega", e.getIsEntrega());
        preencherSelectTrueOrFalse(form,"isCobranca", e.getIsCobranca());

        preencherInputModal(modal, "bairro", e.getBairro());
        preencherInputModal(modal, "cidade", e.getCidade());
        preencherInputModal(modal, "estado", e.getEstado());
        preencherInputModal(modal, "pais", e.getPais());
        preencherInputModal(modal, "cep", e.getCep());
        preencherInputModal(modal, "observacoes", e.getObservacoes());
        preencherInputModal(modal, "logradouro", e.getLogradouro());
        preencherInputSelectModal(modal, "tipoLogradouro", e.getTipoLogradouro().name());
        preencherInputSelectModal(modal, "tipoResidencial", e.getTipoResidencial().name());
        preencherInputModal(modal, "numero", e.getNumero());
    }

    public void preencherModalCartao(ModaisConta modal, CartaoCredito c) {
        preencherInputModal(modal, "numero", c.getNumero());
        preencherInputModal(modal, "nomeImpresso", c.getNomeImpresso());
        preencherInputModal(modal, "codigoSeguranca", c.getCodigoSeguranca());
        preencherInputSelectModal(modal, "bandeiraCartao", c.getBandeiraCartao().name());

        WebElement form = scrollToElement(
                driver.findElement(By.cssSelector(modal.getSeletorForm()))
        );

        preencherSelectTrueOrFalse(form, "isPreferencial", c.isPreferencial());
    }
}
