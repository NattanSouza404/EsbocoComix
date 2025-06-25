package com.esboco_comix.webapp.casos_de_uso;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.index.PaginaIndex;

public class FluxoChatIA {
    private PaginaIndex paginaIndex;
    private PaginaConta paginaConta;

    public FluxoChatIA(WebDriver driver, WebDriverWait wait){
        paginaIndex = new PaginaIndex(driver, wait);
        paginaConta = new PaginaConta(driver, wait);
    }

    public void logar() throws InterruptedException{
        paginaConta.logar();
    }

    public void abrirPaginaInicial() throws InterruptedException {
        paginaIndex.abrir();
    }

    public void abrirChatIA() throws InterruptedException {
        paginaIndex.abrirChatIA();
    }

    public void enviarMensagemChatIA(String mensagem) throws InterruptedException {
        paginaIndex.enviarMensagemChatIA(mensagem);
    }
}
