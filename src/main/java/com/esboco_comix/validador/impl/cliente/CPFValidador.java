package com.esboco_comix.validador.impl.cliente;

import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class CPFValidador extends AbstractValidador implements IValidador<String> {

    @Override
    public void validar(String cpf) {
        validarAtributoObrigatorio(cpf, "CPF");

        validarSeApenasNumeros(cpf, "CPF");

        if (cpf.length() != 11){
            throw new IllegalArgumentException("CPF deve ser composto por 11 caracteres!");
        }
 
    }
    
}
