package com.esboco_comix.backend.validador.cupom;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.validador.impl.cupom.CupomValidador;

public class CupomValidadorTest {
    
    @ParameterizedTest
    @MethodSource("provideCuponsInativos")
    public void validarCuponsInvalidos(Cupom cupom) {
        CupomValidador validador = new CupomValidador();

        assertThrows(
            IllegalArgumentException.class,
            () -> {
                validador.validar(cupom);
            }
        );
    }

    @Test
    public void validarCuponsValidos() {
        CupomValidador validador = new CupomValidador();

        Cupom cupomValido = new Cupom();
        cupomValido.setValor(20);
        cupomValido.setPromocional(true);
        cupomValido.setTroca(false);

        validador.validar(cupomValido);
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
