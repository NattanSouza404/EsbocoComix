let promessaCliente = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const cliente = await retornarCliente(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada cliente:", cliente);
    return cliente;
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

customElements.define('dados-pessoais-conta', DadosPessoaisConta);

const dadosPessoaisConta = new DadosPessoaisConta();

const conta = document.getElementById('conta');
conta.append(dadosPessoaisConta);

const modalAlterarSenha = new ModalAlterarSenha();
document.body.append(modalAlterarSenha);

const modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
document.body.append(modalAlterarDadosPessoais);