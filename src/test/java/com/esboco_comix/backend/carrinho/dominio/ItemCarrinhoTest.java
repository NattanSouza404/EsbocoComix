package com.esboco_comix.backend.carrinho.dominio;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.carrinho.dominio.ItemCarrinho;

public class ItemCarrinhoTest {
    
    @ParameterizedTest
    @MethodSource("provideItemCarrinhoInvalido")
    public void validarItensCarrinhoInvalidos(
        int quantidade
    ) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                ItemCarrinho.builder()
                    .quantidade(quantidade)
                .build();
            }
        );
    }

    @Test
    public void validarItensCarrinhoVálidos() {
        ItemCarrinho.builder()
            .quantidade(20)
        .build();
    }

    public static Stream<Arguments> provideItemCarrinhoInvalido(){
        return Stream.of(
            Arguments.of(-9),
            Arguments.of(0),
            Arguments.of(-100)
        );
    }
}
