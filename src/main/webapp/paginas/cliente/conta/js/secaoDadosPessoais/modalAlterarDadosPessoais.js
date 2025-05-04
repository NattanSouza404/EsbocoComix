import { montarClientePorForm } from "/js/script.js";
import { atualizarCliente } from "/js/api/apiCliente.js";
import { FormularioDadosPessoais } from "/js/componentes/forms/formDadosPessoais.js";
import { Modal } from "/js/componentes/modal.js";

export class ModalAlterarDadosPessoais extends Modal {

    constructor(){
        const conteudoModal = ConteudoModalAlterarDadosPessoais();

        super('modal-alterar-dados-pessoais', "Editar Dados Pessoais", conteudoModal);

        conteudoModal.insertAdjacentHTML('beforeend', `
            <button class="botao-salvar" type="button">Salvar</button>
        `);

        conteudoModal.querySelector('.botao-salvar').onclick = () => {
            this.enviarAtualizacao();
        };

        this.form = conteudoModal;
    }

    atualizar(cliente){
        this.cliente = cliente;
        this.form.atualizar(cliente);
    }

    async enviarAtualizacao(){
        const cliente = montarClientePorForm(this.form);
        atualizarCliente(cliente);
    }
}

function ConteudoModalAlterarDadosPessoais(){
    const form = new FormularioDadosPessoais();
    form.id = 'alterar-dados-pessoais';
    return form;
}
