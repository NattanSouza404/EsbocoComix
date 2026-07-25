package com.esboco_comix.backend.model.value_objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.esboco_comix.model.value_objects.Cep;

public class CEPTest {

    @ParameterizedTest
    @MethodSource("provideCepsValidos")
    public void validarCepsValidos(String cep) {
        new Cep(cep);
    }

    @ParameterizedTest
    @MethodSource("provideCepsInvalidos")
    public void validarCepsInvalidos(String cep) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Cep(cep);
            }
        );
    }

    public static Stream<Arguments> provideCepsValidos(){
        return Stream.of(
            Arguments.of("12345678")
        );
    }

    public static Stream<Arguments> provideCepsInvalidos(){
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("2342222222222222"),
            Arguments.of("158 8 82"),
            Arguments.of("158 A 3d")
        );
    }
}
