import { enviarPedido } from "/js/api/apiPedido.js";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";
import { ResumoPedido } from "./secaoResumo/SecaoResumo.js";
import { SecaoSelecaoEndereco } from "./secaoEndereco/SecaoSelecaoEndereco.js";
import { SecaoCartaoCredito } from "./secaoCartaoCredito/SecaoCartaoCredito.js";
import { retornarCupons } from "/js/api/apiCupom.js";
import { SecaoCupom } from "./secaoCupom/SecaoCupons.js";

const enderecos = await retornarEnderecos(
    localStorage.getItem('idcliente')
);

const cartoesCredito = await retornarCartoesCredito(
    localStorage.getItem('idcliente')
);

const carrinho = await retornarCarrinho();

const cupons = await retornarCupons(
    localStorage.getItem('idcliente')
);

const secaoSelecaoEndereco = new SecaoSelecaoEndereco(); 
secaoSelecaoEndereco.preencher(enderecos);

const secaoSelecaoCartao = new SecaoCartaoCredito(cartoesCredito);

const secaoCupons = new SecaoCupom(cupons);

const resumoPedido = new ResumoPedido(
    secaoSelecaoEndereco, secaoSelecaoCartao, secaoCupons
);

resumoPedido.preencherResumo(carrinho, secaoSelecaoEndereco.getEnderecoSelecionado());

document.getElementById('btn-enviar-pedido').onclick = () => {
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

    enviarPedido(
        {
            idCliente: idCliente,
    
            valorFrete: valorFrete,

            enderecoEntrega: {
                id: idEndereco
            },

            cuponsPedido: cuponsPedido,

            cartoesCreditoPedido: secaoSelecaoCartao.getCartoesCreditoPedido()
            
        }
    
    )
};