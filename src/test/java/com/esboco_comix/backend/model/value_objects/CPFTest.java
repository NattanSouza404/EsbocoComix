package com.esboco_comix.backend.model.value_objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.model.value_objects.Cpf;

public class CPFTest {

    @ParameterizedTest
    @MethodSource("provideCpfsValidos")
    public void validarCpfsValidos(String cpf) {
        new Cpf(cpf);
    }

    @ParameterizedTest
    @MethodSource("provideCpfsInvalidos")
    public void validarCpfsInvalidos(String cpf) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Cpf(cpf);
            }
        );
    }

    public static Stream<Arguments> provideCpfsValidos(){
        return Stream.of(
            Arguments.of("15815815822")
        );
    }

    public static Stream<Arguments> provideCpfsInvalidos(){
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("234"),
            Arguments.of("1581 815 22")
        );
    }
}
