package com.esboco_comix.service.validador;

import static com.esboco_comix.service.validador.ValidadorUtil.*;

import com.esboco_comix.model.entidades.CartaoCredito;

public class CartaoCreditoValidador {
    public void validar(CartaoCredito c) throws Exception{
        validarAtributoObrigatorio(c.getNomeImpresso(), "Nome impresso do cartão");
        validarAtributoObrigatorio(c.getBandeiraCartao(), "Bandeira do cartão");
        validarAtributoObrigatorio(c.getNumero(), "Número do cartão");
        validarAtributoObrigatorio(c.getCodigoSeguranca(), "Código de segurança do cartão");

        validarSeApenasNumeros(c.getNumero(), "Número do cartão");
        validarSeApenasNumeros(c.getCodigoSeguranca(), "Código de segurança do cartão");
    }
}
