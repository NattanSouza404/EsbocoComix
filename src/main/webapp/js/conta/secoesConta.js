import { criarElemento } from "../script.js";

export class DadosPessoaisConta extends HTMLElement {
    constructor(clienteAtual){
        super();

        this.id = 'dados-pessoais';
        this.clienteAtual = clienteAtual;

        this.preencherDados();
    }

    async preencherDados(){

        const dados = {
            "Nome":             this.clienteAtual.nome,
            "Data Nascimento":  this.clienteAtual.dataNascimento,
            "CPF":              this.clienteAtual.cpf,
            "E-mail":           this.clienteAtual.email,
            "Gênero":           this.clienteAtual.genero,
            "Ranking":          this.clienteAtual.ranking
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    
        this.append(document.createElement('hr'));
        
        this.append(criarElemento(
            'p', `Telefone ${this.clienteAtual.telefone.tipo} : ${this.clienteAtual.telefone.ddd} ${this.clienteAtual.telefone.numero}`
        ));
    }
}

export class DadosEnderecoConta extends HTMLElement {
    constructor(endereco){

        super();

        this.className = 'endereco-conta';

        this.titulo = criarElemento('h3', 'Endereço');
        this.append(this.titulo);

        let msgTipoEndereco = "";

        if (endereco.isResidencial === true){
            msgTipoEndereco += "Residencial";
        }

        if (endereco.isEntrega === true){
            msgTipoEndereco += "Entrega";
        }

        if (endereco.isCobranca === true){
            msgTipoEndereco += "Cobranca";
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

customElements.define('dados-pessoais-conta', DadosPessoaisConta);
customElements.define('dados-endereco-conta', DadosEnderecoConta);
