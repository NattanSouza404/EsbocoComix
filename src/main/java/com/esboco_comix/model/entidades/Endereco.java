package com.esboco_comix.model.entidades;

import com.esboco_comix.model.enuns.TipoLogradouro;
import com.esboco_comix.model.enuns.TipoResidencial;
import com.esboco_comix.model.value_objects.Cep;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {
    private int id;
    private String fraseCurta;
    private String logradouro;
    private TipoLogradouro tipoLogradouro;
    private TipoResidencial tipoResidencial;
    private String numero;
    private String bairro;
    private Cep cep;
    private String cidade;
    private String estado;
    private String pais;

    @JsonProperty("isResidencial")
    private Boolean isResidencial;

    @JsonProperty("isEntrega")
    private Boolean isEntrega;

    @JsonProperty("isCobranca")
    private Boolean isCobranca;

    private String observacoes;

    @JsonProperty("isAtivo")
    private Boolean isAtivo;

    private int idCliente;

    public void validar(){
        if (fraseCurta == null || fraseCurta.isBlank()) {
            throw new IllegalArgumentException("Frase curta do endereço não pode ser nula ou vazia!");
        }

        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número do endereço não pode ser nulo ou vazio!");
        }

        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro do endereço não pode ser nulo ou vazio!");
        }

        if (tipoLogradouro == null) {
            throw new IllegalArgumentException("Tipo de logradouro do endereço não pode ser nulo!");
        }

        if (tipoResidencial == null) {
            throw new IllegalArgumentException("Tipo de residência do endereço não pode ser nulo!");
        }

        if (bairro == null || bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro do endereço não pode ser nulo ou vazio!");
        }

        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade do endereço não pode ser nula ou vazia!");
        }

        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado do endereço não pode ser nulo ou vazio!");
        }

        if (pais == null || pais.isBlank()) {
            throw new IllegalArgumentException("País do endereço não pode ser nulo ou vazio!");
        }

        if (isResidencial == null){
            throw new IllegalArgumentException("Obrigatório informar se o endereço é residencial ou não!");
        }

        if (isEntrega == null){
            throw new IllegalArgumentException("Obrigatório informar se o endereço é de entrega ou não!");
        }
        
        if (isCobranca == null){
            throw new IllegalArgumentException("Obrigatório informar se o endereço é de cobrança ou não!");
        }
    }
}
