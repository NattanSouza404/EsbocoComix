package com.esboco_comix.dto;

import java.util.List;

import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCadastrarCliente {
    private Cliente cliente;
    private List<Endereco> enderecos;
    private List<CartaoCredito> cartoesCredito;

    private String senhaNova;
    private String senhaConfirmacao;
}
