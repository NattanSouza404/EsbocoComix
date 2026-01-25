import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "../script.js";
import { inserirCliente } from "@api/cliente.api.js";
import { SecaoFormsCartaoCredito } from "./secoes/cadastrar/secaoFormsCartaoCredito.js";
import { FormularioCadastrarDadosPessoais } from "../componentes/forms/formsCadastrar.js";
import { SecaoFormsEndereco } from "./secoes/cadastrar/secaoFormsEndereco.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../localStorage.js";

/**
 * @typedef {Window & { enviarCliente?: any }} WindowComFuncao
 */

/** @type {WindowComFuncao} */
const win = window;

export async function initPagina() {

    const mainContainer = document.getElementById('container-cadastrar-cliente');

    const formDadosPessoais = new FormularioCadastrarDadosPessoais();
    document.getElementById('secao-dados-pessoais').append(formDadosPessoais);

    const secaoFormsEndereco = new SecaoFormsEndereco();
    mainContainer.append(secaoFormsEndereco);

    const secaoCartaoCredito = new SecaoFormsCartaoCredito();
    mainContainer.append(secaoCartaoCredito);

    async function enviarCliente(){
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar?");

        if (!confirmacaoUsuario){
            return;
        }

        const cliente = montarClientePorForm(formDadosPessoais);
        
        const formsEndereco = document.querySelectorAll('.endereco');
        const enderecos = [];
        formsEndereco.forEach((e) => {
            enderecos.push(montarEnderecoPorForm(e));
        });

        const formsCartaoCredito = document.querySelectorAll('.cartao-credito');
        const cartoesCredito = [];
        formsCartaoCredito.forEach((c) => {
            cartoesCredito.push(montarCartaoCreditoPorForm(c));
        });

        const senhaNova = new FormData(formDadosPessoais).get("senhaNova");
        const senhaConfirmacao = new FormData(formDadosPessoais).get("senhaConfirmacao");

        try {
            const cadastro = await inserirCliente({
                cliente: cliente,
                enderecos: enderecos,
                cartoesCredito: cartoesCredito,
                senhaNova: senhaNova,
                senhaConfirmacao: senhaConfirmacao
            });

            alert('Cadastrado com sucesso');

            localStorage.setItem(localStorageKeys.idCliente, cadastro.cliente.id);
            window.location.href = "/";
        } catch (error){
            alertarErro(error);
        }
    }

    win.enviarCliente = enviarCliente;

    document.getElementById('navbar-nav').remove();
}