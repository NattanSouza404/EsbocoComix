package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.dto.FiltrarClienteDTO;
import com.esboco_comix.webapp.paginas.admin.clientes.PaginaClientes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConsultarClientes {
    private PaginaClientes paginaClientes;

    public ConsultarClientes(WebDriver driver, WebDriverWait wait) throws Exception {
        paginaClientes = new PaginaClientes(driver, wait);
    }

    public void consultarPorFiltro(FiltrarClienteDTO filtro) throws Exception {
        paginaClientes.abrir();
        paginaClientes.consultarPorFiltro(filtro);
    }

    public void visualizarPedidosCliente(int index) throws InterruptedException{
        paginaClientes.abrir();
        paginaClientes.visualizarPedidosCliente(index);
    }

}
