package com.fatec.controller;

import java.util.List;

import com.fatec.model.entidades.Cliente;
import com.fatec.model.entidades.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCadastrarCliente {
    private Cliente cliente;
    private List<Endereco> enderecos;
}
