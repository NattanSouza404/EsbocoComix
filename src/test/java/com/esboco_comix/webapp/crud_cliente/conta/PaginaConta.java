package com.esboco_comix.webapp.crud_cliente.conta;

import org.openqa.selenium.WebDriver;

import com.esboco_comix.webapp.utils.BaseTest;

public class PaginaConta extends BaseTest {

    public final ModalAlterarDadosPessoais modalAlterarDadosPessoais;
    public final ModalAlterarSenha modalAlterarSenha;
    public final ModalAlterarEndereco modalAlterarEndereco;
    public final ModalAlterarCartaoCredito modalAlterarCartaoCredito;
    
    public PaginaConta(WebDriver driver){
        super(driver);
        
        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais(driver);
        this.modalAlterarSenha = new ModalAlterarSenha(driver);
        this.modalAlterarEndereco = new ModalAlterarEndereco(driver);
        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito(driver);
    }

}
