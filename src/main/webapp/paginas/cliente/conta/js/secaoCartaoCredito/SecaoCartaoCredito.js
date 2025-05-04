import { CartaoCartaoCredito } from "./CartaoCartaoCredito.js";
import { ModalAdicionarCartaoCredito } from "./modalAdicionarCartaoCredito.js";
import { ModalAlterarCartaoCredito } from "./modalAlterarCartaoCredito.js";

export class SecaoCartaoCredito extends HTMLElement {
    constructor(cartoes) {
        super();

        this.id = 'secao-cartao-credito';
        this.style.display = 'none';

        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito();
        this.modalAdicionarCartaoCredito = new ModalAdicionarCartaoCredito();

        this.insertAdjacentHTML('beforeend', `
            <div class="container-cartoes"></div>

            <div>
                <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                data-bs-target="#modal-adicionar-cartao-credito">
                    Adicionar Cartão de crédito
                </button>
            </div>             
        `);

        this.containerCartoes = this.querySelector('.container-cartoes');

        this.atualizar(cartoes);
    }

    atualizar(cartoes) {
        if (Array.isArray(cartoes)) {
            this.containerCartoes.innerHTML = "";

            let contador = 1;
            cartoes.forEach(
                (e) => {
                    const cartao = new CartaoCartaoCredito(e, this.modalAlterarCartaoCredito);
                    cartao.titulo.textContent = contador + "º Cartão";
                    this.containerCartoes.append(cartao);
                    contador++;
                }
            )

        }

    }

}

customElements.define('secao-cartao-credito', SecaoCartaoCredito, { extends: 'section' });