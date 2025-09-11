package com.esboco_comix.backend.validador.endereco;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.esboco_comix.validador.impl.endereco.CEPValidador;

public class CEPValidadorTest {
    
    @Test
    public void validarCEP(){
        CEPValidador cepValidador = new CEPValidador();

        cepValidador.validar("12345678");

        assertThrows(IllegalArgumentException.class, () -> {
            cepValidador.validar(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cepValidador.validar("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cepValidador.validar("234");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cepValidador.validar("158 8 82");
        });
    }
}
