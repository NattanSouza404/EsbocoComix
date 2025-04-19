import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "/js/script.js";
import { inserirCliente } from "/js/api/apiCliente.js";
import { SecaoFormsEndereco } from "/js/componentes/secaoEndereco.js";
import { SecaoFormsCartaoCredito } from "/js/componentes/secaoCartaoCredito.js";
import { FormularioCadastrarDadosPessoais } from "./formsCadastrar.js";

const mainContainer = document.getElementById('container-cadastrar-cliente');

const secaoDadosPessoais = document.createElement('section');
secaoDadosPessoais.id = 'secao-dados-pessoais';

const formDadosPessoais = new FormularioCadastrarDadosPessoais();
secaoDadosPessoais.append(formDadosPessoais);
mainContainer.append(secaoDadosPessoais);

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