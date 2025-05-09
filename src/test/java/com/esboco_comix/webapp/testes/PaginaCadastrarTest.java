package com.esboco_comix.webapp.testes;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.base.factories.CadastrarClienteFactory;
import com.esboco_comix.webapp.casos_de_uso.CadastrarCliente;
import org.junit.Test;

public class PaginaCadastrarTest extends BaseTest {

    @Test
    public void cadastrarCliente() {
        try {
            CadastrarCliente fluxo = new CadastrarCliente(driver, wait);
            fluxo.abrir();

            CadastrarClienteDTO pedido = CadastrarClienteFactory.criar();

            fluxo.preencherCliente(pedido);
            fluxo.preencherEnderecos(pedido.getEnderecos());

            fluxo.enviarCadastro();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
