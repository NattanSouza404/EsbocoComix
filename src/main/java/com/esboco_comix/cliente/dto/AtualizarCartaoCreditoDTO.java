package com.esboco_comix.cliente.dto;

import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AtualizarCartaoCreditoDTO(
    int id,
    String numero,
    String nomeImpresso,
    String codigoSeguranca,

    @JsonProperty("isPreferencial")
    boolean isPreferencial,

    BandeiraCartao bandeiraCartao
){}