package com.esboco_comix.service.validador.impl;

import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;

public class EmailValidador extends AbstractValidador implements IValidador<String> {
    @Override
    public void validar(String email) {
        String parteLocal = "^[A-Za-z0-9+_.-]+";
        String parteDominio = "@[A-Za-z0-9.-]+";
        String sufixoDominio = "\\.[A-Za-z]{2,}$";

        String regexEmail = parteLocal + parteDominio + sufixoDominio;

        if (!email.matches(regexEmail)){
            throw new IllegalArgumentException("Email inválido!");
        }
    }
}
