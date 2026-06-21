package com.esboco_comix.webapp.base.factories;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.model.enuns.TipoTelefone;
import com.esboco_comix.model.value_objects.Cpf;
import com.esboco_comix.model.value_objects.Email;
import com.esboco_comix.model.value_objects.Telefone;

import java.time.LocalDate;

public class ClienteTesteFactory {

    public static Cliente criar(){
        Cliente cliente = new Cliente();
        cliente.setNome("Humberto Neves");
        cliente.setCpf(new Cpf("11122233344"));
        cliente.setEmail(new Email("humberto@email.com"));
        cliente.setDataNascimento(LocalDate.of(2013, 2, 3));
        cliente.setGenero(Genero.MASCULINO);

        cliente.setTelefone(
            new Telefone("11", "99999999", TipoTelefone.CELULAR)
        );

        return cliente;
    }
}
