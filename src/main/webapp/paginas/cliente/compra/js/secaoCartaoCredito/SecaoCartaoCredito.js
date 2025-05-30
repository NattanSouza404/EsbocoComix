import { SelecaoCartaoCredito } from "./SelecaoCartaoCredito.js";

export class SecaoCartaoCredito {
    constructor(cartoesCredito){

        this.cartoesCredito = cartoesCredito;

        this.containerCartoes = document.getElementById('cartoes');
        this.btnAdicionarCartao = document.getElementById('btn-adicionar-cartao');

        if (!Array.isArray(cartoesCredito) || cartoesCredito.length == 0){
            this.containerCartoes.innerHTML = '<p>Você não possui nenhum cartão de crédito.</p>'
            this.btnAdicionarCartao.parentNode.removeChild(this.btnAdicionarCartao);
            return;
        }

        this.adicionarCartaoCredito();
        this.btnAdicionarCartao.onclick = () => this.adicionarCartaoCredito();
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

    adicionarInputListeners(secaoResumo) {
        this.secaoResumo = secaoResumo;
        const inputs = this.containerCartoes.querySelectorAll('input');
        inputs.forEach(input => {
            input.removeEventListener('input', this.boundInputHandler);
            this.boundInputHandler = this.acaoMudancaInput.bind(this);
            input.addEventListener('input', this.boundInputHandler);
        });
    }
    
    acaoMudancaInput(event) {
        this.secaoResumo.atualizarCartoes(this.getCartoesCreditoPedido());
    }
   
}