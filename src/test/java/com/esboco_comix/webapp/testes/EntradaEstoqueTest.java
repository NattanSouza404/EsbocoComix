package com.esboco_comix.webapp.testes;

import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.webapp.casos_de_uso.RealizarEntradaEstoque;
import org.junit.Test;

import com.esboco_comix.webapp.base.BaseTest;

import java.time.LocalDateTime;

public class EntradaEstoqueTest extends BaseTest {
    
    @Test
    public void realizarEntradaEstoque(){
        try {
            RealizarEntradaEstoque fluxo = new RealizarEntradaEstoque(driver, wait);

            fluxo.abrirPaginaEstoque();

            EntradaEstoque entradaEstoque = new EntradaEstoque();
            entradaEstoque.setQuantidade(10);
            entradaEstoque.setFornecedor("Grande Loja do Norte");
            entradaEstoque.setValorCusto(30.0);
            entradaEstoque.setDataEntrada(
                    LocalDateTime.of(2025, 1, 30, 12, 30, 0)
            );

            fluxo.realizarEntradaEstoque(0, entradaEstoque);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
