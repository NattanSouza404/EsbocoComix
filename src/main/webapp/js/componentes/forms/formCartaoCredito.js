import { BANDEIRAS_CARTAO } from "../../dados.js";
import { criarElemento, criarElementoInput, criarSelectSimOuNao } from "../../script.js";

export class FormularioCartaoCredito extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'cartao-credito';
        const div = document.createElement('div');
        div.className = "numeracao";

        this.numeroTitulo = criarElemento('p', '1');
        div.append(this.numeroTitulo);
        div.append(criarElemento('p', 'Cartão de Crédito'));
        this.append(div);

        const mainDiv = document.createElement('div');
        mainDiv.className = 'dados-cartao-credito';
        this.append(mainDiv);

        mainDiv.append(criarElemento('label', 'Número Cartão de Crédito'));
        mainDiv.append(criarElementoInput('numero'));
        
        mainDiv.append(criarElemento('label', 'Nome Impresso?'));
        mainDiv.append(criarElementoInput('nomeImpresso'));

        mainDiv.append(criarElemento('label', 'Código de Segurança'));
        mainDiv.append(criarElementoInput('codigoSeguranca'));

        mainDiv.append(criarElemento('label', 'É preferencial?'));
        mainDiv.append(criarSelectSimOuNao('isPreferencial'));

        mainDiv.append(criarElemento('label', 'Bandeira do Cartão'));
        let select = document.createElement('select');
        select.name = "bandeiraCartao";
        mainDiv.append(select);

        BANDEIRAS_CARTAO.forEach(({ nome, valor }) => {
            let option = criarElemento('option', nome);
            option.value = valor;
            select.append(option);
        });

        this.botaoRemover = criarElemento('button', 'Remover');
        this.botaoRemover.onclick = () => {
            if (this.parentNode){
                this.parentNode.removeChild(this);
            }
        };
        this.append(this.botaoRemover);
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