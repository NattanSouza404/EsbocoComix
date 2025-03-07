class SecaoCadastrarDadosPessoais extends HTMLElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';
        this.className = 'secao-cadastrar';

        const form = new FormularioDadosPessoais();
        form.id = "form-cadastrar-dados-pessoais";
        this.append(form);

        const header = document.createElement('div');
        header.className = 'header-dados-cadastro';
        header.append(criarElemento('p', 'Senha'));
        form.append(header);

        this.secaoSenha = document.createElement('div');
        this.secaoSenha.className = 'dados-formulario';
        form.append(this.secaoSenha);

        this.secaoSenha.append(criarElemento('label', "Senha"));
        this.secaoSenha.append(criarElementoInput('senha', null, 'password'));

        this.secaoSenha.append(criarElemento('label', "Confirme a senha"));
        this.secaoSenha.append(criarElementoInput('senhaConfirmacao', null, 'password'));
    }

}

customElements.define('secao-cadastrar-dados-pessoais', SecaoCadastrarDadosPessoais);

const cadastrarCliente = document.getElementById('container-cadastrar-cliente');
cadastrarCliente.append(new SecaoCadastrarDadosPessoais());

function montarEnderecoObrigatorio(){
    const form = new FormularioEndereco();
    const button = form.querySelector('button');
    form.removeChild(button);

    const isResidencial = form.querySelector('[name = "isResidencial"]');
    isResidencial.value = 'true';
    const opcaoNao = isResidencial.querySelector('[value = "false"]');
    opcaoNao.disabled = true;

    const fraseCurta = form.querySelector('[name = "fraseCurta"]');
    fraseCurta.placeholder = "Casa";

    return form;
}

const formEndereco = montarEnderecoObrigatorio();
const containerEndereco = document.getElementById('container-enderecos');
containerEndereco.append(formEndereco);

async function enviarCliente(){
    const form = document.getElementById('form-cadastrar-dados-pessoais');
    const cliente = montarClientePorForm(form);

    inserirCliente(cliente);
}

function adicionarEndereco(){
    const nEnderecosNaTela = document.querySelectorAll('.endereco').length + 1;
    const form = new FormularioEndereco();
    form.setNumeroTitulo(nEnderecosNaTela);
    containerEndereco.append(form);

}