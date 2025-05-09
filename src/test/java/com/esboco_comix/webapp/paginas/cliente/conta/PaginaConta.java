package com.esboco_comix.webapp.paginas.cliente.conta;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaConta extends AbstractPagina {

    public final ModalAlterarDadosPessoais modalAlterarDadosPessoais;
    public final ModalAlterarSenha modalAlterarSenha;
    public final ModalAlterarEndereco modalAlterarEndereco;
    public final ModalAlterarCartaoCredito modalAlterarCartaoCredito;

    public PaginaConta(WebDriver driver, WebDriverWait wait){
        super(driver, wait);

        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais(driver);
        this.modalAlterarSenha = new ModalAlterarSenha(driver);
        this.modalAlterarEndereco = new ModalAlterarEndereco(driver);
        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito(driver);
    }

    public void abrir(){
        driver.get("http://localhost:8080/conta?idcliente=1");
    }

    public void logar() throws InterruptedException {
        abrir();
        Thread.sleep(1000);
    }

}
