import { TIPOS_LOGRADOURO, TIPOS_RESIDENCIAL } from "../../dados.js";
import { criarElemento, criarElementoInput, criarSelectSimOuNao } from "../../script.js";

export class FormularioEndereco extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'endereco';
        const div = document.createElement('div');
        div.className = "numeracao";

        this.numeroTitulo = criarElemento('p', '1');
        div.append(this.numeroTitulo);
        div.append(criarElemento('p', 'Endereço'));
        this.append(div);

        const mainDiv = document.createElement('div');
        mainDiv.className = 'dados-endereco';
        this.append(mainDiv);

        mainDiv.append(criarElemento('label', 'Frase curta para identificação'));
        mainDiv.append(criarElementoInput('fraseCurta'));
        
        mainDiv.append(criarElemento('label', 'É um endereço residencial?'));
        mainDiv.append(criarSelectSimOuNao('isResidencial'));

        mainDiv.append(criarElemento('label', 'É um endereço de cobrança?'));
        mainDiv.append(criarSelectSimOuNao('isCobranca'));

        mainDiv.append(criarElemento('label', 'É um endereço de entrega?'));
        mainDiv.append(criarSelectSimOuNao('isEntrega'));

        mainDiv.append(criarElemento('label', 'Tipo de Logradouro'));
        let select = document.createElement('select');
        select.name = "tipoResidencial";
        mainDiv.append(select);

        TIPOS_RESIDENCIAL.forEach((tipoResidencial) => {
            let option = criarElemento('option', tipoResidencial);
            select.append(option);
        });

        mainDiv.append(criarElemento('label', 'Nº Endereço'));
        mainDiv.append(criarElementoInput('numero'));

        mainDiv.append(criarElemento('label', 'Logradouro'));
        mainDiv.append(criarElementoInput('logradouro'));

        mainDiv.append(criarElemento('label', 'Tipo de Logradouro'));
        select = document.createElement('select');
        select.name = "tipoLogradouro";
        mainDiv.append(select);
    
        TIPOS_LOGRADOURO.forEach((tipoLogradouro) => {
            let option = criarElemento('option', tipoLogradouro);
            select.append(option);
        });

        mainDiv.append(criarElemento('label', 'CEP'));
        mainDiv.append(criarElementoInput('cep', '00000-000'));

        mainDiv.append(criarElemento('label', 'Bairro'));
        mainDiv.append(criarElementoInput('bairro'));

        mainDiv.append(criarElemento('label', 'Cidade'));
        mainDiv.append(criarElementoInput('cidade'));

        mainDiv.append(criarElemento('label', 'Estado'));
        mainDiv.append(criarElementoInput('estado'));

        mainDiv.append(criarElemento('label', 'País'));
        mainDiv.append(criarElementoInput('pais'));

        mainDiv.append(criarElemento('label', 'Observações'));
        mainDiv.append(criarElementoInput('observacoes', 'Perto do prédio...'));

        this.botaoRemover = criarElemento('button', 'Remover');
        this.botaoRemover.type = 'button';
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

    atualizar(endereco){
        this.endereco = endereco;

        Object.entries(this.endereco).forEach(
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

customElements.define('form-dados-endereco', FormularioEndereco, { extends: 'form'});