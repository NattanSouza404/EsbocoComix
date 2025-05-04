import { atualizarSenha } from "/js/api/apiCliente.js";
import { Modal } from "/js/componentes/modal.js";

export class ModalAlterarSenha extends Modal {

    constructor() {
        const conteudoModal = ConteudoModalAlterarSenha();

        super('modal-alterar-senha', "Alterar Senha", conteudoModal);

        conteudoModal.insertAdjacentHTML('beforeend', `
            <button class="botao-salvar" type="button">Salvar</button>
        `);

        conteudoModal.querySelector('.botao-salvar').onclick = () => {
            this.enviarFormulario(this.cliente)
        }

        this.conteudoModal = conteudoModal;
    }

    atualizar(cliente) {
        this.cliente = cliente;
    }

    enviarFormulario(cliente) {
        const form = document.getElementById("alterar-senha");
        const formData = new FormData(form);

        const pedidoAlterarSenha = {
            cliente: {
                id: cliente.id
            },
            "senhaAntiga": formData.get('senhaAntiga'),
            "senhaNova": formData.get('senhaNova'),
            "senhaConfirmacao": formData.get('senhaConfirmacao')
        };

        atualizarSenha(pedidoAlterarSenha);
    }
}

function ConteudoModalAlterarSenha() {
    const form = document.createElement('form');
    form.id = 'alterar-senha';

    form.innerHTML = `
        <p class="aviso">
            A senha deve ser composta de pelo menos <br/>
            8 caracteres, ter letras maiúsculas e <br/>
            minúsculas além de conter caracteres especiais.
        </p>

        <div class="dados-formulario">
            <label>
                Senha Antiga
                <input name="senhaAntiga" type="password" autocomplete="password"></input>
            </label>

            <label>
                Nova Senha
                <input name="senhaNova" type="password" autocomplete="new-password"></input>
            </label>

            <label>
                Confirme a senha
                <input name="senhaConfirmacao" type="password" autocomplete="new-password"></input>
            </label>
        </div>
    `;

    return form;
}