package com.esboco_comix.service.validador.impl;

import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;

public class CupomValidador extends AbstractValidador implements IValidador<Cupom> {
    @Override
    public void validar(Cupom cupom) throws Exception {
        if (cupom.getValor() < 0){
            throw new Exception("Cupom não pode ter valor negativo!");
        }

        if (cupom.isPromocional() && cupom.isTroca()){
            throw new Exception("Cupom não pode ser promocional e de troca!");
        }

        if (!cupom.isPromocional() && !cupom.isTroca()){
            throw new Exception("Cupom deve ser de troca ou promocional!");
        }
    }
}
