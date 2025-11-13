import { montarEnderecoPorForm } from "/js/script.js";
import { atualizarEndereco } from "../../../../../js/api/apiEndereco.js";
import { Modal } from "/js/componentes/modal.js";
import { FormularioEndereco } from "/js/componentes/forms/formEndereco.js";
import { alertarErro } from "../../../../../js/api/alertErro.js";

export class ModalAlterarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-alterar-endereco', "Alterar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.botao-salvar').onclick = () => {
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
        endereco.idCliente = localStorage.getItem('idcliente');

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
