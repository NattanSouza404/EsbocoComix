import { CartaoDadosPessoais } from "./CartaoDadosPessoais.js";
import { ModalAlterarDadosPessoais } from "./modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./modalAlterarSenha.js";

export class SecaoDadosPessoais extends HTMLElement {
    constructor(cliente){
        super();

        this.id = 'secao-dados-pessoais';
        this.style.display = 'block';

        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
        document.body.append(this.modalAlterarDadosPessoais);

        this.modalAlterarSenha = new ModalAlterarSenha();
        document.body.append(this.modalAlterarSenha);

        this.cartaoDadosPessoais = new CartaoDadosPessoais();
        this.append(this.cartaoDadosPessoais);

        const div = document.createElement('div');

        div.innerHTML = `
        <button id="btn-editar-cadastro" onclick="toggleDisplay('modal-alterar-dados-pessoais')">Editar Cadastro</button>
            <button id="btn-alterar-senha" onclick="toggleDisplay('modal-alterar-senha')">Alterar senha</button>
        `;

        this.append(div);

        this.atualizar(cliente);
    }

    atualizar(cliente){
        if (cliente){
            this.modalAlterarDadosPessoais.atualizar(cliente);
            this.modalAlterarSenha.atualizar(cliente);
            this.cartaoDadosPessoais.atualizar(cliente);
        }

    }

}

customElements.define('secao-dados-pessoais', SecaoDadosPessoais, {extends:'section'});