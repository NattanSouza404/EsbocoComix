import { formatarPreco } from "../../script.js";

export const CartaoItemPedido = (
    item,
    confirmarTrocaItem,
    confirmarDevolucaoItem,
    acoesHabilitadas
) => {
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