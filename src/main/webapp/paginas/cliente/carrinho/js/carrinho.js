import { calcularValorTotal, formatarPreco } from "/js/script.js";
import { atualizarItemCarrinho, deletarItemCarrinho } from "/js/api/apiCarrinho.js";
import { retornarCarrinho } from "/js/api/apiCarrinho.js";

const carrinho = await retornarCarrinho();

if (carrinho.itensCarrinho.length === 0){
    document.getElementById('main-container').innerHTML = `
        <h3 class="text-center">Carrinho vazio</h3>
    `;
}

if (carrinho.itensCarrinho.length !== 0){
    carrinho.itensCarrinho.forEach(item => {
        const linha = document.createElement('tr');
    
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
        `;
    
        const td = document.createElement('td');
    
        let button = document.createElement('button');
        button.textContent = 'Atualizar Quantidade';
        button.className = "btn btn-warning btn-sm";
    
        button.onclick = () => {
            item.quantidade = linha.querySelector('input').value;

            const confirmacaoUsuario = confirm("Deseja mesmo atualizar quantidade desse item?"); 

            if (confirmacaoUsuario){
                atualizarItemCarrinho(item);
            }
            
        };
    
        td.append(button);
    
        button = document.createElement('button');
        button.textContent = 'Remover';
        button.className = "btn btn-danger btn-sm";
    
        button.onclick = () => {
            const confirmacaoUsuario = confirm("Deseja mesmo deletar esse item?");

            if (confirmacaoUsuario){
                deletarItemCarrinho(item);
            }
 
        };
    
        td.append(button);
    
        linha.append(td);
    
        document.getElementById("tabela-produtos-carrinho")
            .append(linha);
    });
    
    let valorTotal = calcularValorTotal(carrinho);
    
    document.getElementById('total-carrinho').textContent = `${formatarPreco(valorTotal)}`;
}


