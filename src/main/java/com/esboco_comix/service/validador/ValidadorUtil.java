package com.esboco_comix.service.validador;

public class ValidadorUtil {

    public static void validarAtributoObrigatorio(Object atributo, String nome) throws Exception{
        if (atributo == null){
            throw new Exception(nome+" é obrigatório!");
        }
    }

    public static void validarAtributoObrigatorio(String atributo, String nome) throws Exception{
        if (atributo == null){
            throw new Exception(nome+" é obrigatório!");
        }

        if (atributo.isEmpty()){
            throw new Exception(nome+" é obrigatório!");
        }
    }

}
