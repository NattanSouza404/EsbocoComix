import { SecaoCadastrarDadosPessoais } from "./secoesCadastrar.js";
import { FormularioEndereco } from "../forms.js";
import { montarClientePorForm, montarEnderecoPorForm } from "../script.js";
import { inserirCliente } from "../api.js";

const cadastrarCliente = document.getElementById('container-cadastrar-cliente');
cadastrarCliente.append(new SecaoCadastrarDadosPessoais());

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

async function enviarCliente(){
    const formCliente = document.getElementById('form-cadastrar-dados-pessoais');
    const cliente = montarClientePorForm(formCliente);
    
    const formsEndereco = document.querySelectorAll('.endereco');
    const enderecos = [];
    formsEndereco.forEach((e) => {
        enderecos.push(montarEnderecoPorForm(e));
    });

    inserirCliente({
        cliente: cliente,
        enderecos: enderecos
    });
}

function adicionarEndereco(){
    const nEnderecosNaTela = document.querySelectorAll('.endereco').length + 1;
    const form = new FormularioEndereco();
    form.setNumeroTitulo(nEnderecosNaTela);
    containerEndereco.append(form);
}

window.adicionarEndereco = adicionarEndereco;
window.enviarCliente = enviarCliente;