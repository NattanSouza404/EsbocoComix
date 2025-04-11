import { criarElemento } from "/js/script.js";

export class CartaoEndereco extends HTMLElement {
    constructor(endereco){

        super();

        this.className = 'endereco-conta';
        this.titulo = criarElemento('h3', 'Endereço');
        this.append(this.titulo);
        this.atualizar(endereco);
    }

    atualizar(endereco){
        let msgTipoEndereco = "";

        if (endereco.isResidencial === true){
            msgTipoEndereco += "[Residencial] ";
        }

        if (endereco.isEntrega === true){
            msgTipoEndereco += "[Entrega] ";
        }

        if (endereco.isCobranca === true){
            msgTipoEndereco += "[Cobranca] ";
        }

        if (endereco.observacoes === null){
            endereco.observacoes = "";
        }

        const dados = {
            "Frase Curta":          endereco.fraseCurta,
            "Tipo do Endereço":     msgTipoEndereco,
            "Tipo de Residência":   endereco.tipoResidencial,
            "Nº":                   endereco.numero,
            "Tipo Logradouro":      endereco.tipoLogradouro,
            "CEP":                  endereco.cep,
            "Bairro":               endereco.bairro,
            "Cidade":               endereco.cidade,
            "Estado":               endereco.estado,
            "País":                 endereco.pais,
            "Observações":          endereco.observacoes
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    }
}

customElements.define('dados-endereco-conta', CartaoEndereco);