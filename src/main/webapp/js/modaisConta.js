class ModalAlterarSenha extends Modal {

    constructor(){
        super();

        this.id = 'modal-alterar-senha';

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
        promessaCliente.then((clienteAtual) => {
            const form = document.getElementById("alterar-senha");
            const formData = new FormData(form);

            const clienteToUpdate = {
                "id": clienteAtual.id,
                "nome": clienteAtual.nome,
                "senha": formData.get('senha'),
                "senhaConfirmacao": formData.get('senhaConfirmacao')
            };

            atualizarCliente(clienteToUpdate, 'atualizarsenha');
        });
    }
}

class ModalAlterarDadosPessoais extends Modal {

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
                promessaCliente.then((clienteAtual) => {
                    this.enviarAtualizacao(clienteAtual);
                })
            }
        ));

        conteudoModal.append(form);

        return conteudoModal;
    }

    atualizar(cliente){
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

    async enviarAtualizacao(clienteAtual){
        const form = document.getElementById("alterar-dados-pessoais");
        const cliente = montarClientePorForm(form);

        cliente.id = clienteAtual.id;
        
        atualizarCliente(cliente);
    }
}

customElements.define('alterar-senha', ModalAlterarSenha);
customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);