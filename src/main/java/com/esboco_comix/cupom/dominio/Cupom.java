package com.esboco_comix.cupom.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cupom {
    private int id;
    private double valor;

    @JsonProperty("isPromocional")
    private boolean isPromocional;

    @JsonProperty("isTroca")
    private boolean isTroca;

    @JsonProperty("isAtivo")
    private boolean isAtivo;

    private int idCliente;

    @Builder 
    public Cupom(
        int id,
        double valor,
        boolean isPromocional,
        boolean isTroca,
        boolean isAtivo,
        int idCliente
    ){
        if (valor <= 0){
            throw new IllegalArgumentException("Cupom deve ter valor maior que 0!");
        }

        if (isPromocional == isTroca) {
            throw new IllegalArgumentException(
                "Cupom deve ser promocional OU de troca!"
            );
        }

        this.id = id;
        this.valor = valor;
        this.isPromocional = isPromocional;
        this.isTroca = isTroca;
        this.isAtivo = isAtivo;
        this.idCliente = idCliente;
    }

    public static Cupom gerarCupomTroca(int idCliente, double valor) {
        return Cupom.builder()
            .isAtivo(true)
            .idCliente(idCliente)
            .isTroca(true)
            .isPromocional(false)
            .valor(valor)
        .build();
    }
}
