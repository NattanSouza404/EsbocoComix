package com.esboco_comix.pedido.dto;

import java.util.List;

import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.pedido.dominio.CartaoCreditoPedido;
import com.esboco_comix.pedido.dominio.CupomPedido;

public record CriarPedidoDTO (
    int idCliente,
    Double valorFrete,

    Endereco enderecoEntrega,
    
    List<CartaoCreditoPedido> cartoesCreditoPedido,
    List<CupomPedido> cuponsPedido
){}