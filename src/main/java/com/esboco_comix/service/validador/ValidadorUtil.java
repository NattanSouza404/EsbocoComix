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

    public static void validarSeApenasNumeros(String atributo, String nome) throws Exception{
        if (!atributo.matches("\\d+")){
            throw new Exception(nome+" só pode conter números!");
        }
    }

    public static void validarEmail(String email) throws Exception {
        String parteLocal = "^[A-Za-z0-9+_.-]+";
        String parteDominio = "@[A-Za-z0-9.-]+";;
        String sufixoDominio = "\\.[A-Za-z]{2,}$";

        String regexEmail = parteLocal + parteDominio + sufixoDominio;

        if (!email.matches(regexEmail)){
            throw new Exception("Email inválido!");
        }
    }

}
