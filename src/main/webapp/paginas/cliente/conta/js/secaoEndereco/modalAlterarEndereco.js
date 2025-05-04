import { montarEnderecoPorForm } from "/js/script.js";
import { atualizarEndereco } from "/js/api/apiEndereco.js";
import { Modal } from "/js/componentes/modal.js";
import { FormularioEndereco } from "/js/componentes/forms/formEndereco.js";

export class ModalAlterarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-alterar-endereco', "Alterar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.btn-atualizar').onclick = () => {
            this.enviarAtualizacao();
        };
    }

    show(endereco){
        this.conteudoModal.atualizar(endereco);
        super.show();
    }

    async enviarAtualizacao() {
        const endereco = montarEnderecoPorForm(this.conteudoModal);
        endereco.id = this.conteudoModal.endereco.id;
        atualizarEndereco(endereco);
    }

}

function ConteudoModal() {
    const form = new FormularioEndereco();
    form.id = 'alterar-endereco';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="btn-atualizar">Atualizar</button>
    `);

    return form;
}
