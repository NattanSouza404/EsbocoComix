import { montarEnderecoPorForm } from "/js/script.js";
import { Modal } from "/js/componentes/modal.js";
import { FormularioEndereco } from "/js/componentes/forms/formEndereco.js";
import { inserirEndereco } from "/js/api/apiEndereco.js";
import { alertarErro } from "../../../../../js/api/alertErro.js";

export class ModalAdicionarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModal();

        super('modal-adicionar-endereco', "Adicionar Endereço", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('.botao-salvar').onclick = () => {
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

        endereco.idCliente = localStorage.getItem('idcliente');

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
