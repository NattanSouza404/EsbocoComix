import { criarElemento, criarElementoInput } from "/js/script.js";
import { atualizarCliente } from "/js/api.js";
import { BotaoFechar } from "/js/componentes/botoes/BotaoFechar.js";
import { BotaoSalvar } from "/js/componentes/botoes/BotaoSalvar.js";
import { Modal } from "/js/componentes/componentes.js";

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

customElements.define('alterar-senha', ModalAlterarSenha);