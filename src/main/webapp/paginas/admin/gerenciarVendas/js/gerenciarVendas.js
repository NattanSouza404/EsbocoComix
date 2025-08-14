import { retornarPedidos } from "../../../../js/api/apiPedido.js";
import { retornarPedidosPosVenda } from "../../../../js/api/apiPedidoPosVenda.js";
import { SecaoPedidosPosVenda } from "./secaoPedidosPosVenda.js";
import { SecaoPedidos } from "./secaoPedidos.js";
import { alertarErro } from "../../../../js/api/alertErro.js";

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