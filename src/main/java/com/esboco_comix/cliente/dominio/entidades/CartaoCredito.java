package com.esboco_comix.cliente.dominio.entidades;

import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoCredito {
    private int id;
    private String numero;
    private String nomeImpresso;
    private String codigoSeguranca;

    @JsonProperty("isPreferencial")
    private boolean isPreferencial;

    private BandeiraCartao bandeiraCartao;

    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private int idCliente;
    private int idBandeiraCartao;

    public void validar(){
        if (nomeImpresso == null || nomeImpresso.isBlank()) {
            throw new IllegalArgumentException("Nome impresso do cartão não pode ser nulo ou vazio!");
        }

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número do cartão não pode ser nulo ou vazio!");
        }

        if (numero.length() != 16){
            throw new IllegalArgumentException("Número do cartão deve ter 16 caracteres!");
        }

        if (!numero.matches("\\d+")){
            throw new IllegalArgumentException("Número do cartão só pode conter números!");
        }

        if (codigoSeguranca == null || codigoSeguranca.isBlank()) {
            throw new IllegalArgumentException("Código de segurança do cartão não pode ser nulo ou vazio!");
        }

        if (!codigoSeguranca.matches("\\d+")){
            throw new IllegalArgumentException("Código de segurança do cartão só pode conter números!");
        }

        if (codigoSeguranca.length() != 3){
            throw new IllegalArgumentException("Código de segurança do cartão deve ter 3 caracteres!");
        }

        if (!codigoSeguranca.matches("\\d+")){
            throw new IllegalArgumentException("Código de segurança do cartão só pode conter números!");
        }

        if (bandeiraCartao == null) {
            throw new IllegalArgumentException("Bandeira do cartão não pode ser nula!");
        }
    }
}
