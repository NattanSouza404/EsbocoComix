import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "/js/script.js";
import { inserirCliente } from "/js/api/apiCliente.js";
import { SecaoFormsCartaoCredito } from "./secaoFormsCartaoCredito.js";
import { FormularioCadastrarDadosPessoais } from "./formsCadastrar.js";
import { SecaoFormsEndereco } from "./secaoFormsEndereco.js";

const mainContainer = document.getElementById('container-cadastrar-cliente');

const formDadosPessoais = new FormularioCadastrarDadosPessoais();
document.getElementById('secao-dados-pessoais').append(formDadosPessoais);

const secaoFormsEndereco = new SecaoFormsEndereco();
mainContainer.append(secaoFormsEndereco);

const secaoCartaoCredito = new SecaoFormsCartaoCredito();
mainContainer.append(secaoCartaoCredito);

async function enviarCliente(){
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

    inserirCliente({
        cliente: cliente,
        enderecos: enderecos,
        cartoesCredito: cartoesCredito,
        senhaNova: senhaNova,
        senhaConfirmacao: senhaConfirmacao
    });
}

window.enviarCliente = enviarCliente;

document.getElementById('navbar-nav').remove();