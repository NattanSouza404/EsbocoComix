import { FormularioCadastrarDadosPessoais } from "./formsCadastrar.js";
import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "../script.js";
import { inserirCliente } from "../api.js";
import { SecaoFormsEndereco } from "./secaoEndereco.js";
import { SecaoFormsCartaoCredito } from "./secaoCartaoCredito.js";

const mainContainer = document.getElementById('container-cadastrar-cliente');

const secaoDadosPessoais = document.createElement('section');
secaoDadosPessoais.id = 'secao-dados-pessoais';
secaoDadosPessoais.append(new FormularioCadastrarDadosPessoais());
mainContainer.append(secaoDadosPessoais);

const secaoFormsEndereco = new SecaoFormsEndereco();
mainContainer.append(secaoFormsEndereco);

const secaoCartaoCredito = new SecaoFormsCartaoCredito();
mainContainer.append(secaoCartaoCredito);

async function enviarCliente(){
    const formCliente = document.getElementById('form-cadastrar-dados-pessoais');
    const cliente = montarClientePorForm(formCliente);
    
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

    inserirCliente({
        cliente: cliente,
        enderecos: enderecos,
        cartoesCredito: cartoesCredito
    });
}

window.enviarCliente = enviarCliente;