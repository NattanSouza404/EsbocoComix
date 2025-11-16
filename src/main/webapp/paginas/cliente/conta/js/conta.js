import { retornarCliente } from "/js/api/apiCliente.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

import { SecaoDadosPessoais } from "./secaoDadosPessoais/SecaoDadosPessoais.js";
import { SecaoEndereco } from "./secaoEndereco/SecaoEndereco.js";
import { SecaoCartaoCredito } from "./secaoCartaoCredito/SecaoCartaoCredito.js";
import { retornarCupons } from "/js/api/apiCupom.js";
import { SecaoCupom } from "./secaoCupons/secaoCupons.js";
import { removerHistorico } from "../../../../js/localStorage.js";
import { alertarErro } from "../../../../js/api/alertErro.js";

const urlParams = new URLSearchParams(window.location.search);
const idClienteURL = urlParams.get('idcliente'); 
if (idClienteURL !== null && idClienteURL !== undefined){
    localStorage.setItem('idcliente', idClienteURL);
    removerHistorico();
    window.location.href = "/conta";
}

const idCliente = localStorage.getItem('idcliente');

let cliente;
let enderecos;
let cartoesCredito;
let cupons;

try {
    cliente = await retornarCliente(idCliente);
    enderecos = await retornarEnderecos(idCliente);
    cartoesCredito = await retornarCartoesCredito(idCliente);
    cupons = await retornarCupons(idCliente);
} catch (error){
    if (!idCliente || idCliente.length === 0){
        alertarErro(new Error("Você deve ter uma conta para acessar essa página!"));
        window.location.href = "/cadastrar";
    } else {
        alertarErro(error);
        window.location.href = "/";
    } 
}

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
            secao.style.display = 'flex';
            return;
        } 

        secao.style.display = 'none';
    });
}

window.trocarSecao = (idSecao) => trocarSecao(idSecao);