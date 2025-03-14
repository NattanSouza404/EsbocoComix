package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.paginas.cadastrar_cliente.PaginaCadastrar;

public class WebAppTest {

    @Test
    public void teste() {
        PaginaCadastrar p = new PaginaCadastrar();
        Cliente c = ClienteTesteFactory.criar();
        
        try {
            p.preencherCliente(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
