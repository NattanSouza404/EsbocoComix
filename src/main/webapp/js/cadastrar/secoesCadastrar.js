import { FormularioDadosPessoais } from '../forms.js';
import { criarElemento, criarElementoInput } from "../script.js";

export class SecaoCadastrarDadosPessoais extends HTMLElement {
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