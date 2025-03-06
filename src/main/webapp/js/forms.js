function montarClientePorForm(form){
    const formData = new FormData(form);

    return {
        nome: formData.get("nome"),
        genero: formData.get("genero"),
        dataNascimento: formData.get("dataNascimento"),
        cpf: formData.get("cpf"),
        email: formData.get("email"),
        senha: formData.get("senha"),
        senhaConfirmacao: formData.get("senhaConfirmacao"),
        telefone: {
            tipo: formData.get("tipoTelefone"),
            ddd: formData.get("ddd"),
            numero: formData.get("numero"),
        }
    };
    
}

function montarEnderecoPorForm(form){
    const formData = new FormData(form);

    return {
        fraseCurta: formData.get('fraseCurta'),
        logradouro: formData.get('logradouro'),
        tipoLogradouro: formData.get('tipoLogradouro'),
        tipoResidencial: formData.get('tipoResidencial'),
        numero: formData.get('numero'),
        bairro: formData.get('bairro'),
        cep: formData.get('cep'),
        cidade: formData.get('cidade'),
        estado: formData.get('estado'),
        pais: formData.get('pais'),
        isResidencial: formData.get('isResidencial'),
        isEntrega: formData.get('isEntrega'),
        isCobranca: formData.get('isCobranca'),
        observacoes: formData.get('observacoes')
    };
    
}

class FormularioDadosPessoais extends HTMLFormElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';

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

class FormularioEndereco extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'endereco';
        const div = document.createElement('div');
        div.className = "numeracao";
        div.append(criarElemento('p', '1'));
        div.append(criarElemento('p', 'Endereço residencial'));
        this.append(div);

        const mainDiv = document.createElement('div');
        mainDiv.className = 'dados-endereco';
        this.append(mainDiv);

        mainDiv.append(criarElemento('label', 'Frase curta para identificação'));
        let input = document.createElement('input');
        input.name = 'fraseCurta';
        mainDiv.append(input);
        
        mainDiv.append(criarElemento('label', 'É um endereço residencial?'));
        let select = this.criarSelectSimOuNao();
        select.name = 'isResidencial';
        mainDiv.append(select);

        mainDiv.append(criarElemento('label', 'É um endereço de cobrança?'));
        select = this.criarSelectSimOuNao();
        select.name = 'isCobranca';
        mainDiv.append(select);

        mainDiv.append(criarElemento('label', 'É um endereço de entrega?'));
        select = this.criarSelectSimOuNao();
        select.name = 'isEntrega';
        mainDiv.append(select);

        mainDiv.append(criarElemento('label', 'Nº Endereço'));
        input = document.createElement('input');
        input.name = 'numero';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Logradouro'));
        input = document.createElement('input');
        input.name = 'logradouro';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Tipo de Logradouro'));
        select = document.createElement('select');
        select.name = "tipoLogradouro";
        mainDiv.append(select);
    
        ["Rua", "Avenida", "Praça"].forEach((tipoLogradouro) => {
            let option = criarElemento('option', tipoLogradouro);
            select.append(option);
        });

        mainDiv.append(criarElemento('label', 'CEP'));
        input = document.createElement('input');
        input.name = 'cep';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Bairro'));
        input = document.createElement('input');
        input.name = 'bairro';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Cidade'));
        input = document.createElement('input');
        input.name = 'cidade';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Estado'));
        input = document.createElement('input');
        input.name = 'estado';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'País'));
        input = document.createElement('input');
        input.name = 'pais';
        mainDiv.append(input);

        mainDiv.append(criarElemento('label', 'Observações'));
        input = document.createElement('input');
        input.name = 'observacoes';
        mainDiv.append(input);
    }

    criarSelectSimOuNao(){
        let select = document.createElement('select');

        let option = criarElemento('option', 'Não');
        option.value = 'false';
        select.append(option);
        option = criarElemento('option', 'Sim');
        option.value = 'true';
        select.append(option);

        return select;
    }
}

customElements.define('form-dados-endereco', FormularioEndereco, { extends: 'form'});