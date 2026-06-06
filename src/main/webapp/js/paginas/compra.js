import { enviarPedido } from "@api/pedido.api.js";
import { retornarCarrinho } from "@api/carrinho.api.js";
import { retornarEnderecos } from "@api/endereco.api.js";
import { retornarCartoesCredito } from "@api/cartaoCredito.api.js";
import { ResumoPedido } from "@paginas/secoes/compra/secaoResumo/SecaoResumo.js";
import { SecaoSelecaoEndereco } from "@paginas/secoes/compra/secaoEndereco/SecaoSelecaoEndereco.js";
import { SecaoCartaoCredito } from "@paginas/secoes/compra/secaoCartaoCredito/SecaoCartaoCredito.js";
import { retornarCupons } from "@api/cupom.api.js";
import { SecaoCupom } from "@paginas/secoes/compra/secaoCupom/SecaoCupons.js";
import { alertarErro } from "@api/alertErro.js";
import { localStorageKeys } from "../localStorage.js";

const secaoSelecaoEndereco = new SecaoSelecaoEndereco();
let secaoSelecaoCartao;
let secaoCupons;

const getElementos = () => {
    return {
        btnEnviarPedido: document.getElementById('btn-enviar-pedido')
    };
}

export async function initPagina() {
    try {
        const idCliente = localStorage.getItem(localStorageKeys.idCliente); 

        const enderecos = await retornarEnderecos(idCliente);
        const cartoesCredito = await retornarCartoesCredito(idCliente);
        const cupons = await retornarCupons(idCliente);
        const carrinho = await retornarCarrinho();

        secaoSelecaoEndereco.preencher(enderecos);
        secaoSelecaoCartao = new SecaoCartaoCredito(cartoesCredito);
        secaoCupons = new SecaoCupom(cupons);

        const resumoPedido = new ResumoPedido(
            secaoSelecaoEndereco,
            secaoSelecaoCartao,
            secaoCupons
        );

        resumoPedido.preencherResumo(
            carrinho,
            secaoSelecaoEndereco.getEnderecoSelecionado()
        );
    } catch (error){
        alertarErro(error);
        window.location.href = "/";
    }

    getElementos().btnEnviarPedido.onclick = confirmarEnvioDePedido;
}

async function confirmarEnvioDePedido(){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo realizar essa compra?");

        if (!confirmacaoUsuario){
            return;
        }
        
        const idCliente = localStorage.getItem(localStorageKeys.idCliente);
        const endereco = secaoSelecaoEndereco.getEnderecoSelecionado();

        const idEndereco = endereco.id;
        const valorFrete = endereco.valorFrete;

        const cuponsPedido = [];

        secaoCupons.getCuponsPedido().forEach(cupom => {
            cuponsPedido.push({
                idCupom: cupom.idCupom
            });
        });
    
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
        window.location.href = "/minhasCompras";
    } catch (error){
        alertarErro(error);
    }
};