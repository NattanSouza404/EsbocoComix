package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.paginas.cliente.cadastrar.PaginaCadastrar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CadastrarCliente {
    private PaginaCadastrar paginaCadastrar;

    public CadastrarCliente(WebDriver driver, WebDriverWait wait) throws Exception {
        paginaCadastrar = new PaginaCadastrar(driver, wait);
    }

    public void abrir(){
        paginaCadastrar.abrir();
    }

    public void preencherCliente(CadastrarClienteDTO dto) throws InterruptedException {
        paginaCadastrar.preencherCliente(dto);
    }

    public void preencherEnderecos(List<Endereco> enderecos) throws InterruptedException {
        paginaCadastrar.preencherEnderecos(enderecos);
    }

    public void enviarCadastro() throws InterruptedException {
        paginaCadastrar.enviarCadastro();
    }
}
