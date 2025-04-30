package com.esboco_comix.webapp.crud_cliente;

import org.junit.Test;

import com.esboco_comix.webapp.crud_cliente.cadastrar_cliente.PaginaClientes;

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
