package com.esboco_comix.controller;

import java.util.List;

import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;

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
