package com.esboco_comix.cliente.dto;

import java.time.LocalDate;

import com.esboco_comix.cliente.dominio.enuns.Genero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarClienteDTO {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Genero genero;
    private String email;
    private Integer ranking;
    private Boolean isAtivo;
}
