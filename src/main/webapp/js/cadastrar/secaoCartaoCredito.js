import { criarElemento } from "../script.js";
import { FormularioCartaoCredito } from "../forms.js";

export class SecaoFormsCartaoCredito extends HTMLElement {
    constructor(){
        super();

        const p = criarElemento("p", "Cartões de crédito");
        p.className = 'titulo-dados-cadastro';
        this.append(p);

        this.container = document.createElement('div');
        this.container.id = 'container-cartao-credito';
        this.append(this.container);

        const footer = document.createElement('div');
        footer.id = 'footer-secao-cartao-credito';
        this.append(footer);

        const button = criarElemento('button', '+ Novo Cartão de Crédito');
        button.type = 'button';
        button.onclick = () => this.adicionarCartaoCredito();
        footer.append(button);
    }

    adicionarCartaoCredito(){
        const nCartoesCreditoNaTela = document.querySelectorAll('.cartao-credito').length + 1;
        const form = new FormularioCartaoCredito();
        form.setNumeroTitulo(nCartoesCreditoNaTela);
        this.container.append(form);
    }
}

customElements.define('secao-form-cartao-credito', SecaoFormsCartaoCredito, {extends:"section"});