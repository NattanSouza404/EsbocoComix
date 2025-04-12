import { SelecaoCartaoCredito } from "./SelecaoCartaoCredito.js";

export class SecaoCartaoCredito {
    constructor(cartoesCredito){

        this.cartoesCredito = cartoesCredito;

        this.containerCartoes = document.getElementById('cartoes');

        this.adicionarCartaoCredito();
        document.getElementById('btn-adicionar-cartao').onclick = () => this.adicionarCartaoCredito();
    }

    adicionarCartaoCredito(){
        this.containerCartoes.append(
            new SelecaoCartaoCredito(this.cartoesCredito)
        );
    }

    getCartoesCreditoPedido(){
        const selectCartoes = document.querySelectorAll('.selecao-cartao-credito');

        const cartoesCreditoPedido = [];
        selectCartoes.forEach(select => {
            cartoesCreditoPedido.push(
                select.getCartaoPedido()
            )
        });

        return cartoesCreditoPedido;
    }
   
}