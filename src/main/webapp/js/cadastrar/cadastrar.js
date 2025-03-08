import { FormularioCadastrarDadosPessoais } from "./formsCadastrar.js";
import { FormularioEndereco, FormularioCartaoCredito } from "../forms.js";
import { montarCartaoCreditoPorForm, montarClientePorForm, montarEnderecoPorForm } from "../script.js";
import { inserirCliente } from "../api.js";

const secaoCadastrarDadosPessoais = document.getElementById('secao-dados-pessoais');
secaoCadastrarDadosPessoais.append(new FormularioCadastrarDadosPessoais());

function montarEnderecoObrigatorio(){
    const form = new FormularioEndereco();
    const button = form.querySelector('button');
    form.removeChild(button);

    const isResidencial = form.querySelector('[name = "isResidencial"]');
    isResidencial.value = 'true';
    const opcaoNao = isResidencial.querySelector('[value = "false"]');
    opcaoNao.disabled = true;

    const fraseCurta = form.querySelector('[name = "fraseCurta"]');
    fraseCurta.placeholder = "Casa";

    return form;
}

const formEndereco = montarEnderecoObrigatorio();
const containerEndereco = document.getElementById('container-enderecos');
containerEndereco.append(formEndereco);

const formCartaoCredito = new FormularioCartaoCredito();
const containerCartaoCredito = document.getElementById('container-cartao-credito');
containerCartaoCredito.append(formCartaoCredito);

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

function adicionarEndereco(){
    const nEnderecosNaTela = document.querySelectorAll('.endereco').length + 1;
    const form = new FormularioEndereco();
    form.setNumeroTitulo(nEnderecosNaTela);
    containerEndereco.append(form);
}

function adicionarCartaoCredito(){
    const nCartoesCreditoNaTela = document.querySelectorAll('.cartao-credito').length + 1;
    const form = new FormularioCartaoCredito();
    form.setNumeroTitulo(nCartoesCreditoNaTela);
    containerCartaoCredito.append(form);
}

window.adicionarEndereco = adicionarEndereco;
window.enviarCliente = enviarCliente;
window.adicionarCartaoCredito = adicionarCartaoCredito;