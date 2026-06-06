import { formatarPreco } from "../../script.js";

export const SumarioCarrinho = (valorTotal) => {
    const div = document.createElement('div');
    div.className = "d-flex justify-content-between align-items-center mt-4";

    div.innerHTML = /* html */ `
        <h4>
            Total: <span id="total-carrinho" class="text-success">
                ${formatarPreco(valorTotal)}
            </span>
        </h4>
        <a
            id="link-compra"
            href="/compra" 
        >
            <button class="btn btn-primary btn-lg">
                Comprar
            </button>
        </a>
    `;

    return div;
}