import { criarElemento, montarEnderecoPorForm } from "/js/script.js";
import { atualizarEndereco, deletarEndereco } from "/js/api/apiEndereco.js";
import { SecaoFormsEndereco } from "/js/componentes/secaoEndereco.js";
import { Modal } from "/js/componentes/modal.js";

export class ModalAlterarEndereco extends Modal {

    constructor() {
        const conteudoModal = ConteudoModalAlterarEndereco();

        super('modal-alterar-endereco', "Alterar Endereço", conteudoModal);

        conteudoModal.botaoAddEndereco.onclick = () => {
            const form = conteudoModal.adicionarEndereco();
            form.botaoRemover.onclick = () => {
                this.enviarDelecao(form);
            };
            form.botaoRemover.type = 'button';
            form.botaoRemover.className = 'btn-remover';
            form.append(this.criarBotaoAtualizar(form));
        };

        this.secaoForm = conteudoModal;
    }

    atualizar(enderecos) {
        this.enderecos = enderecos;

        this.secaoForm.container.textContent = '';

        enderecos.forEach(e => {
            const form = this.secaoForm.adicionarEndereco();
            form.botaoRemover.onclick = () => {
                this.enviarDelecao(form);
            };
            form.botaoRemover.type = 'button';
            form.botaoRemover.className = 'btn-remover';
            form.append(this.criarBotaoAtualizar(form));
            form.atualizar(e);
        });
    }

    async enviarAtualizacao(form) {
        const endereco = montarEnderecoPorForm(form);
        if (form.endereco != null) {
            endereco.id = form.endereco.id;
        }
        atualizarEndereco(endereco);
    }

    async enviarDelecao(form) {
        const endereco = montarEnderecoPorForm(form);
        if (form.endereco != null) {
            endereco.id = form.endereco.id;
        }
        deletarEndereco(endereco);
    }

    criarBotaoAtualizar(form) {
        const botaoAtualizar = criarElemento('button', 'Atualizar');
        botaoAtualizar.type = "button";
        botaoAtualizar.className = "btn-atualizar";
        botaoAtualizar.onclick = () => {
            this.enviarAtualizacao(form);
        };
        return botaoAtualizar;
    }

}

function ConteudoModalAlterarEndereco() {
    const secaoForm = new SecaoFormsEndereco();
    secaoForm.id = 'alterar-endereco';
    return secaoForm;
}
