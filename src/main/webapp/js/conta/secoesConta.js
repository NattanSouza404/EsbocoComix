import { criarElemento } from "../script.js";

export class DadosPessoaisConta extends HTMLElement {
    constructor(cliente){
        super();
        this.atualizar(cliente);
    }

    async atualizar(cliente){

        this.textContent = "";

        const dados = {
            "Nome":             cliente.nome,
            "Data Nascimento":  cliente.dataNascimento,
            "CPF":              cliente.cpf,
            "E-mail":           cliente.email,
            "Gênero":           cliente.genero,
            "Ranking":          cliente.ranking
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    
        this.append(document.createElement('hr'));

        const telefone = cliente.telefone;
        
        this.append(criarElemento(
            'p', `Telefone ${telefone.tipo} : ${telefone.ddd} ${telefone.numero}`
        ));
    }
}

export class DadosEnderecoConta extends HTMLElement {
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

export class DadosCartaoCreditoConta extends HTMLElement {
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
            "Bandeira do Cartão":   cartaoCredito.bandeiraCartao.nome,
            "Preferencial":         cartaoCredito.isPreferencial,
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    }
}

customElements.define('dados-pessoais-conta', DadosPessoaisConta);
customElements.define('dados-endereco-conta', DadosEnderecoConta);
customElements.define('dados-cartao-credito', DadosCartaoCreditoConta) 
