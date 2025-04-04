import { GENEROS, TIPOS_TELEFONE } from "../../dados.js";
import { criarElemento, criarElementoInput, formatarDataParaInput } from "../../script.js";

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

        GENEROS.forEach(genero => {
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

        select = document.createElement('select');
        select.name = "tipoTelefone";
        this.secaoTelefone.append(select);

        TIPOS_TELEFONE.forEach(genero => {
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
                let elemento = this.querySelector(`[name="${chave}"]`);

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

customElements.define('form-dados-pessoais', FormularioDadosPessoais, { extends: 'form'});