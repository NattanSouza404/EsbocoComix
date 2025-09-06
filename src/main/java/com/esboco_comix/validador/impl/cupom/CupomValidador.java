package com.esboco_comix.validador.impl.cupom;

import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class CupomValidador extends AbstractValidador implements IValidador<Cupom> {
    @Override
    public void validar(Cupom cupom) {
        if (cupom.getValor() < 0){
            throw new IllegalArgumentException("Cupom não pode ter valor negativo!");
        }

        if (cupom.isPromocional() && cupom.isTroca()){
            throw new IllegalArgumentException("Cupom não pode ser promocional e de troca!");
        }

        if (!cupom.isPromocional() && !cupom.isTroca()){
            throw new IllegalArgumentException("Cupom deve ser de troca ou promocional!");
        }
    }
}
