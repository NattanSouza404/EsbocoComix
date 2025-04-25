package com.esboco_comix.service.impl.pedido;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.esboco_comix.controller.session.Carrinho;
import com.esboco_comix.dao.impl.pedido.CartaoCreditoPedidoDAO;
import com.esboco_comix.dao.impl.pedido.CupomPedidoDAO;
import com.esboco_comix.dao.impl.pedido.ItemPedidoDAO;
import com.esboco_comix.dao.impl.pedido.PedidoDAO;
import com.esboco_comix.dto.ConsultarPedidosDTO;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.CartaoCreditoPedido;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.CarrinhoService;
import com.esboco_comix.service.impl.ClienteService;
import com.esboco_comix.service.impl.CupomService;
import com.esboco_comix.service.impl.QuadrinhoService;

public class PedidoService {

    private CalculadoraPedido calculadora = new CalculadoraPedido();
    private PedidoValidator validator = new PedidoValidator(calculadora);

    private CarrinhoService carrinhoService = new CarrinhoService();
    private ClienteService clienteService = new ClienteService();
    private QuadrinhoService quadrinhoService = new QuadrinhoService();
    private CupomService cupomService = new CupomService();

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();

    public Pedido inserir(Pedido pedido, HttpSession session) throws Exception {

        Carrinho carrinho = carrinhoService.retornarCarrinhoSessao(session);

        if (carrinho.isVazio()) {
            throw new Exception("Nenhum item presente no carrinho!");
        }

        validator.validarFormaPagamento(pedido, carrinho);

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
        return new ConsultarPedidosDTO(
            pedidoDAO.consultarTodos(),
            itemPedidoDAO.consultarPedidosTroca()
        );
    }

    public List<PedidoDTO> consultarPorIDCliente(int idCliente) throws Exception {
        List<PedidoDTO> pedidoDTO = pedidoDAO.consultarByIDCliente(idCliente);

        Map<Integer, List<ItemPedidoDTO>> itensMap = new HashMap<>();

        for (ItemPedidoDTO itemPedidoDTO : itemPedidoDAO.consultarByIDCliente(idCliente)) {
            int idPedido = itemPedidoDTO.getItemPedido().getIdPedido();
            
            if (itensMap.get(idPedido) == null){
                itensMap.put(idPedido, 
                    Arrays.asList(itemPedidoDTO)
                );
                continue;
            }

            itensMap.get(idPedido)
                .add(itemPedidoDTO);
        }

        for (PedidoDTO p : pedidoDTO) {
            p.setItensPedidoDTO(
                itensMap.get(p.getPedido().getId())
            );
        }

        return pedidoDTO;
    }

    public Pedido atualizarStatus(PedidoDTO pedido) throws Exception {
        if (pedido.getPedido().getStatus().equals(StatusPedido.TROCA_ACEITA)){
            List<ItemPedido> itensPedido = itemPedidoDAO.consultarByIDPedido(pedido.getPedido().getId());

            List<Quadrinho> quadrinhos = quadrinhoService.consultarTodos();
            double valor = calculadora.calcularValorTotalPedido(quadrinhos, itensPedido);

            cupomService.inserir(
                cupomService.gerarCupomTroca(pedido.getPedido().getIdCliente(), valor)
            );
        }

        return pedidoDAO.atualizarStatus(
            pedido.getPedido()
        );
    }

    public ItemPedido atualizarStatus(ItemPedidoDTO itemPedidoDTO) throws Exception {
        if (itemPedidoDTO.getItemPedido().getStatus().equals(StatusItemPedido.TROCA_ACEITA)){
            Quadrinho quadrinho = quadrinhoService.consultarByID(itemPedidoDTO.getItemPedido().getIdQuadrinho());
            double valor = quadrinho.getPreco() * itemPedidoDTO.getItemPedido().getQuantidade(); 

            cupomService.inserir(
                cupomService.gerarCupomTroca(clienteService.consultarByIDPedido(
                    itemPedidoDTO.getItemPedido().getIdPedido()
                ).getId(), 
                valor)
            );
        }

        return itemPedidoDAO.atualizarStatus(
            itemPedidoDTO.getItemPedido()
        );
    }

}
