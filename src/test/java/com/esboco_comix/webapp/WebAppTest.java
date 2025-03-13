package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.cadastrar_cliente.PaginaCadastrar;

public class WebAppTest {

    @Test
    public void teste() {
        PaginaCadastrar p = new PaginaCadastrar();
        Cliente c = novoCliente();
        
        try {
            p.preencherCliente(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cliente novoCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("AA");
        cliente.setCpf("aaa");
        return cliente;
    }
}
