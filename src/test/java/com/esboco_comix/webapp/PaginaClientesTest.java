package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.webapp.paginas.clientes.PaginaClientes;

public class PaginaClientesTest {

    @Test
    public void consultarPorFiltro(){
        PaginaClientes paginaClientes = new PaginaClientes();

        try {
            paginaClientes.consultarPorFiltro();
            paginaClientes.fechar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
