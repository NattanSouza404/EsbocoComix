package com.esboco_comix.service.validador.impl;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;

public class EnderecoValidador extends AbstractValidador implements IValidador<Endereco> {
    @Override
    public void validar(Endereco endereco) throws Exception{
        validarAtributoObrigatorio(endereco.getFraseCurta(), "Frase curta");
        validarAtributoObrigatorio(endereco.getNumero(), "Número do endereço");
        validarAtributoObrigatorio(endereco.getCep(), "CEP");
        validarAtributoObrigatorio(endereco.getLogradouro(), "Logradouro");
        validarAtributoObrigatorio(endereco.getTipoLogradouro(), "Tipo Logradouro");
        validarAtributoObrigatorio(endereco.getTipoResidencial(), "Tipo de residência");
        validarAtributoObrigatorio(endereco.getBairro(), "Bairro");
        validarAtributoObrigatorio(endereco.getCidade(), "Cidade");
        validarAtributoObrigatorio(endereco.getEstado(), "Estado");
        validarAtributoObrigatorio(endereco.getPais(), "País");

        if (endereco.getIsResidencial() == null){
            throw new Exception("Obrigatório informar se o endereço é residencial ou não!");
        }

        if (endereco.getIsEntrega() == null){
            throw new Exception("Obrigatório informar se o endereço é de entrega ou não!");
        }
        
        if (endereco.getIsCobranca() == null){
            throw new Exception("Obrigatório informar se o endereço é de cobrança ou não!");
        }

        validarSeApenasNumeros(endereco.getCep(), "CEP");
    }
}
