package com.esboco_comix.webapp.paginas.cliente.cadastrar;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.base.AbstractPagina;
import com.esboco_comix.webapp.paginas.cliente.FormDadosPessoais;
import com.esboco_comix.webapp.paginas.cliente.FormEndereco;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PaginaCadastrar extends AbstractPagina {

    private FormDadosPessoais formDadosPessoais;
    private List<FormEndereco> formsEndereco;

    public PaginaCadastrar(WebDriver driver, WebDriverWait wait) throws Exception {
        super(driver, wait);
    }

    @Override
    public void abrir() {
        driver.get("http://localhost:8080/cadastrar");
    }

    public void preencherCliente(CadastrarClienteDTO pedido) throws InterruptedException {
        if (formDadosPessoais == null){
            this.formDadosPessoais = new FormDadosPessoais(driver,
                    wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.id("cadastrar-dados-pessoais")
                    ))
            );
        }

        formDadosPessoais.preencherCliente(pedido);
    }

    public void adicionarNovoEndereco(){
        scrollToElement(
            driver.findElement(By.cssSelector("#footer-secao-endereco button"))
        ).click();
    }

    public void enviarCadastro() throws InterruptedException {
        scrollToElement(driver.findElement(By.id("botao-enviar-cadastro")))
                .click();
        sleep();

        wait.until(ExpectedConditions.alertIsPresent())
                .accept();

        wait.until(ExpectedConditions.alertIsPresent())
                .dismiss();
    }

    public void preencherEnderecos(List<Endereco> enderecos) throws InterruptedException {
        if (formsEndereco == null){
            formsEndereco = new ArrayList<>();

            List<WebElement> forms = driver.findElements(By.className("endereco"));

            for (WebElement element : forms) {
                formsEndereco.add(new FormEndereco(driver, element));
            }
        }

        for (int i = 0; i < enderecos.size(); i++){
            formsEndereco.get(i).preencher(enderecos.get(i));
        }
    }

}
