import { Modal } from "/js/componentes/modal.js";
import { montarCartaoCreditoPorForm } from "/js/script.js";
import { FormularioCartaoCredito } from "/js/componentes/forms/formCartaoCredito.js";
import { inserirCartaoCredito } from "/js/api/apiCartaoCredito.js";
import { alertarErro } from "../../../../../js/api/alertErro.js";

export class ModalAdicionarCartaoCredito extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-adicionar-cartao-credito', "Adicionar Cartão de Crédito", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.botao-salvar').onclick = async () => {
            await this.enviarFormulario();
        };
    }

    async enviarFormulario(){
        const cartao = montarCartaoCreditoPorForm(
            this.conteudoModal
        );

        cartao.idCliente = localStorage.getItem('idcliente');

        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse cartão de crédito?");

        if (!confirmacaoUsuario){
            return;
        }

        try {
            await inserirCartaoCredito(cartao);
            alert("Cadastrado com sucesso!");
            window.location.reload();
        } catch(error){
            alertarErro(error);
        }

    }

}

function ConteudoModal() {
    const form = new FormularioCartaoCredito();
    form.id = 'adicionar-cartao-credito';

    form.insertAdjacentHTML('beforeend', `
        <button type="button" class="botao-salvar">Salvar</button>
    `);

    return form;
}
