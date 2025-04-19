package com.esboco_comix.service.impl.pedido;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dao.impl.pedido.CartaoCreditoPedidoDAO;
import com.esboco_comix.dao.impl.pedido.CupomPedidoDAO;
import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.CarrinhoService;

public class PedidoService {

    private CarrinhoService carrinhoService = new CarrinhoService();
    private CalculadoraPedido calculadora = new CalculadoraPedido();

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();

    public Pedido inserir(Pedido pedido, HttpSession session) throws Exception {

        Carrinho carrinho = carrinhoService.retornarCarrinhoSessao(session);

        if (carrinho.isVazio()) {
            throw new Exception("Nenhum item presente no carrinho!");
        }

        if (pedido.getCartoesCreditoPedido().isEmpty() && pedido.getCuponsPedido().isEmpty()) {
            throw new Exception("Nenhuma forma de pagamento foi provida!");
        }

        Set<Integer> idsCartao = new HashSet<>();

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            if (!idsCartao.add(cartao.getIdCartaoCredito())){
                throw new Exception("Não é possível usar o mesmo cartão duas vezes no mesmo pedido!");
            }
        }

        double valorTotalPedido = calculadora.calcularValorTotalPedido(pedido, carrinho);
        double valorTotalPago = calculadora.calcularValorFormaPagamento(pedido);

        if (valorTotalPago != valorTotalPedido){
            throw new Exception("Valor pago não condiz com valor do pedido!");
        }

        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedido.setItensPedido(carrinho.esvaziar());

        Pedido pedidoInserido = pedidoDAO.inserir(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setIdPedido(pedidoInserido.getId());
            ItemPedido itemInserido = itemPedidoDAO.inserir(item);
            pedidoInserido.getItensPedido().add(itemInserido);
        }

        for (CartaoCreditoPedido cartao : pedido.getCartoesCreditoPedido()) {
            cartao.setIdPedido(pedidoInserido.getId());
            CartaoCreditoPedido cartaoCreditoPedidoInserido = cartaoCreditoPedidoDAO.inserir(cartao);
            pedidoInserido.getCartoesCreditoPedido().add(cartaoCreditoPedidoInserido);
        }

        for (CupomPedido cupom : pedido.getCuponsPedido()) {
            cupom.setIdPedido(pedidoInserido.getId());
            CupomPedido cupomPedidoInserido = cupomPedidoDAO.inserir(cupom);
            pedidoInserido.getCuponsPedido().add(cupomPedidoInserido);
        }

        return pedidoInserido;
    }

    public List<Pedido> consultarTodos() throws Exception {
        List<Pedido> pedidos = pedidoDAO.consultarTodos();

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

    public List<Pedido> consultarPorIDCliente(int idCliente) throws Exception {
        List<Pedido> pedidos = pedidoDAO.consultarByIDCliente(idCliente);

        for (Pedido p : pedidos) {
            p.setItensPedido(itemPedidoDAO.consultarByIDPedido(p.getId()));
        }

        return pedidos;
    }

    public Pedido atualizarStatus(Pedido pedido) throws Exception {
        return pedidoDAO.atualizarStatus(pedido);
    }

}
