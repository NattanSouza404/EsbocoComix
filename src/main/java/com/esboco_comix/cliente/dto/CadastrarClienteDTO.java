package com.esboco_comix.cliente.dto;

import java.util.List;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.entidades.Endereco;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CadastrarClienteDTO {
    private Cliente cliente;
    private List<Endereco> enderecos;
    private List<CartaoCredito> cartoesCredito;

    private String senhaNova;
    private String senhaConfirmacao;
}
