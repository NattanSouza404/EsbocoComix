import { criarElemento } from "/js/script.js";
import { FormularioCartaoCredito } from "/js/componentes/forms/formCartaoCredito.js";

export class SecaoFormsCartaoCredito extends HTMLElement {
    constructor(){
        super();

        this.insertAdjacentHTML('beforeend', `
            <p>Cartões de Crédito</p>

            <div id="container-cartao-credito"></div>
            
            <div id="footer-secao-cartao-credito">
                <button type="button" class="btn-adicionar-cartao">+ Novo Cartão de Crédito</button>
            </div>
        `);

        this.container = this.querySelector('#container-cartao-credito');

        this.btnAddCartao = this.querySelector('.btn-adicionar-cartao').onclick = () => {
            this.adicionarCartaoCredito();
        }
    }

    adicionarCartaoCredito(){
        const nCartoesCreditoNaTela = document.querySelectorAll('.cartao-credito').length + 1;
        const form = new FormularioCartaoCredito();
        form.setNumeroTitulo(nCartoesCreditoNaTela);
        this.container.append(form);

        form.insertAdjacentHTML('beforeend', `
            <button type="button" class="btn-remover">Remover</button>
        `);

        form.querySelector('.btn-remover').onclick = () => {
            if (form.parentNode){
                form.parentElement.removeChild(form);
            }
        };

        return form;
    }
}

customElements.define('secao-form-cartao-credito', SecaoFormsCartaoCredito, {extends:"section"});