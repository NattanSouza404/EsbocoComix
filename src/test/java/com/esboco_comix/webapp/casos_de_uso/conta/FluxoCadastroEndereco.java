package com.esboco_comix.webapp.casos_de_uso.conta;

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
        paginaConta.abrirModal(ModaisConta.ADICIONAR_ENDERECO);

        paginaConta.preencherInputModal(ModaisConta.ADICIONAR_ENDERECO, "fraseCurta", e.getFraseCurta());

        paginaConta.enviar(ModaisConta.ADICIONAR_ENDERECO);
    }

    public void alterarNovoEndereco(Endereco e) throws InterruptedException{
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.ENDERECO);
        paginaConta.abrirModalAtualizar(ModaisConta.ALTERAR_ENDERECO);

        paginaConta.preencherInputModal(ModaisConta.ALTERAR_ENDERECO, "fraseCurta", e.getFraseCurta());

        paginaConta.enviar(ModaisConta.ALTERAR_ENDERECO);
    }

    public void deletarNovoEndereco() throws InterruptedException{
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.ENDERECO);
        paginaConta.removerNovoEndereco();
    }
}
