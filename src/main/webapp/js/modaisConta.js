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

        let input = document.createElement('input');
        input.type = 'password';
        input.name = "senha";
        input.autocomplete = "new-password";
        dadosFormulario.append(label);
        dadosFormulario.append(input);

        label = criarElemento('label', 'Confirme a senha');
        input = document.createElement('input');
        input.type = 'password';
        input.name = "senhaConfirmacao";
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
        promessaCliente.then((cliente) => {

            const form = document.getElementById("alterar-senha");
            const formData = new FormData(form);

            const senha = formData.get('senha');
            const senhaConfirmacao = formData.get('senhaConfirmacao')

            if (!(senha === senhaConfirmacao)){
                alert('Senhas não são iguais!');
                return;
            }

            const clienteToUpdate = {
                "id": cliente.id,
                "nome": cliente.nome,
                "senha": formData.get('senha')
            }

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
                promessaCliente.then((cliente) => {
                    this.enviarFormulario(cliente);
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

    async enviarFormulario(cliente){
        const form = document.getElementById("alterar-dados-pessoais");
        const formData = new FormData(form);

        const c = {
            id: cliente.id,
            nome: formData.get("nome"),
            genero: formData.get("genero"),
            dataNascimento: formData.get("dataNascimento"),
            cpf: formData.get("cpf"),
            email: formData.get("email"),
            telefone: {
                tipo: formData.get("tipoTelefone"),
                ddd: formData.get("ddd"),
                numero: formData.get("numero"),
            }
        };

        atualizarCliente(c);
    }
}

customElements.define('alterar-senha', ModalAlterarSenha);
customElements.define('alterar-dados-pessoais', ModalAlterarDadosPessoais);