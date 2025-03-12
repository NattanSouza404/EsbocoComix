import { DadosPessoaisConta, DadosEnderecoConta } from "./secoesConta.js";
import { ModalAlterarSenha, ModalAlterarDadosPessoais, ModalAlterarEndereco, ModalAlterarCartaoCredito } from "./modaisConta.js";
import { retornarCliente, retornarEnderecos } from "../api.js";
import { criarElemento } from "../script.js";

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

/*
let promessaCartoesCredito = (async () => {
    const uRLSearchParams = new URLSearchParams(window.location.search);
    const cartoesCredito = await retornarCartoesCredito(
        uRLSearchParams.get('idcliente')
    );
    console.log("Variavel global inicializada cartões de crédito:", cartoesCredito);
    return cartoesCredito;
})();
*/

const dadosEndereco = document.getElementById('dados-endereco');

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

promessaCliente.then((cliente) => {
    promessaEnderecos.then((enderecos) => {
        modalAlterarEndereco.atualizar(enderecos, cliente);
    });
})

const navegacaoConta = document.createElement('nav');
const opcoesConta = document.getElementById('opcoes-conta');
opcoesConta.append(navegacaoConta);

const textosBotao = [
    "Editar Cadastro", 'Alterar senha', 'Editar endereço', 'Editar Cartão de Crédito'
];

const acoesBotao = [
    () => modalAlterarDadosPessoais.toggleDisplay(),
    () => modalAlterarSenha.toggleDisplay(),
    () => modalAlterarEndereco.toggleDisplay(),
    () => modalAlterarCartaoCredito.toggleDisplay()
];

for (let i = 0; i < 4; i++) {
    const button = criarElemento('button', textosBotao[i]);
    button.onclick = acoesBotao[i];
    button.className = 'botao-prototipo';
    navegacaoConta.append(button);
}