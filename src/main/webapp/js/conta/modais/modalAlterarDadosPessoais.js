import { BotaoFechar, BotaoSalvar, Modal } from "../../componentes/componentes.js";
import { montarClientePorForm } from "../../script.js";
import { atualizarCliente } from "../../api.js";
import { FormularioDadosPessoais } from "../../componentes/forms/formDadosPessoais.js";

export class ModalAlterarDadosPessoais extends Modal {

    constructor(){
        super();

        this.id = 'modal-alterar-dados-pessoais';
        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        this.form = new FormularioDadosPessoais();
        this.form.id = 'alterar-dados-pessoais';

        this.form.append(new BotaoSalvar(
            () => {
                this.enviarAtualizacao(this.cliente);
            }
        ));

        conteudoModal.append(this.form);

        return conteudoModal;
    }

    atualizar(cliente){
        this.cliente = cliente;
        this.form.atualizar(cliente);
    }

    async enviarAtualizacao(){
        const form = document.getElementById("alterar-dados-pessoais");
        const cliente = montarClientePorForm(form);

        cliente.id = this.cliente.id;
        
        atualizarCliente(cliente);
    }
}

customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);