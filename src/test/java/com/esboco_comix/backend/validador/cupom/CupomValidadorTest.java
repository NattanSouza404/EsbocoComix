package com.esboco_comix.backend.validador.cupom;

import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.validador.impl.cupom.CupomValidador;

public class CupomValidadorTest {
    
    @Test
    public void validarValorCupom(){
        CupomValidador validador = new CupomValidador();
        
        Cupom cupom = new Cupom();
        cupom.setPromocional(true);

        cupom.setValor(0);

        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(cupom);
        });

        cupom.setValor(-10);

        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(cupom);
        });

        cupom.setValor(10);

        validador.validar(cupom);

        cupom.setValor(30);

        validador.validar(cupom);

        //TODO: adicionar um limite máximo para o valor de um cupom
    }

    @Test
    public void validarStatusCupom(){
        CupomValidador validador = new CupomValidador();

        Cupom cupom = new Cupom();
        cupom.setValor(10);
        cupom.setPromocional(true);
        cupom.setTroca(true);

        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(cupom);
        });

        cupom.setPromocional(false);
        cupom.setTroca(false);

        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(cupom);
        });

        cupom.setPromocional(true);
        cupom.setTroca(false);

        validador.validar(cupom);

        cupom.setPromocional(false);
        cupom.setTroca(true);

        validador.validar(cupom);
    }
}
