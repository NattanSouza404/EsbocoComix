import { retornarPedidos } from "/js/api/apiPedido.js";

const pedirTroca = () => {
    const modal = new bootstrap.Modal(document.getElementById('trocaModal'));
    modal.show();
};

const idCliente = localStorage.getItem('idcliente');

const pedidos = await retornarPedidos(idCliente);

let valorTotal = 0;

pedidos.forEach(pedido => {
    pedido.itensPedido.forEach(item => {
        valorTotal += item.quantidade * 2;
    });

    pedido.data = '02/02/2022';
}
);

pedidos.forEach(pedido => {
    const div = document.createElement('div');

    div.innerHTML = `
        <div class="p-3 mb-3 text-white rounded secao-superior">
            <div class="d-flex justify-content-between align-items-center">
                <div>
                    <h5>Código #${pedido.id}</h5>
                    <p>Valor total: R$ ${valorTotal}</p>
                    <p>Status: ${pedido.status}</p>
                    <p>Data: ${pedido.data}</p>
                </div>
                <div>
                    <button class="btn btn-light btn-sm botao-troca">Pedir Troca</button>
                </div>
            </div>
        </div>
    `;

    const button = div.getElementsByClassName('botao-troca')[0];
    button.onclick = () => pedirTroca();

    const containerItensPedido = document.createElement("div");
    containerItensPedido.className = "ps-4";
    div.append(containerItensPedido);

    pedido.itensPedido.forEach(item => {

        const div2 = document.createElement('div');
        div2.className = "p-3 mb-2 bg-light rounded border";

        div2.innerHTML = `
            <div class="d-flex justify-content-between align-items-center">
                <div>
                    <h6>Quadrinho id: ${item.idQuadrinho}</h6>
                    <p>Quantidade: ${item.quantidade} unidades</p>
                </div>
                <button class="btn btn-secondary btn-sm botao-troca">Pedir Troca</button>
            </div>
        `

        const button = div2.getElementsByClassName('botao-troca')[0];
        button.onclick = () => pedirTroca();

        containerItensPedido.append(div2);
    });

    document.getElementById('container-compras').append(div);
})
