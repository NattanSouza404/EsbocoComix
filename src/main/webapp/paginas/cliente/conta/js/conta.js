import { DadosPessoaisConta, DadosEnderecoConta, DadosCartaoCreditoConta } from "./secoesConta.js";
import { retornarCliente, retornarEnderecos, retornarCartoesCredito } from "/js/api.js";
import { NavegacaoConta } from "./navegacaoConta.js";
import { ModalAlterarCartaoCredito } from "./modais/modalAlterarCartaoCredito.js";
import { ModalAlterarEndereco } from "./modais/modalAlterarEndereco.js";
import { ModalAlterarDadosPessoais } from "./modais/modalAlterarDadosPessoais.js";
import { ModalAlterarSenha } from "./modais/modalAlterarSenha.js";

let promessaCliente = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const cliente = await retornarCliente(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada cliente:", cliente);
    return cliente;
})();

let promessaEnderecos = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const enderecos = await retornarEnderecos(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada endereços:", enderecos);
    return enderecos;
})();

let promessaCartoesCredito = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const cartoesCredito = await retornarCartoesCredito(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada cartões de crédito:", cartoesCredito);
    return cartoesCredito;
})();

const dadosEndereco = document.getElementById('dados-endereco');
const dadosCartaoCredito = document.getElementById('dados-cartao-credito');

const modalAlterarDadosPessoais = new ModalAlterarDadosPessoais();
const modalAlterarEndereco = new ModalAlterarEndereco();
const modalAlterarCartaoCredito = new ModalAlterarCartaoCredito();
const modalAlterarSenha = new ModalAlterarSenha();

document.body.append(modalAlterarDadosPessoais);
document.body.append(modalAlterarSenha);
document.body.append(modalAlterarCartaoCredito)
document.body.append(modalAlterarEndereco);

promessaCliente.then((cliente) => {
    modalAlterarDadosPessoais.atualizar(cliente);
    modalAlterarSenha.atualizar(cliente);

    const containerDadosPessoais = document.getElementById('dados-pessoais');
    containerDadosPessoais.append(new DadosPessoaisConta(cliente));
});

promessaEnderecos.then((enderecos) => {
    let contador = 1;
    enderecos.forEach(
        (e) => {
            const endereco = new DadosEnderecoConta(e);
            endereco.titulo.textContent = contador+"º Endereço";
            dadosEndereco.append(endereco);
            contador++;
        }
    )
});

promessaCartoesCredito.then((cartoes) => {
    let contador = 1;
    cartoes.forEach(
        (e) => {
            const cartao = new DadosCartaoCreditoConta(e);
            cartao.titulo.textContent = contador+"º Cartão";
            dadosCartaoCredito.append(cartao);
            contador++;
        }
    )
});

promessaCliente.then((cliente) => {
    promessaEnderecos.then((enderecos) => {
        modalAlterarEndereco.atualizar(enderecos, cliente);
    });

    promessaCartoesCredito.then((cartoes) => {
        modalAlterarCartaoCredito.atualizar(cartoes, cliente);
    });
})

const navegacaoConta = new NavegacaoConta();
const opcoesConta = document.getElementById('opcoes-conta');
opcoesConta.append(navegacaoConta);

const botoes = [
    { id: 'btn-editar-cadastro', texto: "Editar Cadastro", acao: () => modalAlterarDadosPessoais.toggleDisplay() },
    { id: 'btn-alterar-senha', texto: 'Alterar senha', acao: () => modalAlterarSenha.toggleDisplay() },
    { id: 'btn-alterar-endereco', texto: 'Editar endereço', acao: () => modalAlterarEndereco.toggleDisplay() },
    { id: 'btn-alterar-cartao-credito', texto: 'Editar Cartão de Crédito', acao: () => modalAlterarCartaoCredito.toggleDisplay() }
];

botoes.forEach(({ id, texto, acao }) => {
    navegacaoConta.adicionarBotao(id, texto, acao);
});