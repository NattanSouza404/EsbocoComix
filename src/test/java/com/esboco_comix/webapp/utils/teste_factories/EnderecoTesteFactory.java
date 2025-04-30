package com.esboco_comix.webapp.utils.teste_factories;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.enuns.TipoLogradouro;
import com.esboco_comix.model.enuns.TipoResidencial;

public class EnderecoTesteFactory {
    public static List<Endereco> criar(){
        List<Endereco> enderecos = new ArrayList<>();

        Endereco e = new Endereco();
        e.setFraseCurta("Casa");
        
        e.setLogradouro("Limoeiro");
        e.setTipoLogradouro(TipoLogradouro.RUA);
        e.setTipoResidencial(TipoResidencial.CASA);
        e.setIsCobranca(true);
        e.setIsEntrega(true);
        e.setIsResidencial(true);
        
        e.setNumero("23");
        e.setCep("22222222");
        e.setBairro("Brilhante");
        e.setCidade("Ficticialândia");
        e.setEstado("SP");
        e.setPais("Brasil");
        
        e.setObservacoes("Perto do prédio J");

        enderecos.add(e);

        return enderecos;
    }
}
