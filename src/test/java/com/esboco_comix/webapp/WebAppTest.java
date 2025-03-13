package com.esboco_comix.webapp;

import org.junit.Test;

import com.esboco_comix.model.entidades.Cliente;

public class WebAppTest {

    @Test
    public void teste() {
        try {
            PaginaCadastrar p = new PaginaCadastrar();
            Cliente c = new Cliente();
            c.setNome("AA");
            
            p.preencherCliente(c);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        
    }   
}
