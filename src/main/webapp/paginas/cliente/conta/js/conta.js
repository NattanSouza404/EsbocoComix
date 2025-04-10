import { DadosPessoaisConta, DadosEnderecoConta, DadosCartaoCreditoConta } from "./secoesConta.js";

import { retornarCliente } from "/js/api/apiCliente.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

import { NavegacaoConta } from "./navegacaoConta.js";
import { ModalAlterarCartaoCredito } from "./modais/modalAlterarCartaoCredito.js";
import { ModalAlterarEndereco } from "./modais/modalAlterarEndereco.js";
import { ModalAlterarDadosPessoais } from "./modais/modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./modais/modalAlterarSenha.js";

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
containerDadosPessoais.append(new DadosPessoaisConta(cliente));

let contador = 1;
enderecos.forEach(
    (e) => {
        const endereco = new DadosEnderecoConta(e);
        endereco.titulo.textContent = contador+"º Endereço";
        dadosEndereco.append(endereco);
        contador++;
    }
)

contador = 1;
cartoesCredito.forEach(
    (e) => {
        const cartao = new DadosCartaoCreditoConta(e);
        cartao.titulo.textContent = contador+"º Cartão";
        dadosCartaoCredito.append(cartao);
        contador++;
    }
)

modalAlterarEndereco.atualizar(enderecos, cliente);
modalAlterarCartaoCredito.atualizar(cartoesCredito, cliente);

const navegacaoConta = new NavegacaoConta();
const opcoesConta = document.getElementById('opcoes-conta');
opcoesConta.append(navegacaoConta);

const botoes = [
    { id: 'btn-editar-cadastro', texto: "Editar Cadastro", acao: () => modalAlterarDadosPessoais.toggleDisplay() },
    { id: 'btn-alterar-senha', texto: 'Alterar senha', acao: () => modalAlterarSenha.toggleDisplay() },
    { id: 'btn-alterar-endereco', texto: 'Editar endereço', acao: () => modalAlterarEndereco.toggleDisplay() },
];

botoes.forEach(({ id, texto, acao }) => {
    navegacaoConta.adicionarBotao(id, texto, acao);
});

navegacaoConta.append(
    document.createRange().createContextualFragment(
        `<button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
            data-bs-target="#modal-alterar-cartao-credito">
            Editar Cartão de crédito
        </button>`)
    .firstChild
);