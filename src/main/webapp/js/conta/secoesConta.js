import { criarElemento } from "../script.js";

export class DadosPessoaisConta extends HTMLElement {
    constructor(clienteAtual){
        super();

        this.id = 'dados-pessoais';
        this.cliente = clienteAtual;

        this.preencherDados();
    }

    async preencherDados(){

        const dados = {
            "Nome":             this.cliente.nome,
            "Data Nascimento":  this.cliente.dataNascimento,
            "CPF":              this.cliente.cpf,
            "E-mail":           this.cliente.email,
            "Gênero":           this.cliente.genero,
            "Ranking":          this.cliente.ranking
        };

        Object.entries(dados).forEach(
            ([chave, valor]) => {
                this.append(criarElemento('p', chave+": "+valor));
            }
        );
    
        this.append(document.createElement('hr'));
        
        this.append(criarElemento(
            'p', `Telefone ${this.cliente.telefone.tipo} : ${this.cliente.telefone.ddd} ${this.cliente.telefone.numero}`
        ));
    }
}

export class DadosEnderecoConta extends HTMLElement {
    constructor(endereco){

        super();

        this.className = 'endereco-conta';
        this.endereco = endereco;

        this.titulo = criarElemento('h3', 'Endereço');
        this.append(this.titulo);

        let msgTipoEndereco = "";

        if (this.endereco.isResidencial === true){
            msgTipoEndereco += "Residencial";
        }

        if (this.endereco.isEntrega === true){
            msgTipoEndereco += "Entrega";
        }

        if (this.endereco.isCobranca === true){
            msgTipoEndereco += "Cobranca";
        }

        if (this.endereco.observacoes === null){
            this.endereco.observacoes = "";
        }

        const dados = {
            "Frase Curta":          this.endereco.fraseCurta,
            "Tipo do Endereço":     msgTipoEndereco,
            "Tipo de Residência":   this.endereco.tipoResidencial,
            "Nº":                   this.endereco.numero,
            "Tipo Logradouro":      this.endereco.tipoLogradouro,
            "CEP":                  this.endereco.cep,
            "Bairro":               this.endereco.bairro,
            "Cidade":               this.endereco.cidade,
            "Estado":               this.endereco.estado,
            "País":                 this.endereco.pais,
            "Observações":          this.endereco.observacoes
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
