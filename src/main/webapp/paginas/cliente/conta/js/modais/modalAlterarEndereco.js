import { BotaoFechar, Modal } from "/js/componentes/componentes.js";
import { criarElemento, montarEnderecoPorForm} from "/js/script.js";
import { atualizarEndereco, deletarEndereco } from "/js/api.js";
import { SecaoFormsEndereco } from "/js/componentes/secaoEndereco.js";

export class ModalAlterarEndereco extends Modal {

    constructor(){
        super();

        this.id = 'modal-alterar-endereco';
        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        this.secaoForm = new SecaoFormsEndereco();
        this.secaoForm.id = 'alterar-endereco';

        this.secaoForm.botaoAddEndereco.onclick = () => {
            const form = this.secaoForm.adicionarEndereco();
            form.botaoRemover.onclick = () => {
                this.enviarPedidoDeletarEndereco(form);
            };
            form.botaoRemover.type = 'button';
            form.botaoRemover.className = 'btn-remover';
            form.append(this.criarBotaoAtualizar(form));
        }

        conteudoModal.append(this.secaoForm);

        return conteudoModal;
    }
    
    atualizar(enderecos, cliente){
        this.enderecos = enderecos;
        this.cliente = cliente;

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

    async enviarAtualizacao(form){
        const endereco = montarEnderecoPorForm(form);
        if (form.endereco != null){
            endereco.id = form.endereco.id;
        }
        endereco.idCliente = this.cliente.id;
        atualizarEndereco(endereco);
    }

    async enviarDelecao(form){
        const endereco = montarEnderecoPorForm(form);
        if (form.endereco != null){
            endereco.id = form.endereco.id;
        }
        deletarEndereco(endereco);
    }

    criarBotaoAtualizar(form){
        const botaoAtualizar = criarElemento('button', 'Atualizar');
        botaoAtualizar.type = "button";
        botaoAtualizar.className = "btn-atualizar";
        botaoAtualizar.onclick = () => {
            this.enviarAtualizacao(form);
        };
        return botaoAtualizar;
    }

}

customElements.define('alterar-endereco', ModalAlterarEndereco);