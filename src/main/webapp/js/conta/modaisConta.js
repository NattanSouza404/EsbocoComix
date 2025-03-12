import { Modal } from "../componentes/componentes.js";
import { BotaoFechar, BotaoSalvar } from "../componentes/componentes.js";
import { criarElemento, criarElementoInput, montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm} from "../script.js";
import { FormularioDadosPessoais } from "../componentes/forms.js";
import { atualizarCliente, atualizarEndereco, atualizarCartaoCredito } from "../api.js";
import { SecaoFormsEndereco } from "../componentes/secaoEndereco.js";
import { SecaoFormsCartaoCredito } from "../componentes/secaoCartaoCredito.js";

export class ModalAlterarDadosPessoais extends Modal {

    constructor(){
        super();

        this.id = 'modal-alterar-dados-pessoais';
        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        this.form = new FormularioDadosPessoais();
        this.form.id = 'alterar-dados-pessoais';

        this.form.append(new BotaoSalvar(
            () => {
                this.enviarAtualizacao(this.cliente);
            }
        ));

        conteudoModal.append(this.form);

        return conteudoModal;
    }

    atualizar(cliente){
        this.cliente = cliente;
        this.form.atualizar(cliente);
    }

    async enviarAtualizacao(){
        const form = document.getElementById("alterar-dados-pessoais");
        const cliente = montarClientePorForm(form);

        cliente.id = this.cliente.id;
        
        atualizarCliente(cliente);
    }
}

export class ModalAlterarSenha extends Modal {

    constructor(){
        super();
        this.id = 'modal-alterar-senha';
        this.append(this.initConteudoModal());
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        const form = document.createElement('form');
        form.id = 'alterar-senha';

        const aviso = document.createElement('p');
        aviso.className = 'aviso';
        aviso.innerHTML = 
            "A senha deve ser composta de pelo menos <br/>"+
            "8 caracteres, ter letras maiúsculas e <br/>"+
            "minúsculas além de conter caracteres especiais.";
        form.append(aviso);

        const dadosFormulario = document.createElement('div');
        dadosFormulario.className = 'dados-formulario';

        let label = criarElemento('label', 'Senha Antiga');
        let input = criarElementoInput('senhaAntiga', null, 'password');
        input.autocomplete = "password";
        dadosFormulario.append(label);
        dadosFormulario.append(input);

        label = criarElemento('label', 'Nova senha');
        input = criarElementoInput('senhaNova', null, 'password');
        input.autocomplete = "new-password";
        dadosFormulario.append(label);
        dadosFormulario.append(input);

        label = criarElemento('label', 'Confirme a senha');
        input = criarElementoInput('senhaConfirmacao', null, 'password');
        input.autocomplete = "new-password";
        dadosFormulario.append(label);
        dadosFormulario.append(input);

        form.append(dadosFormulario);

        form.append(new BotaoSalvar(
            () => this.enviarFormulario(this.cliente)
        ));

        conteudoModal.append(form);

        return conteudoModal;
    }

    atualizar(cliente){
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

        atualizarCliente(pedidoAlterarSenha, 'atualizarsenha');
    }
}

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
                this.enviarPedidoDeletarEndereco(form);
            };
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

    }

    criarBotaoAtualizar(form){
        const botaoAtualizar = criarElemento('button', 'Atualizar');
        botaoAtualizar.type = "button";
        botaoAtualizar.onclick = () => {
            this.enviarAtualizacao(form);
        };
        return botaoAtualizar;
    }

}

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
                this.enviarPedidoDeletar(form);
            };
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

    async enviarPedidoDeletar(form){

    }

    criarBotaoAtualizar(form){
        const botaoAtualizar = criarElemento('button', 'Atualizar');
        botaoAtualizar.type = "button";
        botaoAtualizar.onclick = () => {
            this.enviarAtualizacao(form);
        };
        return botaoAtualizar;
    }
}

customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);
customElements.define('alterar-senha', ModalAlterarSenha);
customElements.define('alterar-endereco', ModalAlterarEndereco);
customElements.define('alterar-cartao-credito', ModalAlterarCartaoCredito);