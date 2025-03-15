package com.esboco_comix.webapp;

import java.util.List;

import org.junit.Test;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.webapp.paginas.cadastrar_cliente.PaginaCadastrar;
import com.esboco_comix.webapp.teste_factories.ClienteTesteFactory;
import com.esboco_comix.webapp.teste_factories.EnderecoTesteFactory;

public class WebAppTest {

    @Test
    public void testeCadastro() {
        PaginaCadastrar p = new PaginaCadastrar();

        Cliente c = ClienteTesteFactory.criar();
        List<Endereco> enderecos = EnderecoTesteFactory.criar();
        
        try {
            p.preencherCliente(c);
            p.preencherEnderecos(enderecos);

            p.enviarCadastro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
