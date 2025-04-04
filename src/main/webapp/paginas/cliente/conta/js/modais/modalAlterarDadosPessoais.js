import { montarClientePorForm } from "/js/script.js";
import { atualizarCliente } from "/js/api.js";
import { FormularioDadosPessoais } from "/js/componentes/forms/formDadosPessoais.js";
import { BotaoFechar } from "/js/componentes/botoes/BotaoFechar.js";
import { BotaoSalvar } from "/js/componentes/botoes/BotaoSalvar.js";
import { Modal } from "/js/componentes/componentes.js";

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