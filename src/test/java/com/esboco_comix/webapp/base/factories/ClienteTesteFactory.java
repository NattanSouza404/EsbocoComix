package com.esboco_comix.webapp.base.factories;

import java.time.LocalDate;

import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.enuns.TipoTelefone;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dominio.value_objects.Telefone;

public class ClienteTesteFactory {

    public static Cliente criar(){
        return Cliente.builder()
            .nome("Humberto Neves")
            .cpf(new Cpf("11122233344"))
            .email(new Email("humberto@email.com"))
            .dataNascimento(LocalDate.of(2013, 2, 3))
            .genero(Genero.MASCULINO)

            .telefone(
                new Telefone("11", "99999999", TipoTelefone.CELULAR)
            )
        .build();
    }
}
