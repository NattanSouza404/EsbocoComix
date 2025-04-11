import { retornarCliente } from "/js/api/apiCliente.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

import { ModalAlterarCartaoCredito } from "./secaoCartaoCredito/modalAlterarCartaoCredito.js";
import { ModalAlterarEndereco } from "./secaoEndereco/modalAlterarEndereco.js";
import { ModalAlterarDadosPessoais } from "./secaoDadosPessoais/modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./secaoDadosPessoais/modalAlterarSenha.js";
import { toggleDisplay } from "/js/script.js";
import { CartaoDadosPessoais } from "./secaoDadosPessoais/CartaoDadosPessoais.js";
import { CartaoEndereco } from "./secaoEndereco/CartaoEndereco.js";
import { CartaoCartaoCredito } from "./secaoCartaoCredito/CartaoCartaoCredito.js";

const idCliente = localStorage.getItem('idcliente');

const cliente = await retornarCliente(idCliente);
const enderecos = await retornarEnderecos(idCliente);
const cartoesCredito = await retornarCartoesCredito(idCliente);

const dadosEndereco = document.getElementById('dados-endereco');
const dadosCartaoCredito = document.getElementById('dados-cartao-credito');

const modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
const modalAlterarEndereco = new ModalAlterarEndereco();
const modalAlterarCartaoCredito = new ModalAlterarCartaoCredito();
const modalAlterarSenha = new ModalAlterarSenha();

document.body.append(modalAlterarDadosPessoais);
document.body.append(modalAlterarSenha);
document.body.append(modalAlterarEndereco);

modalAlterarDadosPessoais.atualizar(cliente);
modalAlterarSenha.atualizar(cliente);

const containerDadosPessoais = document.getElementById('dados-pessoais');
containerDadosPessoais.append(new CartaoDadosPessoais(cliente));

let contador = 1;
enderecos.forEach(
    (e) => {
        const endereco = new CartaoEndereco(e);
        endereco.titulo.textContent = contador+"º Endereço";
        dadosEndereco.append(endereco);
        contador++;
    }
)

contador = 1;
cartoesCredito.forEach(
    (e) => {
        const cartao = new CartaoCartaoCredito(e);
        cartao.titulo.textContent = contador+"º Cartão";
        dadosCartaoCredito.append(cartao);
        contador++;
    }
)

modalAlterarEndereco.atualizar(enderecos, cliente);
modalAlterarCartaoCredito.atualizar(cartoesCredito, cliente);

window.toggleDisplay = toggleDisplay;