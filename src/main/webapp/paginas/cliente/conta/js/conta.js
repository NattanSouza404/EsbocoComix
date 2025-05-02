import { retornarCliente } from "/js/api/apiCliente.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

import { SecaoDadosPessoais } from "./secaoDadosPessoais/SecaoDadosPessoais.js";
import { SecaoCartaoCredito } from "./secaoCartaoCredito/SecaoCartaoCredito.js";
import { SecaoEndereco } from "./secaoEndereco/SecaoEndereco.js";

let idCliente = localStorage.getItem('idcliente');

if (idCliente === null || idCliente === undefined){
    const urlParams = new URLSearchParams(window.location.search);
    localStorage.setItem('idcliente', urlParams.get('idcliente'));
    idCliente = localStorage.getItem("idcliente");
}

const cliente = await retornarCliente(idCliente);
const enderecos = await retornarEnderecos(idCliente);
const cartoesCredito = await retornarCartoesCredito(idCliente);

const secaoDadosPessoais = new SecaoDadosPessoais(cliente);
const secaoCartaoCredito = new SecaoCartaoCredito(cartoesCredito);
const secaoEndereco = new SecaoEndereco(enderecos);

const container = document.getElementById('secoes-conta');
const secoes = [
    secaoDadosPessoais, secaoCartaoCredito, secaoEndereco
];

secoes.forEach(secao => {
    container.append(secao);
});

function trocarSecao(idSecao){
    secoes.forEach(secao => {
        if (secao.id === idSecao){
            secao.style.display = 'block';
            return;
        } 

        secao.style.display = 'none';
    });
}

window.trocarSecao = (idSecao) => trocarSecao(idSecao);