import { calcularValorTotal, formatarPreco } from "../../../../js/script.js";
import { atualizarItemCarrinho, deletarItemCarrinho, retornarCarrinho } from "../../../../js/api/apiCarrinho.js";

const carrinho = await retornarCarrinho();

if (carrinho.itensCarrinho.length === 0){
    document.getElementById('main-container').innerHTML = `
        <h3 class="text-center">Carrinho vazio</h3>
    `;
}

if (carrinho.itensCarrinho.length !== 0){
    carrinho.itensCarrinho.forEach(item => {
        const linha = document.createElement('tr');
        linha.className = "item-carrinho";
    
        linha.innerHTML = `
            <td class="celula-produto">
                <p class="text-center">${item.nome}</p> 
                <img class="img-carrinho" src="${item.urlImagem}">
            </td>
            <td class="product-price">${formatarPreco(item.preco)}</td>
            <td class="product-quantity">
                <input name="quantidade" value=${item.quantidade}>
            </td>
            <td class="product-total">${formatarPreco(item.quantidade * item.preco)}</td>
            <td>
                <button class="btn-atualizar btn btn-warning btn-sm">Atualizar Quantidade</button>
                <button class="btn-deletar btn btn-danger btn-sm">Remover</button>
            </td>
        `;
    
        linha.querySelector('.btn-atualizar').onclick = () => {
            item.quantidade = linha.querySelector('input').value;

            const confirmacaoUsuario = confirm("Deseja mesmo atualizar quantidade desse item?"); 

            if (confirmacaoUsuario){
                atualizarItemCarrinho(item).then(
                    () => window.location.reload()
                )
            }
            
        };

        linha.querySelector('.btn-deletar').onclick = () => {
            const confirmacaoUsuario = confirm("Deseja mesmo deletar esse item?");

            if (confirmacaoUsuario){
                deletarItemCarrinho(item).then(
                    () => window.location.reload()
                )
            }
 
        };
    
        document.getElementById("tabela-produtos-carrinho")
            .append(linha);
    });
    
    let valorTotal = calcularValorTotal(carrinho);
    
    document.getElementById('total-carrinho').textContent = `${formatarPreco(valorTotal)}`;
}


