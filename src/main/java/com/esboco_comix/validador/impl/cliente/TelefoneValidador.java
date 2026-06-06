package com.esboco_comix.validador.impl.cliente;

import com.esboco_comix.model.entidades.Telefone;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class TelefoneValidador extends AbstractValidador implements IValidador<Telefone> {

    @Override
    public void validar(Telefone telefone) {
        validarAtributoObrigatorio(telefone, "Telefone");

        validarAtributoObrigatorio(telefone.getDdd(), "DDD");
        validarSeApenasNumeros(telefone.getDdd(), "DDD");

        validarAtributoObrigatorio(telefone.getNumero(), "Número de telefone");
        validarSeApenasNumeros(telefone.getNumero(), "Número de telefone");

        validarAtributoObrigatorio(telefone.getTipo(), "Tipo de telefone");
    }
    
}
