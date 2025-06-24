package com.esboco_comix.webapp.testes.sistema_inteiro;

import org.junit.Test;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FluxoChatIA;

public class ChatIATest extends BaseTest {

    @Test
    public void enviarMensagemIA(){
        try {

            FluxoChatIA fluxoChatIA = new FluxoChatIA(driver, wait);
            fluxoChatIA.logar();

            fluxoChatIA.abrirPaginaInicial();
            fluxoChatIA.abrirChatIA();
            fluxoChatIA.enviarMensagemChatIA("Me recomende um quadrinho");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
