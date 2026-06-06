import { retornarCliente } from "@api/cliente.api.js";
import { retornarEnderecos } from "@api/endereco.api.js";
import { retornarCartoesCredito } from "@api/cartaoCredito.api.js";
import { alertarErro } from "@api/alertErro.js";
import { retornarCupons } from "@api/cupom.api.js";

import { SecaoDadosPessoais } from "@paginas/secoes/conta/secaoDadosPessoais/SecaoDadosPessoais.js";
import { SecaoCartaoCredito } from "@paginas/secoes/conta/secaoCartaoCredito/SecaoCartaoCredito.js";
import { SecaoCupom } from "@paginas/secoes/conta/secaoCupons/secaoCupons.js";
import { localStorageKeys, removerHistorico } from "../localStorage.js";
import { SecaoEndereco } from "./secoes/conta/secaoEndereco/SecaoEndereco.js";
import { getUrlParam } from "../script.js";

const getElementos = () => {
    return {
        navLateral: document.getElementById('nav-lateral'),
        secaoAtual: document.querySelector('.secao-atual'),
        secaoDadosPessoais: document.getElementById('secao-dados-pessoais'),
        secaoEndereco: document.getElementById('secao-endereco'),
        secaoCartaoCredito: document.getElementById('secao-cartao-credito'),
        secaoCupom: document.getElementById('secao-cupons')
    }
}

export async function initPagina() {
    try {
        const el = getElementos();

        loginCliente(getUrlParam('idcliente'));

        definicoesBotoesSecao.forEach((def) => {
            el.navLateral.append(BotaoSecaoConta(def));
        });

        const idCliente = localStorage.getItem(localStorageKeys.idCliente);

        if (!idCliente || idCliente.length === 0){
            alertarErro("Você deve ter uma conta para acessar essa página!")
            window.location.href = "/cadastrar";
        }
    
        const cliente = await retornarCliente(idCliente);
        const enderecos = await retornarEnderecos(idCliente);
        const cartoesCredito = await retornarCartoesCredito(idCliente);
        const cupons = await retornarCupons(idCliente);

        new SecaoDadosPessoais(cliente);
        new SecaoCartaoCredito(cartoesCredito);
        new SecaoEndereco(enderecos);
        new SecaoCupom(cupons);

        [el.secaoDadosPessoais, el.secaoEndereco, el.secaoCartaoCredito, el.secaoCupom]
            .forEach(s => {
                s.classList.add('secao-nao-atual')
            });

        el.secaoDadosPessoais.classList.add('secao-atual');
        el.secaoDadosPessoais.classList.remove('secao-nao-atual');

    } catch (error){
        alertarErro(error);
        window.location.href = "/";
    }
}

function trocarSecao(secao){
    const secaoAtual = getElementos().secaoAtual;

    secaoAtual.classList.add('secao-nao-atual')
    secaoAtual.classList.remove('secao-atual');

    secao.classList.add('secao-atual');
    secao.classList.remove('secao-nao-atual');
}

function loginCliente(id){
    if (id !== null && id !== undefined){
        localStorage.setItem(localStorageKeys.idCliente, id);
        removerHistorico();
        window.location.href = "/conta";
    }
}


function BotaoSecaoConta(definicao){
    const btn = document.createElement('button');

    btn.id = definicao.id;
    btn.className = `btn ${definicao.className} mb-2`;
    btn.type = 'button';
    btn.onclick = definicao.acao;

    btn.innerHTML = /* html */`
        <img src="${definicao.img}">
        ${definicao.texto}
    `;

    return btn;
}

const definicoesBotoesSecao = [
    {
        id: "trocar-para-dados-pessoais",
        className: "btn-primary",
        acao: () => trocarSecao(getElementos().secaoDadosPessoais),
        img: "/img/imagem-do-usuario-com-fundo-preto.png",
        texto: "Dados Pessoais"
    },
    {
        id: "trocar-para-endereco",
        className: "btn-success",
        acao: () => trocarSecao(getElementos().secaoEndereco),
        img: "/img/house-fill.svg",
        texto: "Endereços"
    },
    {
        id: "trocar-para-cartao",
        className: "btn-secondary",
        acao: () => trocarSecao(getElementos().secaoCartaoCredito),
        img: "/img/credit-card.svg",
        texto: "Cartões de Crédito"
    },
    {
        id: "trocar-para-cupom",
        className: "btn-warning",
        acao: () => trocarSecao(getElementos().secaoCupom),
        img: "/img/ticket.svg",
        texto: "Cupons"
    }
];