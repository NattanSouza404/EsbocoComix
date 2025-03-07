import { DadosPessoaisConta, DadosEnderecoConta } from "./secoesConta.js";
import { ModalAlterarSenha, ModalAlterarDadosPessoais } from "./modaisConta.js";
import { retornarCliente, retornarEnderecos } from "../api.js";
import { toggleDisplay } from "../script.js";

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

let modalAlterarSenha;
let modalAlterarDadosPessoais;
let dadosPessoaisConta;

promessaCliente.then((clienteAtual) => {
    modalAlterarSenha = new ModalAlterarSenha(clienteAtual);
    modalAlterarDadosPessoais = new ModalAlterarDadosPessoais(clienteAtual);
    dadosPessoaisConta = new DadosPessoaisConta(clienteAtual);

    document.body.append(modalAlterarSenha);
    document.body.append(modalAlterarDadosPessoais);

    const conta = document.getElementById('conta');
    conta.append(dadosPessoaisConta);
});

promessaEnderecos.then((enderecos) => {
    let contador = 1;
    const dadosEndereco = document.createElement('div');
    dadosEndereco.id = 'dados-endereco';
    conta.append(dadosEndereco);

    enderecos.forEach(
        (e) => {
            const endereco = new DadosEnderecoConta(e);
            endereco.titulo.textContent = contador+"º Endereço";
            dadosEndereco.append(endereco);
            contador++;
        }
    )
});

window.toggleDisplay = toggleDisplay;