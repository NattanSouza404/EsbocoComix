import { enviarPedido } from "/js/api/apiPedido.js";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";
import { ResumoPedido } from "./secaoResumo/SecaoResumo.js";
import { SecaoSelecaoEndereco } from "./secaoEndereco/SecaoSelecaoEndereco.js";
import { SecaoCartaoCredito } from "./secaoCartaoCredito/SecaoCartaoCredito.js";
import { retornarCupons } from "/js/api/apiCupom.js";
import { SecaoCupom } from "./secaoCupom/SecaoCupons.js";
import { alertarErro } from "../../../../js/api/alertErro.js";

const idCliente = localStorage.getItem('idcliente'); 

let enderecos;
let cartoesCredito;
let cupons;
let carrinho;

try {
    enderecos = await retornarEnderecos(idCliente);
    cartoesCredito = await retornarCartoesCredito(idCliente);
    cupons = await retornarCupons(idCliente);
    carrinho = await retornarCarrinho(idCliente);
} catch (error){
    alertarErro(error);
    window.location.href = "/";
}

const secaoSelecaoEndereco = new SecaoSelecaoEndereco(); 
secaoSelecaoEndereco.preencher(enderecos);

const secaoSelecaoCartao = new SecaoCartaoCredito(cartoesCredito);

const secaoCupons = new SecaoCupom(cupons);

const resumoPedido = new ResumoPedido(
    secaoSelecaoEndereco, secaoSelecaoCartao, secaoCupons
);

resumoPedido.preencherResumo(carrinho, secaoSelecaoEndereco.getEnderecoSelecionado());

document.getElementById('btn-enviar-pedido').onclick = async () => {
    const confirmacaoUsuario = confirm("Deseja mesmo realizar essa compra?");

    if (!confirmacaoUsuario){
        return;
    }
    
    const idCliente = localStorage.getItem('idcliente');
    const endereco = secaoSelecaoEndereco.getEnderecoSelecionado();

    const idEndereco = endereco.id;
    const valorFrete = endereco.valorFrete;

    const cuponsPedido = [];

    secaoCupons.getCuponsPedido().forEach(cupom => {
        cuponsPedido.push({
            idCupom: cupom.idCupom
        });
    });

    try {
        await enviarPedido(
            {
                idCliente: idCliente,
                valorFrete: valorFrete,

                enderecoEntrega: { id: idEndereco },

                cuponsPedido: cuponsPedido,
                cartoesCreditoPedido: secaoSelecaoCartao.getCartoesCreditoPedido()
            }
        );

        alert('Pedido realizado');
    } catch (error){
        alertarErro(error);
    }
};