package com.esboco_comix.backend.model.entidades;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.model.entidades.Cupom;

public class CupomTest {
    
    @ParameterizedTest
    @MethodSource("provideCuponsInativos")
    public void validarCuponsInvalidos(Cupom cupom) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                cupom.validar();
            }
        );
    }

    @Test
    public void validarCuponsValidos() {
        Cupom cupomValido = new Cupom();
        cupomValido.setValor(20);
        cupomValido.setPromocional(true);
        cupomValido.setTroca(false);

        cupomValido.validar();
    }

    public static Stream<Arguments> provideCuponsInativos(){
        Cupom cupomValorNegativo = new Cupom();
        cupomValorNegativo.setPromocional(true);
        cupomValorNegativo.setTroca(true);
        cupomValorNegativo.setValor(-9);

        Cupom cupomDoisTipos = new Cupom();
        cupomDoisTipos.setValor(20);
        cupomDoisTipos.setPromocional(true);
        cupomDoisTipos.setTroca(true);

        Cupom cupomNenhumTipo = new Cupom();
        cupomNenhumTipo.setValor(20);
        cupomNenhumTipo.setPromocional(false);
        cupomNenhumTipo.setTroca(false);

        return Stream.of(
            Arguments.of(cupomValorNegativo),
            Arguments.of(cupomDoisTipos),
            Arguments.of(cupomNenhumTipo)
        );
    }
}
