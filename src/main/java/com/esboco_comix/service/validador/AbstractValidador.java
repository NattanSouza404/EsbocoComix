package com.esboco_comix.service.validador;

public class AbstractValidador {

    public void validarAtributoObrigatorio(Object atributo, String nome) {
        if (atributo == null){
            throw new IllegalArgumentException(nome+" é obrigatório!");
        }
    }

    public void validarAtributoObrigatorio(String atributo, String nome) {
        if (atributo == null){
            throw new IllegalArgumentException(nome+" é obrigatório!");
        }

        if (atributo.isEmpty()){
            throw new IllegalArgumentException(nome+" é obrigatório!");
        }
    }

    public void validarSeApenasNumeros(String atributo, String nome) {
        if (!atributo.matches("\\d+")){
            throw new IllegalArgumentException(nome+" só pode conter números!");
        }
    }

}
