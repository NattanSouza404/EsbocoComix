class FormularioDadosPessoais extends HTMLFormElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';
        this.className = "secao-cadastrar";

        this.header = document.createElement('div');
        this.header.className = 'header-dados-cadastro';
        this.header.append(criarElemento('p', 'Dados Pessoais'));
        this.append(this.header);

        this.secaoDadosPessoais = document.createElement('div');
        this.secaoDadosPessoais.className = 'dados-formulario';
        this.append(this.secaoDadosPessoais);

        this.secaoDadosPessoais.append(criarElemento('label', "Nome"));
        let input = document.createElement('input');
        input.type = 'text';
        input.name = 'nome';
        input.placeholder = 'Seu Nome';
        this.secaoDadosPessoais.append(input);

        this.secaoDadosPessoais.append(criarElemento('label', "Gênero"));
        let select = document.createElement('select');
        select.name = 'genero';
        this.secaoDadosPessoais.append(select);

        const opcoesGenero = [
            "Masculino", "Feminino", "Outro", "Prefiro não informar"
        ];

        opcoesGenero.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        this.secaoDadosPessoais.append(criarElemento('label', "Data de Nascimento"));
        input = document.createElement('input');
        input.type = 'date';
        input.name = 'dataNascimento';
        this.secaoDadosPessoais.append(input);

        this.secaoDadosPessoais.append(criarElemento('label', "CPF"));
        input = document.createElement('input');
        input.type = 'text';
        input.name = 'cpf';
        input.placeholder = "111.111.111-11";
        this.secaoDadosPessoais.append(input);

        this.secaoDadosPessoais.append(criarElemento('label', "E-mail"));
        input = document.createElement('input');
        input.type = 'email';
        input.name = 'email';
        input.placeholder = "seuemail@email.com";
        this.secaoDadosPessoais.append(input);

        this.header = document.createElement('div');
        this.header.className = 'header-dados-cadastro';
        this.header.append(criarElemento('p', 'Telefone'));
        this.append(this.header);

        this.secaoTelefone = document.createElement('div');
        this.secaoTelefone.className = 'dados-formulario';
        this.append(this.secaoTelefone);

        this.secaoTelefone.append(criarElemento('label', "Tipo do Telefone"));

        const opcoesTipoTelefone = [
            "Fixo", "Celular"
        ];

        select = document.createElement('select');
        select.name = "tipoTelefone";
        this.secaoTelefone.append(select);

        opcoesTipoTelefone.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        this.secaoTelefone.append(criarElemento('label', "DDD"));

        input = document.createElement('input');
        input.type = "text";
        input.name = "ddd";
        input.placeholder = "11";
        this.secaoTelefone.append(input);

        this.secaoTelefone.append(criarElemento('label', 'Número do Telefone'));

        input = document.createElement('input');
        input.type = "text";
        input.name = "numero";
        input.placeholder = "11111-1111";
        this.secaoTelefone.append(input);
    }
}

customElements.define('form-dados-pessoais', FormularioDadosPessoais, { extends: 'form'});