import { formatarPreco, formatarDateTime } from "../../../js/script.js";

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

function CartaoItemPedido(
    item,
    confirmarTrocaItem,
    confirmarDevolucaoItem,
    acoesHabilitadas
){
    const cartao = document.createElement('div');
    cartao.className = "p-3 mb-2 bg-light rounded border";

    cartao.innerHTML = /* html */ `
        <div class="d-flex justify-content-between align-items-center">
            <img src="${item.urlImagem}">
            <div>
                <h6>Produto: ${item.nomeQuadrinho}</h6>
                <p>Quantidade: ${item.quantidade} unidades</p>
                ${item.status !== null && item.status !== undefined ? 
                    `<p>${item.status}</p>` : ''
                }
                <p>${formatarPreco(item.preco)}</p>
                <p>Total: ${formatarPreco(item.preco * item.quantidade)}
            </div>
            <div class="acoes-item-pedido" style="display: none">
                <button class="btn btn-secondary btn-sm botao-troca-item">
                    Pedir Troca
                </button>
                <button class="btn btn-secondary btn-sm botao-devolucao-item">
                    Pedir Devolução
                </button>
            </div>
        </div>
    `;

    if (acoesHabilitadas){
        /** @type {HTMLElement} */
        (cartao.querySelector('.acoes-item-pedido')).style.display = "block";

        /** @type {HTMLButtonElement} */
        (cartao.querySelector('.botao-troca-item')).onclick = () => {
            confirmarTrocaItem(item);
        };

        /** @type {HTMLButtonElement} */
        (cartao.querySelector('.botao-devolucao-item')).onclick = () => {
            confirmarDevolucaoItem(item);
        };
    }

    return cartao;
}