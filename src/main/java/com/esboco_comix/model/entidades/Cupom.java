package com.esboco_comix.model.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void validar() {
        if (valor <= 0){
            throw new IllegalArgumentException("Cupom deve ter valor maior que 0!");
        }

        if (isPromocional == isTroca) {
            throw new IllegalArgumentException(
                "Cupom deve ser promocional OU de troca!"
            );
        }
    }

    public static Cupom gerarCupomTroca(int idCliente, double valor) {
        Cupom cupom = new Cupom();
        cupom.setAtivo(true);
        cupom.setIdCliente(idCliente);
        cupom.setTroca(true);
        cupom.setPromocional(false);
        cupom.setValor(valor);

        cupom.validar();

        return cupom;
    }
}
