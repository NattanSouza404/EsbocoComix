package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.webapp.paginas.admin.clientes.PaginaClientes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FiltrarClientes {
    private PaginaClientes paginaClientes;

    public FiltrarClientes(WebDriver driver, WebDriverWait wait) throws Exception {
        paginaClientes = new PaginaClientes(driver, wait);
    }

    public void abrirPaginaClientes() {
        paginaClientes.abrir();
    }
    
    public void adicionarFiltro(String name, String valor) throws InterruptedException {
        paginaClientes.adicionarFiltro(name, valor);
    }

    public void consultarPorFiltro() throws Exception {
        paginaClientes.consultarPorFiltro();
    }

    public void resetarFiltros(){
        paginaClientes.resetarFiltros();
    }

}
