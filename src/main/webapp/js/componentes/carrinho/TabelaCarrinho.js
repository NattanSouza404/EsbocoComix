import { formatarPreco } from "../../script.js";

export const TabelaCarrinho = (carrinho, confirmarAtualizarItem, confirmarDelecaoItem) => {
    const div = document.createElement('div');
    div.className = "table-responsive";

    const tbody = document.createElement('tbody');
    tbody.id = "tabela-produtos-carrinho";

    div.innerHTML = /* html */ `
        <table id="cartTable" class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Produto</th>
                    <th>Preço</th>
                    <th>Quantidade</th>
                    <th>Total</th>
                    <th>Operações</th>
                </tr>
            </thead>
        </table>
    `;

    div.querySelector('table').append(CorpoTabelaCarrinho(
        carrinho,
        confirmarAtualizarItem,
        confirmarDelecaoItem
    ));

    return div;
}

function CorpoTabelaCarrinho(carrinho, confirmarAtualizarItem, confirmarDelecaoItem){
    const tbody = document.createElement('tbody');
    tbody.id = "tabela-produtos-carrinho";

    carrinho.itensCarrinho.forEach(item => {
        const linha = document.createElement('tr');
        linha.className = "item-carrinho";
    
        linha.innerHTML = /* html */ `
            <td class="celula-produto">
                <p class="text-center">${item.nome}</p> 
                <img class="img-carrinho" src="${item.urlImagem}">
            </td>
            
            <td class="product-price">
                ${formatarPreco(item.preco)}
            </td>

            <td class="product-quantity">
                <input
                    name="quantidade"
                    value=${item.quantidade}
                >
            </td>

            <td class="product-total">
                ${formatarPreco(item.quantidade * item.preco)}
            </td>
            
            <td>
                <button class="btn-atualizar btn btn-warning btn-sm">
                    Atualizar Quantidade
                </button>
                <button class="btn-deletar btn btn-danger btn-sm">
                    Remover
                </button>
            </td>
        `;
    
        /** @type {HTMLButtonElement} */
        (linha.querySelector('.btn-atualizar')).onclick = async () => {
            item.quantidade = linha.querySelector('input').value;
            await confirmarAtualizarItem(item);
        }

        /** @type {HTMLButtonElement} */
        (linha.querySelector('.btn-deletar')).onclick = async () => {
            await confirmarDelecaoItem(item);   
        }
    
        tbody.append(linha);
    });

    return tbody;
}