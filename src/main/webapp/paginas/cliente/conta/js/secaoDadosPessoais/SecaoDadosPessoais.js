import { CartaoDadosPessoais } from "./CartaoDadosPessoais.js";
import { ModalAlterarDadosPessoais } from "./modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./modalAlterarSenha.js";

export class SecaoDadosPessoais extends HTMLElement {
    constructor(cliente){
        super();

        this.id = 'secao-dados-pessoais';
        this.style.display = 'block';

        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
        this.modalAlterarSenha = new ModalAlterarSenha();

        this.cartaoDadosPessoais = new CartaoDadosPessoais();
        this.append(this.cartaoDadosPessoais);

        this.insertAdjacentHTML('beforeend', `
            <div>
                <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                    data-bs-target="#modal-alterar-dados-pessoais">
                    Editar Cadastro
                </button>
                <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                    data-bs-target="#modal-alterar-senha">
                    Alterar Senha
                </button>
            </div>
        `);

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