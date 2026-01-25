import { montarClientePorForm } from "../../../../script.js";
import { atualizarCliente } from "@api/cliente.api.js";
import { FormularioDadosPessoais } from "@componentes/forms/formDadosPessoais.js";
import { Modal } from "@componentes/modal.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../../../../localStorage.js";

export class ModalAlterarDadosPessoais extends Modal {

    constructor(){
        const conteudoModal = ConteudoModalAlterarDadosPessoais();

        super('modal-alterar-dados-pessoais', "Editar Dados Pessoais", conteudoModal);

        conteudoModal.insertAdjacentHTML('beforeend', `
            <button class="botao-salvar" type="button">Salvar</button>
        `);

        /** @type {HTMLButtonElement} */
        (conteudoModal.querySelector('.botao-salvar')).onclick = () => {
            this.enviarAtualizacao();
        };

        this.form = conteudoModal;
    }

    atualizar(cliente){
        this.cliente = cliente;
        this.form.atualizar(cliente);
    }

    async enviarAtualizacao(){
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar ?"); 

        if (!confirmacaoUsuario){
            return;
        }

        const cliente = montarClientePorForm(this.form);
        cliente.id = localStorage.getItem(localStorageKeys.idCliente);

        try {
            await atualizarCliente(cliente);
            alert('Atualizado com sucesso!');
            window.location.reload();
        } catch (error){
            alertarErro(error);
        }
    }
}

function ConteudoModalAlterarDadosPessoais(){
    const form = new FormularioDadosPessoais();
    form.id = 'alterar-dados-pessoais';
    return form;
}
