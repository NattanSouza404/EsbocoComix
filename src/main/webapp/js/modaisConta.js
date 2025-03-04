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

        const form = document.createElement('form');
        form.id = 'alterar-dados-pessoais';

        const secaoPrincipal = document.createElement('section');
        form.append(secaoPrincipal);

        const secaoTelefone = document.createElement('section');
        form.appendChild(secaoTelefone);

        let p = criarElemento('p', "Dados Pessoais")
        p.className = "titulo-dados-cadastro";
        secaoPrincipal.append(p);

        let div = document.createElement('div');
        div.className = 'dados-formulario';
        secaoPrincipal.append(div);

        div.append(criarElemento('label', "Nome"));

        let input = document.createElement('input');
        input.type = "text";
        input.placeholder = "Seu nome";
        input.name = "nome";
        div.append(input);

        div.append(criarElemento('label', "Gênero"));

        let select = document.createElement('select');
        select.name = "genero";
        div.append(select);

        const opcoesGenero = [
            "Masculino", "Feminino", "Outro", "Prefiro não informar"
        ];

        opcoesGenero.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        div.append(criarElemento('label', "Data de Nascimento"));

        input = document.createElement('input');
        input.type = "date";
        input.name = "dataNascimento";
        div.append(input);

        div.append(criarElemento('label', "CPF"));

        input = document.createElement('input');
        input.name = "cpf";
        div.append(input);

        div.append(criarElemento('label', "E-mail"));

        input = document.createElement('input');
        input.type = "email";
        input.name = "email";
        div.append(input);

        p = criarElemento('p', 'Telefone');
        p.className = "titulo-dados-cadastro";
        secaoPrincipal.append(p);

        div = document.createElement('div');
        div.className = 'dados-formulario';
        secaoTelefone.append(div);

        div.append(criarElemento('label', "Tipo do Telefone"));

        const opcoesTipoTelefone = [
            "Fixo", "Celular"
        ];

        select = document.createElement('select');
        select.name = "tipoTelefone";
        div.append(select);

        opcoesTipoTelefone.forEach(genero => {
            select.append(criarElemento('option', genero));
        });

        div.append(criarElemento('label', "DDD"));

        input = document.createElement('input');
        input.type = "text";
        input.name = "ddd";
        input.placeholder = "11";
        div.append(input);

        div.append(criarElemento('label', 'Número do Telefone'));

        input = document.createElement('input');
        input.type = "text";
        input.name = "numero";
        input.placeholder = "11111-1111";
        div.append(input);

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
                id: cliente.telefone.id,
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