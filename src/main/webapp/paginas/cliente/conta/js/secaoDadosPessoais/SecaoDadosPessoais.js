import { CartaoDadosPessoais } from "./CartaoDadosPessoais.js";
import { ModalAlterarDadosPessoais } from "./modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./modalAlterarSenha.js";

export class SecaoDadosPessoais {
    constructor(cliente){
        this.elementoHTML = document.getElementById('secao-dados-pessoais');
        this.container = this.elementoHTML.querySelector('.container');

        this.cartaoDadosPessoais = new CartaoDadosPessoais();
        this.container.append(this.cartaoDadosPessoais);

        this.modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
        this.modalAlterarSenha = new ModalAlterarSenha();

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
