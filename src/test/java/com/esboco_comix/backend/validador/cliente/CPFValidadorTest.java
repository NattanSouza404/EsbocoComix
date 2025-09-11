package com.esboco_comix.backend.validador.cliente;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.esboco_comix.validador.impl.cliente.CPFValidador;

public class CPFValidadorTest {

    @Test
    public void validarCPF(){
        CPFValidador cpfValidador = new CPFValidador();

        cpfValidador.validar("15815815822");

        assertThrows(IllegalArgumentException.class, () -> {
            cpfValidador.validar(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cpfValidador.validar("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cpfValidador.validar("234");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cpfValidador.validar("1581 815 22");
        });
    }
}
