import { montarCartaoCreditoPorForm } from "../../../../script.js";
import { atualizarCartaoCredito } from "@api/cartaoCredito.api.js";;
import { Modal } from "@componentes/common/modal.js";
import { FormularioCartaoCredito } from "@componentes/forms/formCartaoCredito.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../../../../localStorage.js";

export class ModalAlterarCartaoCredito extends Modal {
    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-alterar-cartao-credito', "Alterar Cartão Crédito", conteudoModal);

        this.conteudoModal = conteudoModal;

        /** @type {HTMLButtonElement} */
        (this.conteudoModal.querySelector('.botao-salvar')).onclick = async () => {
            await this.enviarAtualizacao();
        }; 
    }

    show(cartaoCredito){
        this.conteudoModal.atualizar(cartaoCredito);
        super.show();
    }

    async enviarAtualizacao(){
        const cartao = montarCartaoCreditoPorForm(this.conteudoModal);
        cartao.id = this.conteudoModal.cartaoCredito.id;

        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse cartão de crédito?"); 

        if (!confirmacaoUsuario){
            return;
        }

        try {
            cartao.idCliente = localStorage.getItem(localStorageKeys.idCliente);

            await atualizarCartaoCredito(cartao);
            
            alert("Atualizado com sucesso!");

            window.location.reload();
        } catch (error){
            alertarErro(error);
        }

    }

}

function ConteudoModal() {
    const form = new FormularioCartaoCredito();
    form.id = 'alterar-cartao-credito';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="botao-salvar">Salvar</button>
    `);

    return form;
}