import { retornarCliente } from "@api/cliente.api.js";
import { retornarEnderecos } from "@api/endereco.api.js";
import { retornarCartoesCredito } from "@api/cartaoCredito.api.js";
import { alertarErro } from "@api/alertErro.js";
import { retornarCupons } from "@api/cupom.api.js";

import { SecaoDadosPessoais } from "@paginas/secoes/conta/secaoDadosPessoais/SecaoDadosPessoais.js";
import { SecaoEndereco } from "@paginas/secoes/conta//secaoEndereco/SecaoEndereco.js";
import { SecaoCartaoCredito } from "@paginas//secoes/conta/secaoCartaoCredito/SecaoCartaoCredito.js";
import { SecaoCupom } from "@paginas/secoes/conta/secaoCupons/secaoCupons.js";
import { localStorageKeys, removerHistorico } from "../localStorage.js";

/**
 * @typedef {Window & { trocarSecao?: any }} WindowComFuncao
 */

/** @type {WindowComFuncao} */
const win = window;

export async function initPagina() {
    const urlParams = new URLSearchParams(window.location.search);
    const idClienteURL = urlParams.get('idcliente'); 
    if (idClienteURL !== null && idClienteURL !== undefined){
        localStorage.setItem('idcliente', idClienteURL);
        removerHistorico();
        window.location.href = "/conta";
    }

    const idCliente = localStorage.getItem(localStorageKeys.idCliente);

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

    win.trocarSecao = (idSecao) => trocarSecao(idSecao);
}