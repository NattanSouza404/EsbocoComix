import { deletarCartaoCredito } from "/js/api/apiCartaoCredito.js";

export class CartaoCartaoCredito extends HTMLDivElement {
    constructor(cartaoCredito, modalAlterarCartaoCredito){
        super();

        this.className = 'cartao-credito-conta';

        this.modalAlterarCartaoCredito = modalAlterarCartaoCredito;

        let preferencial = cartaoCredito.isPreferencial === true ? "(Preferencial)" : "";
        this.insertAdjacentHTML('beforeend', `
            <h3>Cartão ${preferencial}</h3>
        `);

        this.titulo = this.querySelector('h3');
        
        this.atualizar(cartaoCredito);

        this.cartaoCredito = cartaoCredito;
    }

    atualizar(cartaoCredito){
        this.insertAdjacentHTML('beforeend', `
            <p>Número Cartão: ${cartaoCredito.numero}</p>
            <p>Código de Segurança: ${cartaoCredito.codigoSeguranca}</p>
            <p>Nome impresso: ${cartaoCredito.nomeImpresso}</p>
            <p>Bandeira do Cartão: ${cartaoCredito.bandeiraCartao}</p>

            <button class="btn-atualizar">Atualizar</button>
            <button class="btn-deletar">Remover</button> 
        `);

        const btnAtualizar = this.querySelector('.btn-atualizar');
        btnAtualizar.onclick = () => {
            this.modalAlterarCartaoCredito.show(this.cartaoCredito);
        };

        const btnDeletar = this.querySelector('.btn-deletar');
        btnDeletar.onclick = () => {
            deletarCartaoCredito(this.cartaoCredito);
        };
    }
    
}

customElements.define('dados-cartao-credito', CartaoCartaoCredito, { extends: 'div'}) 
