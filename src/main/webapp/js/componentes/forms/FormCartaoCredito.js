import { BANDEIRAS_CARTAO } from "../../dados.js";

export class FormCartaoCredito extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'cartao-credito';

        this.innerHTML = /* html */`
            <div class="numeracao">
                <p class="numeroTitulo">1</p>
                <p>Cartão de Crédito</p>
            </div>

            <div class="dados-cartao-credito">
                <label>
                    Número Cartão de Crédito
                    <input
                        name="numero"
                        placeholder="xxxxxxxxxxxxxxxx"
                        minlength="16"
                        maxlength="16"
                    >
                </label>

                <label>
                    Nome Impresso
                    <input name="nomeImpresso" placeholder="NOME IMPRESSO">
                </label>

                <label>
                    Código de Segurança
                    <input
                        name="codigoSeguranca"
                        placeholder="xxx"
                        minlength="3"
                        maxlength="3"
                    >
                </label>

                <label>
                    É preferencial?
                    <select name="isPreferencial">
                        <option value="true">Sim</option>
                        <option value="false" selected>Não</option>
                    </select>
                </label>

                <label>
                    Bandeira do Cartão
                    <select name="bandeiraCartao"></select>
                </label>

            </div>

            <button
                class="btn-remover"
                type="button"
                style="display: none;"
            >
                Remover
            </button>
        `;

        this.numeroTitulo = this.querySelector('.numeroTitulo');

        let select = this.querySelector('[name="bandeiraCartao"]');
        BANDEIRAS_CARTAO.forEach(({ nome, valor }) => {
            select.insertAdjacentHTML("beforeend", `
                <option value="${valor}">${nome}</option>
            `);
        });
    }

    setNumeroTitulo(numero){
        this.numeroTitulo.textContent = numero;
        return this;
    }

    habilitarBotaoRemover(){
        const btnRemover = /** @type {HTMLButtonElement} */
            (this.querySelector(".btn-remover"))

        btnRemover.onclick = () => {
            if (this.parentNode){
                this.parentElement.removeChild(this);
            }
        }

        btnRemover.style.display = "block";

        return this;
    }

    atualizar(cartaoCredito){
        this.cartaoCredito = cartaoCredito;

        Object.entries(this.cartaoCredito).forEach(
            ([chave, valor]) => {
                let elemento = /** @type {HTMLInputElement} */
                    (this.querySelector(`[name="${chave}"]`));

                if (!elemento){
                    return;
                }

                elemento.value = valor;
            }
        );
    }
}

customElements.define('form-dados-cartao-credito', FormCartaoCredito, { extends: 'form'});