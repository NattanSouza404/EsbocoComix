import { alertarErro } from "@api/alertErro.js";
import { inserirCliente } from "@api/cliente.api.js";
import { FormularioCartaoCredito } from "@componentes/forms/formCartaoCredito.js";
import { FormularioEndereco } from "@componentes/forms/formEndereco.js";
import { FormularioCadastrarDadosPessoais } from "@componentes/forms/formsCadastrar.js";
import { localStorageKeys } from "../localStorage.js";
import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "../script.js";

const getElementos = () => {
    return {
        navBar: document.getElementById('navbar-nav'),

        secaoDadosPessoais: document.getElementById('secao-dados-pessoais'),
        containerEnderecos: document.querySelector('#container-enderecos'),
        containerCartoesCredito: document.querySelector("#container-cartao-credito"),

        btnEnviarCadastro: document.getElementById('botao-enviar-cadastro'),
        btnAddEndereco: /** @type {HTMLButtonElement} */
            (document.querySelector('.btn-adicionar-endereco'))
        ,
        btnAddCartao: /** @type {HTMLButtonElement} */
            (document.querySelector(".btn-adicionar-cartao"))
        ,

        formDadosPessoais: /** @type {HTMLFormElement} */ (document.getElementById('form-dados-pessoais')),
        formsEndereco: document.querySelectorAll('.endereco'),
        formsCartaoCredito: document.querySelectorAll('.cartao-credito'),
    };
}

export async function initPagina() {
    const el = getElementos();

    el.secaoDadosPessoais.append(
        new FormularioCadastrarDadosPessoais()
    );

    el.btnAddEndereco.onclick = adicionarEndereco;
    el.btnAddCartao.onclick = adicionarCartaoCredito;

    el.containerEnderecos.append(
        new FormularioEndereco().tornarObrigatorio().setNumeroTitulo(1)
    );

    el.containerCartoesCredito.append(
        new FormularioCartaoCredito().setNumeroTitulo(1)
    );

    el.btnEnviarCadastro.onclick = enviarCliente;
    el.navBar.remove();
}

async function enviarCliente(){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar?");

        if (!confirmacaoUsuario){
            return;
        }

        const el = getElementos();

        const formData = new FormData(el.formDadosPessoais);

        const cadastro = await inserirCliente({
            cliente: montarClientePorForm(el.formDadosPessoais),
            enderecos: getEnderecos(),
            cartoesCredito: getCartoesCredito(),
            senhaNova: formData.get('senhaNova'),
            senhaConfirmacao: formData.get('senhaConfirmacao')
        });

        alert('Cadastrado com sucesso');

        localStorage.setItem(localStorageKeys.idCliente, cadastro.cliente.id);
        window.location.href = "/";
    } catch (error){
        alertarErro(error);
    }
}

function getEnderecos(){
    const enderecos = [];

    getElementos().formsEndereco.forEach((e) => {
        enderecos.push(montarEnderecoPorForm(e));
    });

    return enderecos;
}

function getCartoesCredito(){
    const cartoesCredito = [];

    getElementos().formsCartaoCredito.forEach((c) => {
        cartoesCredito.push(montarCartaoCreditoPorForm(c));
    });

    return cartoesCredito;
}

function adicionarEndereco(){
    const el = getElementos();

    const numero = el.formsEndereco.length + 1;
    const form = new FormularioEndereco()
        .setNumeroTitulo(numero)
        .habilitarBotaoRemover();

    el.containerEnderecos.append(form);
}

function adicionarCartaoCredito(){
    const el = getElementos();

    const numero = el.formsCartaoCredito.length + 1;

    const form = new FormularioCartaoCredito()
        .setNumeroTitulo(numero)
        .habilitarBotaoRemover();

    el.containerCartoesCredito.append(form);
}