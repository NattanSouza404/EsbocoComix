package com.esboco_comix.webapp.testes;

import org.junit.Test;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.paginas.cliente.minhas_compras.PaginaMinhasCompras;

public class VisualizarPedidosPosVenda extends BaseTest {
    
    @Test
    public void visualizarPedidosPosVenda(){
        try {
            PaginaMinhasCompras paginaMinhasCompras = new PaginaMinhasCompras(driver, wait);

            paginaMinhasCompras.abrir();
            paginaMinhasCompras.visualizarPedidosPosVenda(0);

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
