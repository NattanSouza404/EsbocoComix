export class SelecaoCartaoCredito extends HTMLDivElement {
    constructor(cartoesCredito) {
        super();

        this.className = 'd-flex mb-3 selecao-cartao-credito';

        this.innerHTML = `
            <select class="form-select me-2"></select>
            <input type="number" class="form-control" placeholder="Valor a pagar" min="0">
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
            option.textContent = `${cartao.numero}: (${cartao.bandeiraCartao})`;

            if (cartao.isPreferencial === true){
                option.selected = true;
            }

            select.append(option);
        });
    }

    getCartaoPedido() {
        return {
            idCartaoCredito: this.querySelector('select').value,
            valor: this.querySelector('input').value
        };
    }
}

customElements.define('selecao-cartao-credito', SelecaoCartaoCredito, { extends: 'div' })