package com.esboco_comix.validador.impl.endereco;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class EnderecoValidador extends AbstractValidador implements IValidador<Endereco> {
    @Override
    public void validar(Endereco endereco) {
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
            throw new IllegalArgumentException("Obrigatório informar se o endereço é residencial ou não!");
        }

        if (endereco.getIsEntrega() == null){
            throw new IllegalArgumentException("Obrigatório informar se o endereço é de entrega ou não!");
        }
        
        if (endereco.getIsCobranca() == null){
            throw new IllegalArgumentException("Obrigatório informar se o endereço é de cobrança ou não!");
        }

        validarSeApenasNumeros(endereco.getCep(), "CEP");
    }
}
