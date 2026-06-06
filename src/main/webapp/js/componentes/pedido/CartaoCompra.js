import { formatarPreco, formatarDateTime } from "../../script.js";
import { CartaoItemPedido } from "./CartaoItemPedido.js";

export class CartaoCompra extends HTMLDivElement {
    constructor(
        pedido,
        confirmarTrocaPedido,
        confirmarDevolucaoPedido,
        confirmarTrocaItem,
        confirmarDevolucaoItem,
        consultarPedidosPosVenda
    ){
        super();

        const e = pedido.enderecoEntrega;

        const endereco = `${e.fraseCurta}, CEP: ${e.cep}<br/>${e.cidade}, ${e.estado} - ${e.pais}`;

        this.innerHTML = /* html */ `
            <div class="p-3 mb-3 text-white rounded secao-superior">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="mb-3">
                        Código: <span class="fw-normal">#${pedido.id}</span>
                    </h5>

                    <div class="row">
                        <div class="col-sm-6">
                            <p class="mb-1">
                                <strong>Valor total:</strong> ${formatarPreco(pedido.valorTotal)}
                            </p>
                            <p class="mb-1">
                                <strong>Frete:</strong> ${formatarPreco(pedido.valorFrete)}
                            </p>
                        </div>
                        <div class="col-sm-6">
                            <p class="mb-1">
                                <strong>Endereço:<br/></strong> ${endereco}
                            </p>
                            <p class="mb-1">
                                <strong>Status:</strong> ${pedido.status}
                            </p>
                        </div>
                    </div>

                    <p class="mt-2">
                        <strong>Data:</strong> ${formatarDateTime(pedido.data)}
                    </p>

                    <div class="operacoes-pedido">
                        <button class="btn btn-light btn-sm botao-troca">
                            Pedir Troca
                        </button>

                        <button class="btn btn-light btn-sm botao-devolucao">
                            Pedir Devolução
                        </button>

                        <button
                            class="btn btn-light btn-sm botao-consultar-pedidos-pos-venda"
                            type="button"
                            data-bs-toggle="modal"
                            data-bs-target="#modal-consultar-pedidos-pos-venda"
                        >
                            Consultar Pedidos Pós Venda
                        </button>
                    </div>
                </div>
            </div>

            <div class="container-itens ps-4"></div>
        `;

        const containerItens = this.querySelector('.container-itens');
        pedido.itensPedidoDTO.forEach(item => {
            containerItens.append(CartaoItemPedido(
                item,
                confirmarTrocaItem,
                confirmarDevolucaoItem,
                true
            ));
        });

        /** @type {HTMLButtonElement} */
        (this.querySelector('.botao-troca')).onclick = () => {
            confirmarTrocaPedido(pedido);
        };
        
        /** @type {HTMLButtonElement} */
        (this.querySelector('.botao-devolucao')).onclick = () => {
            confirmarDevolucaoPedido(pedido);
        };

        /** @type {HTMLButtonElement} */
        (this.querySelector('.botao-consultar-pedidos-pos-venda')).onclick = () => {
            consultarPedidosPosVenda();
        };
    }

}

customElements.define('container-pedido', CartaoCompra, { extends:"div" });