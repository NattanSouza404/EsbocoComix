import { mascararNumeroCartao } from "/js/script.js";

export class SelecaoCartaoCredito extends HTMLDivElement {
    constructor(cartoesCredito) {
        super();

        this.className = 'd-flex mb-3 selecao-cartao-credito';

        this.innerHTML = `
            <select class="form-select me-2"></select>
            <input type="number" class="form-control" placeholder="Valor a pagar" min="0" value=0>
            <button class="btn btn-primary btn-sm">Remover</button>
        `;

        const btnRemover = this.querySelector('button');
        btnRemover.onclick = () => {
            btnRemover.parentElement.remove(btnRemover);
        };

        this.preencherSelect(cartoesCredito);
    }

    preencherSelect(cartoesCredito) {
        const select = this.querySelector('select');

        cartoesCredito.forEach(cartao => {
            const option = document.createElement('option');
            option.value = cartao.id;
            option.textContent = `Cartão ${cartao.bandeiraCartao} nº ${mascararNumeroCartao(cartao.numero)}`;

            if (cartao.isPreferencial === true){
                option.selected = true;
            }

            select.append(option);
        });

        this.cartoesCredito = cartoesCredito;
    }

    getCartaoPedido() {
        let cartao;

        this.cartoesCredito.forEach((c) => {
            const id = this.querySelector('select').value;
            if (c.id == id){
                cartao = { 
                    idCartaoCredito: id,
                    valor: parseInt(
                        this.querySelector('input').value, 10
                    ),
                };
            }
        });

        return cartao;
    }
}

customElements.define('selecao-cartao-credito', SelecaoCartaoCredito, { extends: 'div' })