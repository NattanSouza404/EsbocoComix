package com.esboco_comix.backend.validador.cliente;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.validador.impl.cliente.CPFValidador;

public class CPFValidadorTest {

    @org.junit.jupiter.api.Test
    public void validarCPF(){
        CPFValidador cpfValidador = new CPFValidador();

        Cliente cliente = new Cliente();
        cliente.setCpf("15815815822");

        cpfValidador.validar(cliente);

        assertThrows(IllegalArgumentException.class, () -> {
            cpfValidador.validar(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setCpf("");
            cpfValidador.validar(cliente);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setCpf("234");
            cpfValidador.validar(cliente);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setCpf("1581 815 22");
            cpfValidador.validar(cliente);
        });
    }
}
