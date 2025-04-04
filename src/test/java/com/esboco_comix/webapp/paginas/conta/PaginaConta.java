package com.esboco_comix.webapp.paginas.conta;

import org.openqa.selenium.WebDriver;

import com.esboco_comix.webapp.webdriver.DriverTeste;

public class PaginaConta {

    private static String URL = "http://localhost:8080/conta?idcliente=45"; 

    private WebDriver driver;

    public final ModalAlterarDadosPessoais modalAlterarDadosPessoais;
    public final ModalAlterarSenha modalAlterarSenha;
    public final ModalAlterarEndereco modalAlterarEndereco;
    public final ModalAlterarCartaoCredito modalAlterarCartaoCredito;
    
    public PaginaConta(){
        this.driver = new DriverTeste();
        driver.get(URL);

        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais(driver);
        this.modalAlterarSenha = new ModalAlterarSenha(driver);
        this.modalAlterarEndereco = new ModalAlterarEndereco(driver);
        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito(driver);
    }

    public void fechar() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }

}
