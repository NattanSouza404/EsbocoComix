class SecaoCadastrarDadosPessoais extends HTMLElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';
        this.className = "secao-cadastrar";

        this.header = document.createElement('div');
        this.header.className = 'header-dados-cadastro';
        this.header.append(criarElemento('p', 'Dados Pessoais'));
        this.append(this.header);

        this.formularioDadosPessoais = document.createElement('div');
        this.formularioDadosPessoais.className = 'dados-formulario';
        this.append(this.formularioDadosPessoais);

        this.formularioDadosPessoais.append(criarElemento('label', "Nome"));
        let input = document.createElement('input');
        input.type = 'text';
        input.name = 'nome';
        input.placeholder = 'Seu Nome';
        this.formularioDadosPessoais.append(input);

        this.formularioDadosPessoais.append(criarElemento('label', "Gênero"));
        let select = document.createElement('select');
        select.name = 'genero';
        this.formularioDadosPessoais.append(select);

        const opcoesGenero = [
            "Masculino", "Feminino", "Outro", "Prefiro não informar"
        ];

        opcoesGenero.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        this.formularioDadosPessoais.append(criarElemento('label', "Data de Nascimento"));
        input = document.createElement('input');
        input.type = 'date';
        input.name = 'dataNascimento';
        this.formularioDadosPessoais.append(input);

        this.formularioDadosPessoais.append(criarElemento('label', "CPF"));
        input = document.createElement('input');
        input.type = 'text';
        input.name = 'cpf';
        input.placeholder = "111.111.111-11";
        this.formularioDadosPessoais.append(input);

        this.formularioDadosPessoais.append(criarElemento('label', "E-mail"));
        input = document.createElement('input');
        input.type = 'email';
        input.name = 'email';
        input.placeholder = "seuemail@email.com";
        this.formularioDadosPessoais.append(input);

        this.formularioDadosPessoais.append(criarElemento('label', "Senha"));
        input = document.createElement('input');
        input.type = 'password';
        input.name = 'senha';
        this.formularioDadosPessoais.append(input);

        this.formularioDadosPessoais.append(criarElemento('label', "Confirme a senha"));
        input = document.createElement('input');
        input.type = 'password';
        input.name = 'senhaConfirmacao';
        this.formularioDadosPessoais.append(input);

        this.header = document.createElement('div');
        this.header.className = 'header-dados-cadastro';
        this.header.append(criarElemento('p', 'Telefone'));
        this.append(this.header);

        this.formularioTelefone = document.createElement('div');
        this.formularioTelefone.className = 'dados-formulario';
        this.append(this.formularioTelefone);

        this.formularioTelefone.append(criarElemento('label', "Tipo do Telefone"));

        const opcoesTipoTelefone = [
            "Fixo", "Celular"
        ];

        select = document.createElement('select');
        select.name = "tipoTelefone";
        this.formularioTelefone.append(select);

        opcoesTipoTelefone.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        this.formularioTelefone.append(criarElemento('label', "DDD"));

        input = document.createElement('input');
        input.type = "text";
        input.name = "ddd";
        input.placeholder = "11";
        this.formularioTelefone.append(input);

        this.formularioTelefone.append(criarElemento('label', 'Número do Telefone'));

        input = document.createElement('input');
        input.type = "text";
        input.name = "numero";
        input.placeholder = "11111-1111";
        this.formularioTelefone.append(input);
    }

}

customElements.define('secao-cadastrar-dados-pessoais', SecaoCadastrarDadosPessoais);

const cadastrarCliente = document.getElementById('cadastrar-cliente');
cadastrarCliente.append(new SecaoCadastrarDadosPessoais());

function enviarCliente(){
    const form = document.getElementById('form-cadastrar');
    const formData = new FormData(form);

    const cliente = {
        nome: formData.get("nome"),
        genero: formData.get("genero"),
        dataNascimento: formData.get("dataNascimento"),
        cpf: formData.get("cpf"),
        email: formData.get("email"),
        senha: formData.get('senha'),
        senhaConfirmacao: formData.get('senhaConfirmacao'),
        telefone: {
            tipo: formData.get("tipoTelefone"),
            ddd: formData.get("ddd"),
            numero: formData.get("numero"),
        }
    };

    inserirCliente(cliente);
}