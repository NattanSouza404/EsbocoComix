import { montarClientePorForm } from "/js/script.js";
import { atualizarCliente } from "/js/api/apiCliente.js";
import { FormularioDadosPessoais } from "/js/componentes/forms/formDadosPessoais.js";
import { Modal } from "/js/componentes/modal.js";
import { alertarErro } from "../../../../../js/api/alertErro.js";

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
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar ?"); 

        if (!confirmacaoUsuario){
            return;
        }

        const cliente = montarClientePorForm(this.form);
        cliente.id = localStorage.getItem('idcliente');

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
