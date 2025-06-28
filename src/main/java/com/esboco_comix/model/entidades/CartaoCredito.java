package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.BandeiraCartao;
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
}
