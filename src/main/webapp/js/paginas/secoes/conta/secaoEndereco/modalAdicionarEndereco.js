import { montarEnderecoPorForm } from "../../../../script.js";
import { Modal } from "@componentes/common/modal.js";
import { FormularioEndereco } from "@componentes/forms/formEndereco.js";
import { inserirEndereco } from "@api/endereco.api.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../../../../localStorage.js";

export class ModalAdicionarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-adicionar-endereco', "Adicionar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        /** @type {HTMLButtonElement} */
        (this.conteudoModal.querySelector('.botao-salvar')).onclick = () => {
            this.enviarFormulario()
        };
        
    }

    async enviarFormulario(){
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse endereço?");
        
        if (!confirmacaoUsuario){
            return;
        }

        const endereco = montarEnderecoPorForm(
            this.conteudoModal
        );

        endereco.idCliente = localStorage.getItem(localStorageKeys.idCliente);

        try {
            await inserirEndereco(endereco);
            alert('Cadastrado com sucesso');
            window.location.reload();
        } catch (error){
            alertarErro(error);
        }
        
    }

}

function ConteudoModal() {
    const form = new FormularioEndereco();
    form.id = 'adicionar-endereco';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="botao-salvar">Salvar</button>
    `);

    return form;
}
