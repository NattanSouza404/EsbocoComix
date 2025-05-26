import { retornarCliente } from "/js/api/apiCliente.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

import { SecaoDadosPessoais } from "./secaoDadosPessoais/SecaoDadosPessoais.js";
import { SecaoEndereco } from "./secaoEndereco/SecaoEndereco.js";
import { SecaoCartaoCredito } from "./secaoCartaoCredito/SecaoCartaoCredito.js";
import { retornarCupons } from "/js/api/apiCupom.js";
import { SecaoCupom } from "./secaoCupons/secaoCupons.js";

const urlParams = new URLSearchParams(window.location.search);
const idClienteURL = urlParams.get('idcliente'); 
if (idClienteURL !== null && idClienteURL !== undefined){
    localStorage.setItem('idcliente', idClienteURL);
}

let idCliente = localStorage.getItem('idcliente');

const cliente = await retornarCliente(idCliente);
const enderecos = await retornarEnderecos(idCliente);
const cartoesCredito = await retornarCartoesCredito(idCliente);
const cupons = await retornarCupons(idCliente);

const secaoDadosPessoais = new SecaoDadosPessoais(cliente);
const secaoCartaoCredito = new SecaoCartaoCredito(cartoesCredito);
const secaoEndereco = new SecaoEndereco(enderecos);
const secaoCupom = new SecaoCupom(cupons);

const container = document.getElementById('secoes-conta');
const secoes = [
    secaoDadosPessoais.elementoHTML,
    secaoCartaoCredito.elementoHTML,
    secaoEndereco.elementoHTML,
    secaoCupom.elementoHTML
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