import { calcularValorTotal, formatarPreco } from "../../../../js/script.js";
import { atualizarItemCarrinho, deletarItemCarrinho, retornarCarrinho } from "../../../../js/api/apiCarrinho.js";
import { alertarErro } from "../../../../js/api/alertErro.js";

let carrinho;

try {
    carrinho = await retornarCarrinho();
} catch (error){
    alertarErro('Erro buscando dados:', error);
}

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
    
        linha.querySelector('.btn-atualizar').onclick = async () => {
            item.quantidade = linha.querySelector('input').value;
            await confirmarAtualizarItem(item);
        }

        linha.querySelector('.btn-deletar').onclick = async () => {
            await confirmarDelecaoItem(item);   
        }
    
        document.getElementById("tabela-produtos-carrinho")
            .append(linha);
    });
    
    let valorTotal = calcularValorTotal(carrinho);
    
    document.getElementById('total-carrinho').textContent = `${formatarPreco(valorTotal)}`;
}

async function confirmarAtualizarItem(item){
    const confirmacaoUsuario = confirm("Deseja mesmo atualizar quantidade desse item?"); 

    if (!confirmacaoUsuario){
        return;
    }

    try {
        await atualizarItemCarrinho(item);

        alert('Item atualizado com sucesso!');

        window.location.reload();
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarDelecaoItem(item){
    const confirmacaoUsuario = confirm("Deseja mesmo deletar esse item?");

    if (!confirmacaoUsuario){
        return;
    }

    try {
        await deletarItemCarrinho(item);
        alert("Item deletado com sucesso!");
        window.location.reload();
    } catch (error){
        alertarErro(error);
    }
    
}
