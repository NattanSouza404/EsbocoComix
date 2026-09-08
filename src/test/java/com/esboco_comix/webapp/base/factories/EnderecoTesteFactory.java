package com.esboco_comix.webapp.base.factories;

import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.enuns.TipoLogradouro;
import com.esboco_comix.cliente.dominio.enuns.TipoResidencial;
import com.esboco_comix.cliente.dominio.value_objects.Cep;

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
        e.setCep(new Cep("22222222"));
        e.setBairro("Brilhante");
        e.setCidade("Ficticialândia");
        e.setEstado("SP");
        e.setPais("Brasil");
        
        e.setObservacoes("Perto do prédio J");

        enderecos.add(e);

        return enderecos;
    }
}
