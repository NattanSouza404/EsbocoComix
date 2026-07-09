package com.esboco_comix.dto;

import java.util.List;

import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.Endereco;

public record CriarPedidoDTO (
    int idCliente,
    Double valorFrete,

    Endereco enderecoEntrega,
    
    List<CartaoCreditoPedido> cartoesCreditoPedido,
    List<CupomPedido> cuponsPedido
){}