package com.esboco_comix.service.validador;

public class AbstractValidador {

    public void validarAtributoObrigatorio(Object atributo, String nome) throws Exception{
        if (atributo == null){
            throw new Exception(nome+" é obrigatório!");
        }
    }

    public void validarAtributoObrigatorio(String atributo, String nome) throws Exception{
        if (atributo == null){
            throw new Exception(nome+" é obrigatório!");
        }

        if (atributo.isEmpty()){
            throw new Exception(nome+" é obrigatório!");
        }
    }

    public void validarSeApenasNumeros(String atributo, String nome) throws Exception{
        if (!atributo.matches("\\d+")){
            throw new Exception(nome+" só pode conter números!");
        }
    }

}
