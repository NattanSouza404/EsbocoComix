package com.esboco_comix.webapp.paginas.cliente.cadastrar;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
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

        Cliente c = pedido.getCliente();
        form.findElement(By.name("nome")).sendKeys(c.getNome());
        form.findElement(By.name("cpf")).sendKeys(c.getCpf());
        form.findElement(By.name("email")).sendKeys(c.getEmail());
        form.findElement(By.name("tipoTelefone")).sendKeys(c.getTelefone().getTipo().name());
        form.findElement(By.name("ddd")).sendKeys(c.getTelefone().getDdd());
        form.findElement(By.name("numero")).sendKeys(c.getTelefone().getNumero());
        form.findElement(By.name("senhaNova")).sendKeys(pedido.getSenhaNova());
        form.findElement(By.name("senhaConfirmacao")).sendKeys(pedido.getSenhaConfirmacao());

        preencherInputSelect(form, "tipoTelefone", c.getTelefone().getTipo().name());
        preencherInput(form, "dataNascimento", c.getDataNascimento());

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
            form.findElement(By.name("cep")).sendKeys(e.getCep());
            form.findElement(By.name("observacoes")).sendKeys(e.getObservacoes());
            form.findElement(By.name("logradouro")).sendKeys(e.getLogradouro());
            form.findElement(By.name("tipoLogradouro")).sendKeys(e.getTipoLogradouro().name());
            form.findElement(By.name("tipoResidencial")).sendKeys(e.getTipoResidencial().name());
            form.findElement(By.name("numero")).sendKeys(e.getNumero());

            sleep();
        }
    }

    public void preencherCartoesCreditos(List<CartaoCredito> cartoes) throws InterruptedException {
        List<WebElement> forms = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cartao-credito"))
        );

        for (int i = 0; i < cartoes.size(); i++){
            CartaoCredito c = cartoes.get(i);
            WebElement form = forms.get(i);

            form.findElement(By.name("numero")).sendKeys(c.getNumero());
            form.findElement(By.name("nomeImpresso")).sendKeys(c.getNomeImpresso());
            form.findElement(By.name("codigoSeguranca")).sendKeys(c.getCodigoSeguranca());
            preencherInputSelect(form, "bandeiraCartao", c.getBandeiraCartao().name());
            preencherSelectTrueOrFalse(form, "isPreferencial", c.isPreferencial());

            sleep();
        }
    }

}
