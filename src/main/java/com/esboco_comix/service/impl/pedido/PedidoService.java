package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dao.impl.cliente.ClienteDAO;
import com.esboco_comix.dao.impl.pedido.CartaoCreditoPedidoDAO;
import com.esboco_comix.dao.impl.pedido.CupomPedidoDAO;
import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.dao.impl.quadrinho.QuadrinhoDAO;
import com.esboco_comix.dto.ConsultarPedidosDTO;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.CarrinhoService;

public class PedidoService {

    private CarrinhoService carrinhoService = new CarrinhoService();
    private CalculadoraPedido calculadora = new CalculadoraPedido();

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private QuadrinhoDAO quadrinhoDAO = new QuadrinhoDAO();

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

        double valorTotalPedido = calculadora.calcularValorTotalPedido(pedido, carrinho.getItensPedido());
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

    public ConsultarPedidosDTO consultarTodos() throws Exception {
        ConsultarPedidosDTO dto = new ConsultarPedidosDTO();

        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido p : pedidoDAO.consultarTodos()) {
            pedidosDTO.add(montarDTO(p));
        }

        dto.setPedidos(pedidosDTO);
        dto.setItensPedido(consultarPedidosTroca());

        return dto;
    }

    public List<PedidoDTO> consultarPorIDCliente(int idCliente) throws Exception {
        List<PedidoDTO> pedidos = new ArrayList<>();

        for (Pedido p : pedidoDAO.consultarByIDCliente(idCliente)) {
            pedidos.add(montarDTO(p));
        }

        return pedidos;
    }

    public Pedido atualizarStatus(PedidoDTO pedido) throws Exception {
        return pedidoDAO.atualizarStatus(
            pedido.getPedido()
        );
    }

    public ItemPedido atualizarStatus(ItemPedidoDTO itemPedidoDTO) throws Exception {
        return itemPedidoDAO.atualizarStatus(
            itemPedidoDTO.getItemPedido()
        );
    }

    public List<ItemPedidoDTO> consultarPedidosTroca() throws Exception{
        List<ItemPedidoDTO> itens = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidoDAO.consultarPedidosTroca()) {
            Pedido pedido = pedidoDAO.consultarByID(itemPedido.getIdPedido());
            Cliente cliente = clienteDAO.consultarByID(pedido.getIdCliente());
            Quadrinho quadrinho = quadrinhoDAO.consultarByID(itemPedido.getIdQuadrinho());

            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedido(itemPedido);

            itemPedidoDTO.setNomeCliente(cliente.getNome());
            itemPedidoDTO.setNomeQuadrinho(quadrinho.getTitulo());

            itens.add(itemPedidoDTO);
        }

        return itens;
    }

    private PedidoDTO montarDTO(Pedido pedido) throws Exception {
        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();
        List<ItemPedido> itensPedido = itemPedidoDAO.consultarByIDPedido(pedido.getId()); 
        
        for (ItemPedido itemPedido : itensPedido) {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedido(itemPedido);
            itemPedidoDTO.setNomeQuadrinho(quadrinhoDAO.consultarByID(itemPedido.getIdQuadrinho()).getTitulo());
            itemPedidoDTOs.add(itemPedidoDTO);
        }

        Cliente cliente = clienteDAO.consultarByID(pedido.getIdCliente());

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setPedido(pedido);
        pedidoDTO.setNomeCliente(cliente.getNome());
        pedidoDTO.setValorTotal(calculadora.calcularValorTotalPedido(pedido, itensPedido));
        pedidoDTO.setItensPedidoDTO(itemPedidoDTOs);

        return pedidoDTO;
    }

}
