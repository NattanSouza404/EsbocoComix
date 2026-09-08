import { CartaoCartaoCredito } from "./CartaoCartaoCredito.js";
import { ModalAdicionarCartaoCredito } from "./modalAdicionarCartaoCredito.js";
import { ModalAlterarCartaoCredito } from "./modalAlterarCartaoCredito.js";

export class SecaoCartaoCredito {
    constructor(cartoes) {
        this.modalAlterarCartaoCredito = new ModalAlterarCartaoCredito();
        this.modalAdicionarCartaoCredito = new ModalAdicionarCartaoCredito();

        this.elementoHTML = document.getElementById('secao-cartao-credito');
        this.containerCartoes = this.elementoHTML.querySelector('.container-cartoes');

        this.atualizar(cartoes);
    }

    atualizar(cartoes) {
        if (cartoes && Array.isArray(cartoes)) {
            this.containerCartoes.innerHTML = "";

            let contador = 1;
            cartoes.forEach(
                (c) => {
                    const cartao = new CartaoCartaoCredito(c, this.modalAlterarCartaoCredito);
                    cartao.titulo.textContent = `${contador}º ${cartao.titulo.textContent}`;
                    this.containerCartoes.append(cartao);
                    contador++;
                }
            )

        }

    }

}