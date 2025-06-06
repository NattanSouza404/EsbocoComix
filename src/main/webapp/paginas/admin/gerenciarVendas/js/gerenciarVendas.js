import { retornarPedidos } from "../../../../js/api/apiPedido.js";
import { retornarPedidosPosVenda } from "../../../../js/api/apiPedidoPosVenda.js";
import { SecaoPedidosPosVenda } from "./secaoPedidosPosVenda.js";
import { SecaoPedidos } from "./secaoPedidos.js";

document.getElementById("main-container").style.display = 'none';

const pedidos = await retornarPedidos();
const pedidosPosVenda = await retornarPedidosPosVenda();

document.getElementById("loading").style.display = 'none';
document.getElementById("main-container").style.display = 'block';

const secaoPedidos = new SecaoPedidos(pedidos.pedidos);
const secaoPedidosPosVenda = new SecaoPedidosPosVenda(pedidosPosVenda);