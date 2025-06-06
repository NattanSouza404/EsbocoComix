import { retornarPedidos } from "../../../../js/api/apiPedido.js";
import { retornarPedidosPosVendaByCliente } from "../../../../js/api/apiPedidoPosVenda.js";
import { SecaoCompras } from "./secaoCompras.js";
import { SecaoPedidosPosVenda } from "./secaoPedidosPosVenda.js";

const idCliente = localStorage.getItem('idcliente');

const pedidos = await retornarPedidos(idCliente);
const secaoCompras = new SecaoCompras(pedidos);

const pedidosPosVenda = await retornarPedidosPosVendaByCliente(idCliente);
const secaoPedidosPosVenda = new SecaoPedidosPosVenda(pedidosPosVenda);