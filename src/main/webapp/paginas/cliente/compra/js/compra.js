import { enviarPedido } from "/js/api/apiPedido.js";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";
import { retornarEnderecos } from "/js/api/apiEndereco.js";
import { retornarCartoesCredito } from "/js/api/apiCartaoCredito.js";

const enderecos = await retornarEnderecos(
    localStorage.getItem('idcliente')
);

function calcularFrete(cep) {
    let hash = 0;

    for (let i = 0; i < cep.length; i++) {
        hash += cep.charCodeAt(i);
    }

    // Deixa o valor entre 5 e 20
    return (hash % (20 - 5 + 1)) + 5;
}

enderecos.forEach(endereco => {
    const valorFrete = calcularFrete(endereco.cep);

    const option = document.createElement('option');
    option.value = JSON.stringify(
        { id: endereco.id, valorFrete: valorFrete }
    );

    option.textContent = `${endereco.fraseCurta} - Frete: R$ ${valorFrete},00`;

    document.getElementById('endereco').append(option);
});

const cartoesCredito = await retornarCartoesCredito(
    localStorage.getItem('idcliente')
);

Array.from(document.getElementsByClassName('select-cartao'))
    .forEach(select => {
        cartoesCredito.forEach(cartao => {
            const option = document.createElement('option');
            option.value = cartao.id;
            option.textContent = `${cartao.numero}: (${cartao.bandeiraCartao})`;

            select.append(option);
        });
    });

cartoesCredito.forEach(cartao => {
    const div = document.createElement('div');

    div.className = 'd-flex mb-3';
    div.innerHTML = `
        
    `;

    document.getElementById('cartoes').append(div);
});

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

document.getElementById('me-clica').onclick = () => {

    const idCliente = localStorage.getItem('idcliente');
    const endereco = JSON.parse(document.getElementById('endereco').value);

    const idEndereco = endereco.id;
    const valorFrete = endereco.valorFrete;

    enviarPedido(
        {
            idCliente: idCliente,
            enderecoEntrega: {
                id: idEndereco
            },
            valorFrete: valorFrete,
            //"cartoesCreditoPedido": [
            //    {"id": 5, "valorPago:": 30}
            //],
            //"cupomPromocional": {
            //   "id": 2
            //},
            //"cupons": [
            //    {"id": 5 }, {"id": 1 }
            //]
        }
    
    )
};