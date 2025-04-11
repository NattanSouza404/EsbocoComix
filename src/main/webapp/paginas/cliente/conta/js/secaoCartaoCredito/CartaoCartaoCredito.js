import { criarElemento } from "/js/script.js";

export class CartaoCartaoCredito extends HTMLElement {
    constructor(cartaoCredito){
        super();

        this.className = 'cartao-credito-conta';

        this.titulo = criarElemento('h3', '1º Cartão');
        this.append(this.titulo);
        this.atualizar(cartaoCredito);
    }

    atualizar(cartaoCredito){
        const dados = {
            "Número Cartão":        cartaoCredito.numero,
            "Código de Segurança":  cartaoCredito.codigoSeguranca,
            "Nome impresso":        cartaoCredito.nomeImpresso,
            "Bandeira do Cartão":   cartaoCredito.bandeiraCartao,
            "Preferencial":         cartaoCredito.isPreferencial,
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    }
}

customElements.define('dados-cartao-credito', CartaoCartaoCredito) 
