package com.esboco_comix.cliente.dominio.entidades;

import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
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
    private Boolean isAtivo = true;

    private int idCliente;
    private int idBandeiraCartao;

    @Builder
    public CartaoCredito(
        int id,
        String numero,
        String nomeImpresso,
        String codigoSeguranca,
        boolean isPreferencial,

        BandeiraCartao bandeiraCartao,
        Boolean isAtivo,

        int idCliente,
        int idBandeiraCartao
    ){
        validarNomeImpresso(nomeImpresso);
        validarNumero(numero);
        validarCodigoSeguranca(codigoSeguranca);
        validarBandeira(bandeiraCartao);

        this.id = id;
        this.numero = numero;
        this.nomeImpresso = nomeImpresso;
        this.bandeiraCartao = bandeiraCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.isPreferencial = isPreferencial;

        this.idCliente = idCliente;
        this.idBandeiraCartao = idBandeiraCartao;
    }

    public void atualizar(
        String numero,
        String nomeImpresso,
        BandeiraCartao bandeiraCartao,
        String codigoSeguranca,
        boolean isPreferencial
    ){
        validarNomeImpresso(nomeImpresso);
        validarNumero(numero);
        validarCodigoSeguranca(codigoSeguranca);
        validarBandeira(bandeiraCartao);

        this.numero = numero;
        this.nomeImpresso = nomeImpresso;
        this.bandeiraCartao = bandeiraCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.isPreferencial = isPreferencial;
    }

    private void validarNomeImpresso(String nomeImpresso){
        if (nomeImpresso == null || nomeImpresso.isBlank()) {
            throw new IllegalArgumentException("Nome impresso do cartão não pode ser nulo ou vazio!");
        }
    }

    private void validarNumero(String numero){
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número do cartão não pode ser nulo ou vazio!");
        }

        if (numero.length() != 16){
            throw new IllegalArgumentException("Número do cartão deve ter 16 caracteres!");
        }

        if (!numero.matches("\\d+")){
            throw new IllegalArgumentException("Número do cartão só pode conter números!");
        }
    }

    private void validarCodigoSeguranca(String codigoSeguranca){
        if (codigoSeguranca == null || codigoSeguranca.isBlank()) {
            throw new IllegalArgumentException("Código de segurança do cartão não pode ser nulo ou vazio!");
        }

        if (!codigoSeguranca.matches("\\d+")){
            throw new IllegalArgumentException("Código de segurança do cartão só pode conter números!");
        }

        if (codigoSeguranca.length() != 3){
            throw new IllegalArgumentException("Código de segurança do cartão deve ter 3 caracteres!");
        }

        this.codigoSeguranca = codigoSeguranca;
    }

    private void validarBandeira(BandeiraCartao bandeiraCartao){
        if (bandeiraCartao == null) {
            throw new IllegalArgumentException("Bandeira do cartão não pode ser nula!");
        }
    }
}
