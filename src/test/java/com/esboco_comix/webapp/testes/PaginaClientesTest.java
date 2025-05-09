package com.esboco_comix.webapp.testes;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FiltrarClientes;
import org.junit.Test;

public class PaginaClientesTest extends BaseTest {

    @Test
    public void consultarPorFiltro(){

        try {
            FiltrarClientes fluxo = new FiltrarClientes(driver, wait);

            fluxo.abrirPaginaClientes();
            fluxo.adicionarFiltro("genero", "Feminino");
            fluxo.consultarPorFiltro();

            fluxo.resetarFiltros();

            fluxo.adicionarFiltro("nome", "Jorge");
            fluxo.consultarPorFiltro();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
