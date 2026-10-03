package com.esboco_comix.pedido.service;

import java.util.List;

import com.esboco_comix.carrinho.dominio.Carrinho;
import com.esboco_comix.cliente.dao.CartaoCreditoDAO;
import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.core.dao.TransactionExecutor;
import com.esboco_comix.cupom.dao.CupomDAO;
import com.esboco_comix.estoque.dao.EstoqueDAO;
import com.esboco_comix.estoque.dominio.Estoque;
import com.esboco_comix.pedido.dao.CartaoCreditoPedidoDAO;
import com.esboco_comix.pedido.dao.CupomPedidoDAO;
import com.esboco_comix.pedido.dao.ItemPedidoDAO;
import com.esboco_comix.pedido.dao.PedidoDAO;
import com.esboco_comix.pedido.dominio.CartaoCreditoPedido;
import com.esboco_comix.pedido.dominio.CupomPedido;
import com.esboco_comix.pedido.dominio.ItemPedido;
import com.esboco_comix.pedido.dominio.Pedido;
import com.esboco_comix.pedido.mapper.ItemPedidoMapper;
import com.esboco_comix.quadrinho.dto.QuadrinhoDTO;
import com.esboco_comix.quadrinho.service.QuadrinhoService;

public class FinalizarPedidoService {

    private final QuadrinhoService quadrinhoService = new QuadrinhoService();

    private final TransactionExecutor transactionManager = new TransactionExecutor();

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private final CupomDAO cupomDAO = new CupomDAO();
    private final CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    private final CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();

    private final ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();

    public Pedido executar(Pedido pedido, Carrinho carrinho) {
        if (carrinho.isVazio()) {
            throw new IllegalStateException("Nenhum item presente no carrinho!");
        }

        List<ItemPedido> itensPedido = itemPedidoMapper.toList(carrinho.getItensCarrinho());
        
        for (ItemPedido item: itensPedido){
            QuadrinhoDTO quadrinho = quadrinhoService.consultarByID(item.getIdQuadrinho());
            item.alterarPreco(quadrinho.getPreco());
        }
        
        List<CartaoCredito> cartoesCredito = cartaoCreditoDAO.consultarByIdList(
            pedido.getCartoesCreditoPedido()
                .stream()
                .map(cartao -> Integer.valueOf(cartao.getIdCartaoCredito()))
            .toList()
        );

        pedido.colocarEmProcessamento(
            itensPedido,
            cartoesCredito,
            carrinho
        );

        pedido.atualizarValorTotal();

        return transactionManager.execute(conn -> {
            Pedido pedidoInserido = pedidoDAO.inserir(pedido, conn);

            for (ItemPedido item : pedido.getItensPedido()) {
                pedidoInserido.adicionarItem(
                    itemPedidoDAO.inserir(item, pedido.getId(), conn)
                );

                Estoque estoque = estoqueDAO.consultarByIDQuadrinho(item.getIdQuadrinho(), conn);
                estoque.validarRetirada(item);

                estoqueDAO.retirarDoEstoque(item, conn);
            }

            for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
                pedidoInserido.adicionarCartaoPedido(
                    cartaoCreditoPedidoDAO.inserir(cartao, pedido.getId(), conn)
                );
            }

            for (CupomPedido cupom : pedido.getCuponsPedido()) {
                pedidoInserido.adicionarCupom(
                    cupomPedidoDAO.inserir(cupom, pedido.getId(), conn)
                );
                
                cupomDAO.inativar(cupom.getIdCupom(), conn);
            }

            return pedidoInserido;
        });
    }
}
