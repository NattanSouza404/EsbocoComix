package com.esboco_comix.webapp.casos_de_uso.conta;

import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlterarEndereco {
    private PaginaConta paginaConta;

    public AlterarEndereco(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException {
        paginaConta.logar();
    }

    public void trocarSecaoEndereco() throws InterruptedException {
        paginaConta.trocarSecao(SecoesConta.ENDERECO);
    }

    public void editarEndereco(int index) {
        paginaConta.editarEndereco(index);
    }

    public void preencherInputModal(String nome, String valor) {
        paginaConta.preencherInputModal(ModaisConta.ALTERAR_ENDERECO, nome, valor);
    }

    public void enviar() throws InterruptedException {
        paginaConta.enviar(ModaisConta.ALTERAR_ENDERECO);
    }
}
