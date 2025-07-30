package com.esboco_comix.service.validador.impl;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;

public class CartaoCreditoValidador extends AbstractValidador implements IValidador<CartaoCredito> {
    @Override
    public void validar(CartaoCredito c) {
        validarAtributoObrigatorio(c.getNomeImpresso(), "Nome impresso do cartão");
        validarAtributoObrigatorio(c.getBandeiraCartao(), "Bandeira do cartão");
        validarAtributoObrigatorio(c.getNumero(), "Número do cartão");
        validarAtributoObrigatorio(c.getCodigoSeguranca(), "Código de segurança do cartão");

        validarSeApenasNumeros(c.getNumero(), "Número do cartão");
        validarSeApenasNumeros(c.getCodigoSeguranca(), "Código de segurança do cartão");
    }
}
