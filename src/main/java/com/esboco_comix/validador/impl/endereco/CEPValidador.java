package com.esboco_comix.validador.impl.endereco;

import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class CEPValidador extends AbstractValidador implements IValidador<String> {

    @Override
    public void validar(String cep) {
        validarAtributoObrigatorio(cep, "CEP");
        validarSeApenasNumeros(cep, "CEP");

        if (cep.length() != 8){
            throw new IllegalArgumentException("CEP deve ter 8 caracteres!");
        }
    }
    
}
