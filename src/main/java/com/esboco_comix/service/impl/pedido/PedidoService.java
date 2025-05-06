package com.esboco_comix.service.impl.pedido;

import java.util.ArrayList;
import java.util.List;

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
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.service.impl.CarrinhoService;
import com.esboco_comix.service.impl.CartaoCreditoService;
import com.esboco_comix.service.impl.ClienteService;
import com.esboco_comix.service.impl.CupomService;
import com.esboco_comix.service.impl.EstoqueService;
import com.esboco_comix.service.impl.QuadrinhoService;

public class PedidoService {

    private CarrinhoService carrinhoService = new CarrinhoService();
    private ClienteService clienteService = new ClienteService();
    private CupomService cupomService = new CupomService();
    private QuadrinhoService quadrinhoService = new QuadrinhoService();
    private CartaoCreditoService cartaoCreditoService = new CartaoCreditoService();
    private EstoqueService estoqueService = new EstoqueService();
    
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private CartaoCreditoPedidoDAO cartaoCreditoPedidoDAO = new CartaoCreditoPedidoDAO();
    private CupomPedidoDAO cupomPedidoDAO = new CupomPedidoDAO();

    private CalculadoraPedido calculadora = new CalculadoraPedido(
        this.cupomService,
        this.quadrinhoService,
        this.cartaoCreditoService
    );

    private PedidoValidator validator = new PedidoValidator(calculadora);

    public Pedido inserir(Pedido pedido, HttpSession session) throws Exception {

        Carrinho carrinho = carrinhoService.retornarCarrinhoSessao(session);

        if (carrinho.isVazio()) {
            throw new Exception("Nenhum item presente no carrinho!");
        }

        validator.validarFormaPagamento(pedido, carrinho);

        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedido.setItensPedido(carrinho.esvaziar());
        pedido.setValorTotal(calculadora.calcularValorTotalPedido(pedido, pedido.getItensPedido()));

        Pedido pedidoInserido = pedidoDAO.inserir(pedido);

        for (ItemPedido item : pedido.getItensPedido()) {
            item.setIdPedido(pedidoInserido.getId());
            ItemPedido itemInserido = itemPedidoDAO.inserir(item);
            pedidoInserido.getItensPedido().add(itemInserido);

            estoqueService.retirarDoEstoque(item);
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
            cupomService.inativar(cupom.getIdCupom());
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
        return pedidoDAO.consultarByIDCliente(idCliente);
    }

    public Pedido atualizarStatus(PedidoDTO pedidoDTO) throws Exception {
        Pedido pedido = pedidoDTO.getPedido();
        Pedido pedidoNoBanco = pedidoDAO.consultarByID(pedido.getId());

        StatusPedido status = pedido.getStatus();
        StatusPedido statusNoBanco = pedidoNoBanco.getStatus();

        List<ItemPedidoDTO> itens = itemPedidoDAO.consultarByIDPedido(pedido.getId());

        for (ItemPedidoDTO item : itens) {
            if (item.getItemPedido().getStatus() != null){
                throw new Exception("Esse pedido já possui item com pedido de troca/devolução!");
            }
        }
        
        if (statusNoBanco == StatusPedido.TROCA_CONCLUIDA || statusNoBanco == StatusPedido.DEVOLUCAO_CONCLUIDA){
            throw new Exception("Não é possível alterar pedido com troca ou devolução já concluída!");
        }

        if (status.equals(StatusPedido.TROCA_SOLICITADA) || status.equals(StatusPedido.DEVOLUCAO_SOLICITADA)){
            if (!pedidoNoBanco.getStatus().equals(StatusPedido.ENTREGUE)){
                throw new Exception("Não se pode pedir troca ou devolução se o pedido não foi entregue!");
            }
        }

        if (status.equals(StatusPedido.TROCA_CONCLUIDA) || status.equals(StatusPedido.DEVOLUCAO_CONCLUIDA)){
            List<ItemPedidoDTO> itensPedidoDTO = itemPedidoDAO.consultarByIDPedido(pedido.getId());
            List<ItemPedido> itensPedido = new ArrayList<>();
            
            for (ItemPedidoDTO itemPedidoDTO : itensPedidoDTO) {
                itensPedido.add(itemPedidoDTO.getItemPedido());
            }

            cupomService.gerarCupomTroca(
                pedido.getIdCliente(),
                calculadora.calcularValorTotalPedido(pedido, itensPedido)
            );

            estoqueService.retornarAoEstoque(pedidoDTO);
        }

        return pedidoDAO.atualizarStatus(pedido);
    }

    public ItemPedido atualizarStatus(ItemPedidoDTO itemPedidoDTO) throws Exception {
        ItemPedido item = itemPedidoDTO.getItemPedido();
        
        Pedido pedidoNoBanco = pedidoDAO.consultarByID(item.getIdPedido());
        StatusPedido statusPedidoBanco = pedidoNoBanco.getStatus();

        if (statusPedidoBanco != StatusPedido.ENTREGUE){
            throw new Exception("Pedido não entregue ou pedido de troca/devolução dos itens já foi realizado!");   
        }

        StatusItemPedido statusItemPedido = item.getStatus();
        StatusItemPedido statusItemPedidoNoBanco = itemPedidoDAO.consultarByID(item.getIdPedido(), item.getIdQuadrinho()).getStatus();

        if (statusItemPedidoNoBanco == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedido == StatusItemPedido.DEVOLUCAO_CONCLUIDA){
            throw new Exception("Troca ou devolução do item já foi realizada");
        }

        if (statusItemPedido == StatusItemPedido.TROCA_CONCLUIDA || statusItemPedido == StatusItemPedido.DEVOLUCAO_CONCLUIDA){

            Cliente cliente = clienteService.consultarByIDPedido(item.getIdPedido());  
            
            cupomService.gerarCupomTroca(
                cliente.getId(), 
                calculadora.calcularItemPedido(item)
            );

            estoqueService.retornarAoEstoque(itemPedidoDTO);

        }

        return itemPedidoDAO.atualizarStatus(item);
    }

}
