import { retornarPedidos } from "@api/pedido.api.js";
import { retornarPedidosPosVenda } from "@api/pedidoPosVenda.api.js";
import { SecaoPedidosPosVenda } from "@paginas/secoes/minhasCompras/secaoPedidosPosVenda.js";
import { SecaoPedidos } from "@paginas/secoes/minhasCompras/secaoPedidos.js";
import { alertarErro } from "@api/alertErro.js";

export async function initPagina() {

    document.getElementById("main-container").style.display = 'none';

    let pedidos;
    let pedidosPosVenda;

    try {
        pedidos = await retornarPedidos();
        pedidosPosVenda = await retornarPedidosPosVenda();
    } catch (error){
        alertarErro(error);
    }

    document.getElementById("loading").style.display = 'none';
    document.getElementById("main-container").style.display = 'block';

    const secaoPedidos = new SecaoPedidos(pedidos);
    const secaoPedidosPosVenda = new SecaoPedidosPosVenda(pedidosPosVenda);

}