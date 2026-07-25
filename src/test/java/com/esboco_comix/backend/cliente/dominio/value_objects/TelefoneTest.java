package com.esboco_comix.backend.cliente.dominio.value_objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.cliente.dominio.enuns.TipoTelefone;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;

public class TelefoneTest {

    @ParameterizedTest
    @MethodSource("provideTelefonesValidos")
    public void validarTelefonesValidos(String ddd, String numero, TipoTelefone tipo) {
        new Telefone(ddd, numero, tipo);
    }

    @ParameterizedTest
    @MethodSource("provideTelefonesInvalidos")
    public void validarTelefonesInvalidos(String ddd, String numero, TipoTelefone tipo) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Telefone(ddd, numero, tipo);
            }
        );
    }

    public static Stream<Arguments> provideTelefonesValidos(){
        return Stream.of(
            Arguments.of("11", "987654321", TipoTelefone.CELULAR),
            Arguments.of("22", "87654321", TipoTelefone.CELULAR),
            Arguments.of("22 ", " 87654321", TipoTelefone.CELULAR),
            Arguments.of(" 22", "87654321 ", TipoTelefone.CELULAR),
            Arguments.of(" 22 ", " 87654321 ", TipoTelefone.CELULAR)
        );
    }

    public static Stream<Arguments> provideTelefonesInvalidos(){
        return Stream.of(
            Arguments.of((String) null, "987654321", TipoTelefone.CELULAR),
            Arguments.of("11", "", TipoTelefone.CELULAR),
            Arguments.of("11", null, TipoTelefone.CELULAR),
            Arguments.of("", "987654321", TipoTelefone.CELULAR),
            Arguments.of("11", "1234567890", TipoTelefone.CELULAR),
            Arguments.of("112", "123456789", TipoTelefone.CELULAR),
            Arguments.of("11", "123456789", null),
            Arguments.of("11", "1234567AB", TipoTelefone.FIXO),
            Arguments.of("DD", "123456789", TipoTelefone.FIXO),
            Arguments.of("11", "123", TipoTelefone.FIXO)
        );
    }
}
