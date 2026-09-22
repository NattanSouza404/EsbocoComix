package com.esboco_comix.webapp.paginas.cliente.cadastrar;

import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dto.CadastrarCartaoCreditoDTO;
import com.esboco_comix.cliente.dto.CadastrarClienteDTO;
import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PaginaCadastrar extends AbstractPagina {

    public PaginaCadastrar(WebDriver driver, WebDriverWait wait) throws Exception {
        super(driver, wait, "http://localhost:8080/cadastrar");
    }

    public void preencherCliente(CadastrarClienteDTO pedido) throws InterruptedException {
        WebElement form = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("cadastrar-dados-pessoais"))
        );

        form.findElement(By.name("nome")).sendKeys(pedido.nome());
        form.findElement(By.name("cpf")).sendKeys(pedido.cpf());
        form.findElement(By.name("email")).sendKeys(pedido.email());
        form.findElement(By.name("tipoTelefone")).sendKeys(pedido.telefone().tipo().name());
        form.findElement(By.name("ddd")).sendKeys(pedido.telefone().ddd());
        form.findElement(By.name("numero")).sendKeys(pedido.telefone().numero());
        form.findElement(By.name("senhaNova")).sendKeys(pedido.senhaNova());
        form.findElement(By.name("senhaConfirmacao")).sendKeys(pedido.senhaConfirmacao());

        preencherInputSelect(form, "tipoTelefone", pedido.telefone().tipo().name());
        preencherInput(form, "dataNascimento", pedido.dataNascimento());

        sleep();
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
        sleep();

        wait.until(ExpectedConditions.alertIsPresent())
            .dismiss();
        sleep();
    }

    public void preencherEnderecos(List<Endereco> enderecos) throws InterruptedException {
        List<WebElement> forms = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("endereco"))
        );

        for (int i = 0; i < enderecos.size(); i++){
            Endereco e = enderecos.get(i);
            WebElement form = forms.get(i);

            form.findElement(By.name("fraseCurta")).sendKeys(e.getFraseCurta());

            preencherSelectTrueOrFalse(form, "isResidencial", e.getIsResidencial());
            preencherSelectTrueOrFalse(form, "isEntrega", e.getIsEntrega());
            preencherSelectTrueOrFalse(form, "isCobranca", e.getIsCobranca());

            form.findElement(By.name("bairro")).sendKeys(e.getBairro());
            form.findElement(By.name("cidade")).sendKeys(e.getCidade());
            form.findElement(By.name("estado")).sendKeys(e.getEstado());
            form.findElement(By.name("pais")).sendKeys(e.getPais());
            form.findElement(By.name("cep")).sendKeys(e.getCep().valor());
            form.findElement(By.name("observacoes")).sendKeys(e.getObservacoes());
            form.findElement(By.name("logradouro")).sendKeys(e.getLogradouro());
            form.findElement(By.name("tipoLogradouro")).sendKeys(e.getTipoLogradouro().name());
            form.findElement(By.name("tipoResidencial")).sendKeys(e.getTipoResidencial().name());
            form.findElement(By.name("numero")).sendKeys(e.getNumero());

            sleep();
        }
    }

    public void preencherCartoesCreditos(
        List<CadastrarCartaoCreditoDTO> cartoes
    ) throws InterruptedException {
        List<WebElement> forms = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cartao-credito"))
        );

        for (int i = 0; i < cartoes.size(); i++){
            var c = cartoes.get(i);
            WebElement form = forms.get(i);

            form.findElement(By.name("numero")).sendKeys(c.numero());
            form.findElement(By.name("nomeImpresso")).sendKeys(c.nomeImpresso());
            form.findElement(By.name("codigoSeguranca")).sendKeys(c.codigoSeguranca());
            preencherInputSelect(form, "bandeiraCartao", c.bandeiraCartao().name());
            preencherSelectTrueOrFalse(form, "isPreferencial", c.isPreferencial());

            sleep();
        }
    }

}
