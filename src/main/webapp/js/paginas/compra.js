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

export async function initPagina() {

    const idCliente = localStorage.getItem(localStorageKeys.idCliente); 

    let enderecos;
    let cartoesCredito;
    let cupons;
    let carrinho;

    try {
        enderecos = await retornarEnderecos(idCliente);
        cartoesCredito = await retornarCartoesCredito(idCliente);
        cupons = await retornarCupons(idCliente);
        carrinho = await retornarCarrinho();
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

}