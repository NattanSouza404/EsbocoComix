import { enviarPedido } from "/js/api/apiPedido";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";

const carrinho = await retornarCarrinho();

carrinho.itensCarrinho.forEach(item => {
    const tr = document.createElement('tr');

    tr.innerHTML = `
            <td>${item.nome}</td>
            <td>${item.quantidade}</td>
            <td>R$ ${item.preco}</td>
            <td>R$ ${item.preco * item.quantidade}</td>
    `;

    document.querySelector('#tabela-produtos tbody')
        .append(tr);
});

let valorTotal = 0;

carrinho.itensCarrinho.forEach(item => {
    valorTotal += item.preco * item.quantidade;
});


const info = document.createElement('div');
info.innerHTML = `
    <p>Valor dos Produtos: R$ ${valorTotal}</p>
    <p>Frete: R$ 10,00</p>
    <p>Desconto do Cupom: R$ 0,00</p>
    <hr>
    <p>Total Restante: R$ 70,00</p>
`

document.getElementById('resumo').append(info);

const itensPedido = [];

carrinho.itensCarrinho.forEach(item => {
    itensPedido.push(
        {
            idQuadrinho: item.idQuadrinho,
            quantidade: item.quantidade
        }
    )
});

document.getElementById('me-clica').onclick = () => enviarPedido(
    {
        "idCliente": 45,
        "itensPedido": itensPedido,
        //"cartoesCredito": [
        //    {"id": 5, "valorPago:": 30}
        //],
        //"cupomPromocional": {
        //   "id": 2
        //},
        //"cupons": [
        //    {"id": 5 }, {"id": 1 }
        //]
    }
);