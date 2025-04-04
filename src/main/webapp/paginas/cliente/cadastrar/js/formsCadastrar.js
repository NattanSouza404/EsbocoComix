import { FormularioDadosPessoais } from "../../../../js/componentes/forms/formDadosPessoais.js";
import { criarElemento, criarElementoInput } from "../../../../js/script.js";

export class FormularioCadastrarDadosPessoais extends FormularioDadosPessoais {
    constructor(){
        super();

        const header = document.createElement('div');
        header.className = 'header-dados-cadastro';
        header.append(criarElemento('p', 'Senha'));
        this.append(header);

        this.secaoSenha = document.createElement('div');
        this.secaoSenha.className = 'dados-formulario';

        this.secaoSenha.append(criarElemento('label', "Senha"));
        this.secaoSenha.append(criarElementoInput('senhaNova', null, 'password'));

        this.secaoSenha.append(criarElemento('label', "Confirme a senha"));
        this.secaoSenha.append(criarElementoInput('senhaConfirmacao', null, 'password'));
        
        this.append(this.secaoSenha);
    }

}

customElements.define('form-cadastrar-dados-pessoais', FormularioCadastrarDadosPessoais, {extends: "form"});