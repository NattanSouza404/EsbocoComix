import { Modal } from "../componentes/componentes.js";
import { BotaoFechar, BotaoSalvar } from "../componentes/componentes.js";
import { criarElemento, criarElementoInput, formatarDataParaInput, montarClientePorForm} from "../script.js";
import { FormularioDadosPessoais } from "../componentes/forms.js";
import { atualizarCliente } from "../api.js";
import { SecaoFormsEndereco } from "../componentes/secaoEndereco.js";
import { SecaoFormsCartaoCredito } from "../componentes/secaoCartaoCredito.js";

export class ModalAlterarSenha extends Modal {

    constructor(clienteAtual){
        super();

        this.id = 'modal-alterar-senha';
        this.clienteAtual = clienteAtual;

        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
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

        let label = criarElemento('label', 'Nova senha');

        let input = criarElementoInput('senha', null, 'password');
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
            () => this.enviarFormulario()
        ));

        conteudoModal.append(form);

        return conteudoModal;
    }

    enviarFormulario() {
        const form = document.getElementById("alterar-senha");
        const formData = new FormData(form);

        const clienteToUpdate = {
            "id": this.clienteAtual.id,
            "nome": this.clienteAtual.nome,
            "senha": formData.get('senha'),
            "senhaConfirmacao": formData.get('senhaConfirmacao')
        };

        atualizarCliente(clienteToUpdate, 'atualizarsenha');
    }
}

export class ModalAlterarDadosPessoais extends Modal {

    constructor(clienteAtual){
        super();

        this.id = 'modal-alterar-dados-pessoais';
        this.clienteAtual = clienteAtual;

        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);

        this.atualizar();
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

    atualizar(){
        Object.entries(this.clienteAtual).forEach(
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

        Object.entries(this.clienteAtual.telefone).forEach(
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

export class ModalAlterarEndereco extends Modal {

    constructor(enderecos){
        super();

        this.id = 'modal-alterar-endereco';
        this.enderecos = enderecos;

        this.conteudoModal = this.initConteudoModal();

        this.append(this.conteudoModal);
    }

    initConteudoModal(){
        const conteudoModal = document.createElement('div');
        conteudoModal.className = "conteudo-modal";

        conteudoModal.append(new BotaoFechar(
            () => this.toggleDisplay()
        ));

        const secaoForm = new SecaoFormsEndereco();
        secaoForm.id = 'alterar-endereco';

        secaoForm.append(new BotaoSalvar(
            () => {
                this.enviarAtualizacao(this.enderecos);
            }
        ));

        conteudoModal.append(secaoForm);

        return conteudoModal;
    }

    async enviarAtualizacao(enderecos){
        
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

customElements.define('alterar-senha', ModalAlterarSenha);
customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);
customElements.define('alterar-endereco', ModalAlterarEndereco);
customElements.define('alterar-cartao-credito', ModalAlterarCartaoCredito);