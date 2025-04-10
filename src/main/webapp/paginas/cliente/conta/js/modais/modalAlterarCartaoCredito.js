import { criarElemento, montarCartaoCreditoPorForm } from "/js/script.js";
import { atualizarCartaoCredito, deletarCartaoCredito } from "/js/api/apiCartaoCredito.js";;
import { SecaoFormsCartaoCredito } from "/js/componentes/secaoCartaoCredito.js";
import { BotaoFechar } from "/js/componentes/botoes/BotaoFechar.js";
import { Modal } from "/js/componentes/modal.js";

export class ModalAlterarCartaoCredito extends Modal {
    constructor(){
        const conteudoModal = ConteudoModalAlterarCartaoCredito();

        super('modal-alterar-cartao-credito', "Alterar Cartão Crédito", conteudoModal);

        conteudoModal.buttonAddCartao.onclick = () => {
            const form = conteudoModal.adicionarCartaoCredito();
            form.botaoRemover.onclick = () => {
                this.enviarDelecao(form);
            };
            form.botaoRemover.type = 'button';
            form.append(this.criarBotaoAtualizar(form));
        }

        this.conteudoModal = conteudoModal;   
    }

    atualizar(cartoes, cliente){
        this.cartoes = cartoes;
        this.cliente = cliente;

        this.conteudoModal.container.textContent = '';

        cartoes.forEach(e => {
            const form = this.conteudoModal.adicionarCartaoCredito();
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
        const cartao = montarCartaoCreditoPorForm(form);
        if (form.cartaoCredito != null){
            cartao.id = form.cartaoCredito.id;
        }
        cartao.idCliente = this.cliente.id;
        atualizarCartaoCredito(cartao);
    }

    async enviarDelecao(form){
        const cartaoCredito = montarCartaoCreditoPorForm(form);
        if (form.cartaoCredito != null){
            cartaoCredito.id = form.cartaoCredito.id;
        }
        deletarCartaoCredito(cartaoCredito);
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

customElements.define('alterar-cartao-credito', ModalAlterarCartaoCredito);

function ConteudoModalAlterarCartaoCredito() {
    const secaoForm = new SecaoFormsCartaoCredito();
    secaoForm.id = 'alterar-cartao-credito';
    return secaoForm;
}