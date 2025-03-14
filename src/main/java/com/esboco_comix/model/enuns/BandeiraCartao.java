package com.esboco_comix.model.enuns;

import lombok.Getter;

@Getter
public enum BandeiraCartao {
    MASTERCARD("Mastercard"),
    VISA("Visa"),
    AMERICAN_EXPRESS("American Express"),
    HIPERCARD("Hipercard"),
    ELO("Elo");
    
    private String nome;
    
    BandeiraCartao(String nome) {
        this.nome = nome;
    }

    public static BandeiraCartao getBandeiraByNome(String nome) throws Exception{
        BandeiraCartao[] bandeiras = BandeiraCartao.values();

        for (BandeiraCartao b : bandeiras) {
            if (nome.equals(b.getNome())){
                return b;
            }
        }

        throw new Exception("Bandeira de cartão de crédito não encontrada!");
    }
}
