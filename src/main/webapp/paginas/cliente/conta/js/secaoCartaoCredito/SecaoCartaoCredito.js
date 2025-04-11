import { CartaoCartaoCredito } from "./CartaoCartaoCredito.js";
import { ModalAlterarCartaoCredito } from "./modalAlterarCartaoCredito.js";

export class SecaoCartaoCredito extends HTMLElement {
    constructor(cartoes) {
        super();

        this.id = 'secao-cartao-credito';
        this.style.display = 'none';

        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito();

        this.containerCartoes = document.createElement('div');
        this.append(this.containerCartoes);

        const div = document.createElement('div');

        div.innerHTML = `
            <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                data-bs-target="#modal-alterar-cartao-credito">
                Editar Cartão de crédito</button>
        `;

        this.append(div);

        this.atualizar(cartoes);
    }

    atualizar(cartoes) {
        if (cartoes) {
            this.containerCartoes.innerHTML = "";

            let contador = 1;
            cartoes.forEach(
                (e) => {
                    const cartao = new CartaoCartaoCredito(e);
                    cartao.titulo.textContent = contador + "º Cartão";
                    this.containerCartoes.append(cartao);
                    contador++;
                }
            )

            this.modalAlterarCartaoCredito.atualizar(cartoes);
        }

    }

}

customElements.define('secao-cartao-credito', SecaoCartaoCredito, { extends: 'section' });