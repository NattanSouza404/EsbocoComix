import { BotaoFechar, Modal } from "../../componentes/componentes.js";
import { criarElemento, montarCartaoCreditoPorForm } from "../../script.js";
import { atualizarCartaoCredito, deletarCartaoCredito } from "../../api.js";
import { SecaoFormsCartaoCredito } from "../../componentes/secaoCartaoCredito.js";

export class ModalAlterarCartaoCredito extends Modal {
    constructor(cartoesCreditos){
        super();

        this.id = 'modal-alterar-cartao-credito';
        this.cartoesCreditos = cartoesCreditos;

        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        this.secaoForm = new SecaoFormsCartaoCredito();
        this.secaoForm.id = 'alterar-cartao-credito';

        this.secaoForm.buttonAddCartao.onclick = () => {
            const form = this.secaoForm.adicionarCartaoCredito();
            form.botaoRemover.onclick = () => {
                this.enviarPedidoDeletar(form);
            };
            form.botaoRemover.type = 'button';
            form.append(this.criarBotaoAtualizar(form));
        }

        conteudoModal.append(this.secaoForm);

        return conteudoModal;
    }

    atualizar(cartoes, cliente){
        this.cartoes = cartoes;
        this.cliente = cliente;

        this.secaoForm.container.textContent = '';

        cartoes.forEach(e => {
            const form = this.secaoForm.adicionarCartaoCredito();
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