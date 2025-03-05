class SecaoCadastrarDadosPessoais extends HTMLElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';
        this.className = 'secao-cadastrar';

        const form = new FormularioDadosPessoais();
        form.id = "form-cadastrar";
        this.append(form);

        const header = document.createElement('div');
        header.className = 'header-dados-cadastro';
        header.append(criarElemento('p', 'Senha'));
        form.append(header);

        this.secaoSenha = document.createElement('div');
        this.secaoSenha.className = 'dados-formulario';
        form.append(this.secaoSenha);

        this.secaoSenha.append(criarElemento('label', "Senha"));
        let input = document.createElement('input');
        input.type = 'password';
        input.name = 'senha';
        this.secaoSenha.append(input);

        this.secaoSenha.append(criarElemento('label', "Confirme a senha"));
        input = document.createElement('input');
        input.type = 'password';
        input.name = 'senhaConfirmacao';
        this.secaoSenha.append(input);
    }

}

customElements.define('secao-cadastrar-dados-pessoais', SecaoCadastrarDadosPessoais);

const cadastrarCliente = document.getElementById('cadastrar-cliente');
cadastrarCliente.append(new SecaoCadastrarDadosPessoais());

function enviarCliente(){
    const form = document.getElementById('form-cadastrar');
    const cliente = montarClientePorForm(form);
    
    inserirCliente(cliente);
}