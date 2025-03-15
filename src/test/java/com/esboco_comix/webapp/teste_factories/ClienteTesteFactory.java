package com.esboco_comix.webapp.teste_factories;

import java.time.LocalDate;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Telefone;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.model.enuns.TipoTelefone;

public class ClienteTesteFactory {

    public static Cliente criar(){
        Cliente cliente = new Cliente();
        cliente.setNome("Jorge dos Santos");
        cliente.setCpf("11122233344");
        cliente.setEmail("jorge@email.com");
        cliente.setDataNascimento(LocalDate.of(2013, 2, 3));
        cliente.setGenero(Genero.MASCULINO.getNome());

        Telefone t = new Telefone();
        t.setDdd("11");
        t.setNumero("99999999");
        t.setTipo(TipoTelefone.CELULAR.getNome());
        cliente.setTelefone(t);

        return cliente;
    }   
}
