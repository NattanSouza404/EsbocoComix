import { BANDEIRAS_CARTAO } from "../../dados.js";

export class FormularioCartaoCredito extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'cartao-credito';

        this.innerHTML = `
            <div class="numeracao">
                <p class="numeroTitulo">1</p>
                <p>Cartão de Crédito</p>
            </div>

            <div class="dados-cartao-credito">
                <label>
                    Número Cartão de Crédito
                    <input name="numero" placeholder="xxxx.xxxx.xxxx.xxxx" minlength="16" maxlength="16"></input>
                </label>

                <label>
                    Nome Impresso
                    <input name="nomeImpresso" placeholder="NOME IMPRESSO"></input>
                </label>

                <label>
                    Código de Segurança
                    <input name="codigoSeguranca" placeholder="xxx" minlength="3" maxlength="3"></input>
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
    }

    atualizar(cartaoCredito){
        this.cartaoCredito = cartaoCredito;

        Object.entries(this.cartaoCredito).forEach(
            ([chave, valor]) => {
                let elemento = this.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                elemento.value = valor;
            }
        );
    }
}

customElements.define('form-dados-cartao-credito', FormularioCartaoCredito, { extends: 'form'});