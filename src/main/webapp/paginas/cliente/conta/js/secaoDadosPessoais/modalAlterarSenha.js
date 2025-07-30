import { alertarErro } from "../../../../../js/api/alertErro.js";
import { atualizarSenha } from "/js/api/apiCliente.js";
import { Modal } from "/js/componentes/modal.js";

export class ModalAlterarSenha extends Modal {

    constructor() {
        const conteudoModal = ConteudoModalAlterarSenha();

        super('modal-alterar-senha', "Alterar Senha", conteudoModal);

        conteudoModal.querySelector('.botao-salvar').onclick = async () => {
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

        <button class="botao-salvar" type="button">Salvar</button>
    `;

    return form;
}