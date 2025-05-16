import { formatarPreco } from "/js/script.js";

export class CartaoCupom extends HTMLElement {
    constructor(cupom){
        super();

        this.className = 'cupom-conta';
        
        this.atualizar(cupom);

        this.cupom = cupom;
    }

    atualizar(cupom){
        let tipoCupom = 'Promocional';

        if (cupom.isTroca === true){
            tipoCupom = "de Troca";
        }


        this.insertAdjacentHTML('beforeend', `
            <h3 class="titulo-cupom">Cupom ${tipoCupom}</h3>
            <p>Valor: ${formatarPreco(cupom.valor)}</p>
        `);
    }
    
}

customElements.define('dados-cartao-cupom', CartaoCupom);