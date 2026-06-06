import { alertarErro } from "@api/alertErro.js";
import { atualizarSenha } from "@api/cliente.api.js";
import { Modal } from "@componentes/common/modal.js";

export class ModalAlterarSenha extends Modal {

    constructor() {
        const conteudoModal = ConteudoModalAlterarSenha();

        super('modal-alterar-senha', "Alterar Senha", conteudoModal);

        /** @type {HTMLButtonElement} */
        (conteudoModal.querySelector('.botao-salvar')).onclick = async () => {
            await this.enviarFormulario(this.cliente);
        }

        this.conteudoModal = conteudoModal;
    }

    atualizar(cliente) {
        this.cliente = cliente;
    }

    async enviarFormulario(cliente) {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar?"); 

        if (!confirmacaoUsuario){
            return;
        }

        const form = /** @type {HTMLFormElement} */
            (document.getElementById("alterar-senha"));

        const formData = new FormData(form);

        const pedidoAlterarSenha = {
            cliente: {
                id: cliente.id
            },
            "senhaAntiga": formData.get('senhaAntiga'),
            "senhaNova": formData.get('senhaNova'),
            "senhaConfirmacao": formData.get('senhaConfirmacao')
        };

        try {
            await atualizarSenha(pedidoAlterarSenha);
            alert('Atualizado com sucesso!');
            window.location.reload();
        } catch (error){
            alertarErro(error);
        }
        
    }
}

function ConteudoModalAlterarSenha() {
    const form = document.createElement('form');
    form.id = 'alterar-senha';

    form.innerHTML = /* html */`
        <p class="aviso">
            A senha deve ser composta de pelo menos <br/>
            8 caracteres, ter letras maiúsculas e <br/>
            minúsculas além de conter caracteres especiais.
        </p>

        <div class="dados-formulario">
            <label>
                Senha Antiga
                <input
                    name="senhaAntiga"
                    type="password"
                    autocomplete="password"
                >
            </label>

            <label>
                Nova Senha
                <input
                    name="senhaNova"
                    type="password"
                    autocomplete="new-password"
                >
            </label>

            <label>
                Confirme a senha
                <input
                    name="senhaConfirmacao"
                    type="password"
                    autocomplete="new-password"
                >
            </label>
        </div>

        <button class="botao-salvar" type="button">Salvar</button>
    `;

    return form;
}