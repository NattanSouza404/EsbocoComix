import { montarEnderecoPorForm } from "../../../../script.js";
import { atualizarEndereco } from "@api/endereco.api.js";
import { Modal } from "@componentes/common/modal.js";
import { FormularioEndereco } from "@componentes/forms/formEndereco.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../../../../localStorage.js";

export class ModalAlterarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-alterar-endereco', "Alterar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        /** @type {HTMLButtonElement} */
        (this.conteudoModal.querySelector('.botao-salvar')).onclick = () => {
            this.enviarAtualizacao();
        };
    }

    show(endereco){
        this.conteudoModal.atualizar(endereco);
        super.show();
    }

    async enviarAtualizacao() {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse endereço?"); 

        if (!confirmacaoUsuario){
            return;
        }

        const endereco = montarEnderecoPorForm(this.conteudoModal);
        endereco.id = this.conteudoModal.endereco.id;
        endereco.idCliente = localStorage.getItem(localStorageKeys.idCliente);

        try {
            await atualizarEndereco(endereco);
            alert('Atualizado com sucesso!');
            window.location.reload();
        } catch (error){
            alertarErro(error);
        }

    }

}

function ConteudoModal() {
    const form = new FormularioEndereco();
    form.id = 'alterar-endereco';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="botao-salvar">Atualizar</button>
    `);

    return form;
}
