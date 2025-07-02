package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FluxoCadastroCartao {
    private final PaginaConta paginaConta;

    public FluxoCadastroCartao(WebDriver driver, WebDriverWait wait){
        paginaConta = new PaginaConta(driver, wait);
    }

    public void adicionarCartao(CartaoCredito c) throws Exception {
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);

        ModaisConta modal = ModaisConta.ADICIONAR_CARTAO_CREDITO;
        paginaConta.abrirModal(modal);

        paginaConta.preencherInputModal(modal, "numero", c.getNumero());
        paginaConta.preencherInputModal(modal, "nomeImpresso", c.getNomeImpresso());
        paginaConta.preencherInputModal(modal, "codigoSeguranca", c.getCodigoSeguranca());
        paginaConta.preencherInputSelectModal(modal, "bandeiraCartao", c.getBandeiraCartao().name());

        paginaConta.enviar(modal);
    }

    public void alterarCartao(CartaoCredito c) throws InterruptedException{
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);

        ModaisConta modal = ModaisConta.ALTERAR_CARTAO_CREDITO;
        paginaConta.abrirModal(modal);

        paginaConta.preencherInputModal(modal, "codigoSeguranca", c.getCodigoSeguranca());

        paginaConta.enviar(modal);
    }

    public void deletarNovoCartao() throws InterruptedException {
        paginaConta.logar();

        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);

        paginaConta.removerNovoCartao();
    }
}
