package com.esboco_comix.backend.cupom.dominio;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.cupom.dominio.Cupom;

public class CupomTest {
    
    @ParameterizedTest
    @MethodSource("provideCuponsInvalidos")
    public void validarCuponsInvalidos(
        double valor,
        boolean isTroca,
        boolean isPromocional
    ) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Cupom.builder()
                    .valor(valor)
                    .isTroca(isTroca)
                    .isPromocional(isPromocional)
                .build();
            }
        );
    }

    @Test
    public void validarCuponsValidos() {
        Cupom.builder()
            .valor(20)
            .isPromocional(true)
            .isTroca(false)
        .build();
    }

    public static Stream<Arguments> provideCuponsInvalidos(){
        return Stream.of(
            Arguments.of(-9, true, true),
            Arguments.of(20, true, true),
            Arguments.of(20, false, false)
        );
    }
}
