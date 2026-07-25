package com.esboco_comix.backend.pedido.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.esboco_comix.pedido.dominio.ItemPedido;

public class ItemPedidoTest {

    @Test
    public void validarCriacaoItemPedido() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemPedido.builder()
                .quantidade(0)
                .preco(10.0)
            .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ItemPedido.builder()
                .quantidade(-1)
                .preco(10.0)
            .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ItemPedido.builder()
                .quantidade(2)
                .preco(-2.0)
            .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ItemPedido.builder()
                .quantidade(2)
                .preco(0)
            .build();
        });
    }

        @Test
    public void validarAlterarPreco() {
        ItemPedido itemPedido = ItemPedido.builder()
            .quantidade(1)
            .preco(10.0)
        .build();

        itemPedido.alterarPreco(20.0);
        itemPedido.alterarPreco(10.0);
        itemPedido.alterarPreco(12.5);
        
        assertThrows(IllegalArgumentException.class, () -> {
            itemPedido.alterarPreco(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            itemPedido.alterarPreco(0.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            itemPedido.alterarPreco(-1.0);
        });
    }

    @Test
    public void validarCalcularValor() {
        ItemPedido itemPedido = ItemPedido.builder()
            .quantidade(2)
            .preco(10.0)
        .build();

        assertEquals(20.0, itemPedido.calcularValor(), 0.001);

        ItemPedido itemPedido2 = ItemPedido.builder()
            .quantidade(3)
            .preco(15.0)
        .build();

        assertEquals(45.0, itemPedido2.calcularValor(), 0.001);
    }

}
