package com.esboco_comix.cliente.dto;

import com.esboco_comix.cliente.dominio.enuns.BandeiraCartao;

import lombok.Builder;

@Builder 
public record CadastrarCartaoCreditoDTO(
    String numero,
    String nomeImpresso,
    String codigoSeguranca,
    BandeiraCartao bandeiraCartao,
    boolean isPreferencial,
    int idCliente
){}
