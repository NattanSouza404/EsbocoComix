let promessaCliente = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const cliente = await retornarCliente(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada cliente:", cliente);
    return cliente;
})();

let promessaEnderecos = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const enderecos = await retornarEnderecos(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada endereços:", enderecos);
    return enderecos;
})();

class DadosPessoaisConta extends HTMLElement {
    constructor(){
        super();

        this.id = 'dados-pessoais';

        promessaCliente.then((cliente) => {
            this.preencherDados(cliente);
        });
    }

    async preencherDados(cliente){

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
        
        this.append(criarElemento(
            'p', `Telefone ${cliente.telefone.tipo} : ${cliente.telefone.ddd} ${cliente.telefone.numero}`
        ));
    }
}

class DadosEnderecoConta extends HTMLElement {
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

const dadosPessoaisConta = new DadosPessoaisConta();

const conta = document.getElementById('conta');
conta.append(dadosPessoaisConta);

const dadosEndereco = document.createElement('div');
dadosEndereco.id = 'dados-endereco';
conta.append(dadosEndereco);

promessaEnderecos.then((enderecos) => {
    let contador = 1;
    enderecos.forEach(
        (e) => {
            const endereco = new DadosEnderecoConta(e);
            endereco.titulo.textContent = contador+"º Endereço";
            dadosEndereco.append(endereco);
            contador++;
        }
    )
});

const modalAlterarSenha = new ModalAlterarSenha();
document.body.append(modalAlterarSenha);

const modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
document.body.append(modalAlterarDadosPessoais);

promessaCliente.then((cliente) =>{
    modalAlterarDadosPessoais.atualizar(cliente);
});