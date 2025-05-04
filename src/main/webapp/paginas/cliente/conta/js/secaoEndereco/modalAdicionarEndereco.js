import { montarEnderecoPorForm } from "/js/script.js";
import { Modal } from "/js/componentes/modal.js";
import { FormularioEndereco } from "/js/componentes/forms/formEndereco.js";
import { inserirEndereco } from "/js/api/apiEndereco.js";

export class ModalAdicionarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-adicionar-endereco', "Adicionar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.btn-salvar').onclick = () => {
            this.enviarFormulario()
        };
        
    }

    enviarFormulario(){
        const endereco = montarEnderecoPorForm(
            this.conteudoModal
        );

        endereco.idCliente = localStorage.getItem('idcliente');

        inserirEndereco(endereco);
    }

}

function ConteudoModal() {
    const form = new FormularioEndereco();
    form.id = 'adicionar-endereco';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="btn-salvar">Salvar</button>
    `);

    return form;
}
