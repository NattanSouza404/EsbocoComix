package com.esboco_comix.backend.cliente.dominio.value_objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.cliente.dominio.value_objects.Senha;

public class SenhaTest {

    @ParameterizedTest
    @MethodSource("provideSenhasValidas")
    public void validarSenhasValidas(String senha) {
        new Senha(senha);
    }

    @ParameterizedTest
    @MethodSource("provideSenhasInvalidas")
    public void validarSenhasInvalidas(String senha) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Senha(senha);
            }
        );
    }

    public static Stream<Arguments> provideSenhasValidas(){
        return Stream.of(
            Arguments.of("Senha123!")
        );
    }

    public static Stream<Arguments> provideSenhasInvalidas(){
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("123456789"),
            Arguments.of("senha123!"),
            Arguments.of("SENHA123!")
        );
    }
}
