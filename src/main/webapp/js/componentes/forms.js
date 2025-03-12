import { criarElemento, criarElementoInput, criarSelectSimOuNao, formatarDataParaInput } from "../script.js";

export class FormularioDadosPessoais extends HTMLFormElement {
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
        this.secaoDadosPessoais.append(criarElementoInput('nome', 'Seu nome'));

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
        this.secaoDadosPessoais.append(criarElementoInput('dataNascimento', null, 'date'));

        this.secaoDadosPessoais.append(criarElemento('label', "CPF"));
        this.secaoDadosPessoais.append(criarElementoInput('cpf', '111.111.111-11'));

        this.secaoDadosPessoais.append(criarElemento('label', "E-mail"));
        this.secaoDadosPessoais.append(criarElementoInput('email', 'seuemail@email.com', 'email'));

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
        this.secaoTelefone.append(criarElementoInput('ddd', '11'));

        this.secaoTelefone.append(criarElemento('label', 'Número do Telefone'));
        this.secaoTelefone.append(criarElementoInput('numero', '11111-1111'));
    }

    atualizar(cliente){
        this.cliente = cliente;
        Object.entries(cliente).forEach(
            ([chave, valor]) => {
                let elemento = document.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                if (chave === 'dataNascimento'){
                    elemento.value = formatarDataParaInput(valor);
                    return;
                }

                elemento.value = valor;
            }
        );

        Object.entries(cliente.telefone).forEach(
            ([chave, valor]) => {
                let elemento = document.querySelector(`[name="${chave}"]`);

                if (elemento){
                    elemento.value = valor;
                }
            }
        );
    }
}

export class FormularioEndereco extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'endereco';
        const div = document.createElement('div');
        div.className = "numeracao";

        this.numeroTitulo = criarElemento('p', '1');
        div.append(this.numeroTitulo);
        div.append(criarElemento('p', 'Endereço'));
        this.append(div);

        const mainDiv = document.createElement('div');
        mainDiv.className = 'dados-endereco';
        this.append(mainDiv);

        mainDiv.append(criarElemento('label', 'Frase curta para identificação'));
        mainDiv.append(criarElementoInput('fraseCurta'));
        
        mainDiv.append(criarElemento('label', 'É um endereço residencial?'));
        mainDiv.append(criarSelectSimOuNao('isResidencial'));

        mainDiv.append(criarElemento('label', 'É um endereço de cobrança?'));
        mainDiv.append(criarSelectSimOuNao('isCobranca'));

        mainDiv.append(criarElemento('label', 'É um endereço de entrega?'));
        mainDiv.append(criarSelectSimOuNao('isEntrega'));

        mainDiv.append(criarElemento('label', 'Tipo de Logradouro'));
        let select = document.createElement('select');
        select.name = "tipoResidencial";
        mainDiv.append(select);

        ["Casa", "Apartamento"].forEach((tipoResidencial) => {
            let option = criarElemento('option', tipoResidencial);
            select.append(option);
        });

        mainDiv.append(criarElemento('label', 'Nº Endereço'));
        mainDiv.append(criarElementoInput('numero'));

        mainDiv.append(criarElemento('label', 'Logradouro'));
        mainDiv.append(criarElementoInput('logradouro'));

        mainDiv.append(criarElemento('label', 'Tipo de Logradouro'));
        select = document.createElement('select');
        select.name = "tipoLogradouro";
        mainDiv.append(select);
    
        ["Rua", "Avenida", "Praça"].forEach((tipoLogradouro) => {
            let option = criarElemento('option', tipoLogradouro);
            select.append(option);
        });

        mainDiv.append(criarElemento('label', 'CEP'));
        mainDiv.append(criarElementoInput('cep', '00000-000'));

        mainDiv.append(criarElemento('label', 'Bairro'));
        mainDiv.append(criarElementoInput('bairro'));

        mainDiv.append(criarElemento('label', 'Cidade'));
        mainDiv.append(criarElementoInput('cidade'));

        mainDiv.append(criarElemento('label', 'Estado'));
        mainDiv.append(criarElementoInput('estado'));

        mainDiv.append(criarElemento('label', 'País'));
        mainDiv.append(criarElementoInput('pais'));

        mainDiv.append(criarElemento('label', 'Observações'));
        mainDiv.append(criarElementoInput('observacoes', 'Perto do prédio...'));

        this.botaoRemover = criarElemento('button', 'Remover');
        this.botaoRemover.type = 'button';
        this.botaoRemover.onclick = () => {
            if (this.parentNode){
                this.parentNode.removeChild(this);
            }
        };
        this.append(this.botaoRemover);
    }

    setNumeroTitulo(numero){
        this.numeroTitulo.textContent = numero;
    }

    atualizar(endereco){
        this.endereco = endereco;

        Object.entries(this.endereco).forEach(
            ([chave, valor]) => {
                let elemento = this.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                elemento.value = valor;
            }
        );
    }
}

export class FormularioCartaoCredito extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'cartao-credito';
        const div = document.createElement('div');
        div.className = "numeracao";

        this.numeroTitulo = criarElemento('p', '1');
        div.append(this.numeroTitulo);
        div.append(criarElemento('p', 'Cartão de Crédito'));
        this.append(div);

        const mainDiv = document.createElement('div');
        mainDiv.className = 'dados-cartao-credito';
        this.append(mainDiv);

        mainDiv.append(criarElemento('label', 'Número Cartão de Crédito'));
        mainDiv.append(criarElementoInput('numero'));
        
        mainDiv.append(criarElemento('label', 'Nome Impresso?'));
        mainDiv.append(criarElementoInput('nomeImpresso'));

        mainDiv.append(criarElemento('label', 'Código de Segurança'));
        mainDiv.append(criarElementoInput('codigoSeguranca'));

        mainDiv.append(criarElemento('label', 'É preferencial?'));
        mainDiv.append(criarSelectSimOuNao('isPreferencial'));

        mainDiv.append(criarElemento('label', 'Bandeira do Cartão'));
        let select = document.createElement('select');
        select.name = "bandeiraCartao";
        mainDiv.append(select);

        ["Mastercard", "Visa"].forEach((bandeiraCartao) => {
            let option = criarElemento('option', bandeiraCartao);
            select.append(option);
        });

        const botaoRemover = criarElemento('button', 'Remover');
        botaoRemover.onclick = () => {
            if (this.parentNode){
                this.parentNode.removeChild(this);
            }
        };
        this.append(botaoRemover);
    }

    setNumeroTitulo(numero){
        this.numeroTitulo.textContent = numero;
    }
}

customElements.define('form-dados-pessoais', FormularioDadosPessoais, { extends: 'form'});
customElements.define('form-dados-endereco', FormularioEndereco, { extends: 'form'});
customElements.define('form-dados-cartao-credito', FormularioCartaoCredito, { extends: 'form'});