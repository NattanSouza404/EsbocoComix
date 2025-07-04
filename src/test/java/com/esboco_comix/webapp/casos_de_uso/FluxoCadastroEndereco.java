package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FluxoCadastroEndereco {
    private final PaginaConta paginaConta;

    public FluxoCadastroEndereco(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void adicionarEndereco(Endereco e) throws InterruptedException {
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.ENDERECO);

        var modal = ModaisConta.ADICIONAR_ENDERECO;

        paginaConta.abrirModal(modal);

        paginaConta.preencherModalEndereco(modal, e);

        paginaConta.enviar(modal);
    }

    public void alterarNovoEndereco(Endereco e) throws InterruptedException{
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.ENDERECO);
        paginaConta.abrirModalAtualizar(ModaisConta.ALTERAR_ENDERECO);

        paginaConta.preencherModalEndereco(ModaisConta.ALTERAR_ENDERECO, e);

        paginaConta.enviar(ModaisConta.ALTERAR_ENDERECO);
    }

    public void deletarNovoEndereco() throws InterruptedException{
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.ENDERECO);
        paginaConta.removerNovoEndereco();
    }
}