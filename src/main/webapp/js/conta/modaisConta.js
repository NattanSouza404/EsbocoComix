import { Modal } from "../componentes/componentes.js";
import { BotaoFechar, BotaoSalvar } from "../componentes/componentes.js";
import { criarElemento, criarElementoInput, formatarDataParaInput, montarClientePorForm, montarEnderecoPorForm} from "../script.js";
import { FormularioDadosPessoais, FormularioEndereco } from "../componentes/forms.js";
import { atualizarCliente, atualizarEndereco } from "../api.js";
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

        const form = new FormularioDadosPessoais();
        form.id = 'alterar-dados-pessoais';

        form.append(new BotaoSalvar(
            () => {
                this.enviarAtualizacao(this.clienteAtual);
            }
        ));

        conteudoModal.append(form);

        return conteudoModal;
    }

    atualizar(cliente){
        this.clienteAtual = cliente;
        Object.entries(cliente).forEach(
            ([chave, valor]) => {
                let elemento = document.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                if (chave === 'dataNascimento'){
                    elemento.value = formatarDataParaInput(valor);
                    return;
                }

                elemento.value = valor;
            }
        );

        Object.entries(cliente.telefone).forEach(
            ([chave, valor]) => {
                let elemento = document.querySelector(`[name="${chave}"]`);

                if (elemento){
                    elemento.value = valor;
                }
            }
        );
    }

    async enviarAtualizacao(){
        const form = document.getElementById("alterar-dados-pessoais");
        const cliente = montarClientePorForm(form);

        cliente.id = this.clienteAtual.id;
        
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
            () => this.enviarFormulario(this.clienteAtual)
        ));

        conteudoModal.append(form);

        return conteudoModal;
    }

    atualizar(cliente){
        this.clienteAtual = cliente;
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
            const botaoAtualizar = criarElemento('button', 'Atualizar');
            botaoAtualizar.type = "button";
            botaoAtualizar.onclick = () => {
                this.enviarAtualizacao(form);
            };
            form.append(botaoAtualizar);
        }

        conteudoModal.append(this.secaoForm);

        return conteudoModal;
    }
    
    atualizar(enderecos){
        this.enderecos = enderecos;

        this.secaoForm.container.textContent = '';

        enderecos.forEach(e => {
            const form = new FormularioEndereco();
            this.secaoForm.container.append(form);

            form.botaoRemover.onclick = () => {
                this.enviarPedidoDeletarEndereco(form);
            };

            const botaoAtualizar = criarElemento('button', 'Atualizar');
            botaoAtualizar.type = "button";
            botaoAtualizar.onclick = () => {
                this.enviarAtualizacao(form);
            };
            form.append(botaoAtualizar);

            form.atualizar(e);
        });
    }

    async enviarAtualizacao(form){
        const endereco = montarEnderecoPorForm(form);
        if (form.endereco != null){
            endereco.id = form.endereco.id;
            endereco.idCliente = form.endereco.idCliente;
        } else {
            const uRLSearchParams = new URLSearchParams(window.location.search);
            endereco.idCliente = uRLSearchParams.get('idcliente');
        }
        atualizarEndereco(endereco);
    }

    async enviarDelecao(form){

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

        const secaoForm = new SecaoFormsCartaoCredito();
        secaoForm.id = 'alterar-cartao-credito';

        secaoForm.append(new BotaoSalvar(
            () => {
                this.enviarAtualizacao(this.cartoesCreditos);
            }
        ));

        conteudoModal.append(secaoForm);

        return conteudoModal;
    }

    async enviarAtualizacao(cartoesCreditos){
        
    }
}

customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);
customElements.define('alterar-senha', ModalAlterarSenha);
customElements.define('alterar-endereco', ModalAlterarEndereco);
customElements.define('alterar-cartao-credito', ModalAlterarCartaoCredito);