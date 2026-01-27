import { alertarErro } from "@api/alertErro.js";
import { atualizarItemCarrinho, deletarItemCarrinho, retornarCarrinho } from "@api/carrinho.api.js";
import { SumarioCarrinho } from "@componentes/carrinho/SumarioCarrinho.js";
import { TabelaCarrinho } from "@componentes/carrinho/TabelaCarrinho.js";
import { calcularValorTotal } from "../script.js";

const getElementos = () => {
    return {
        mainContainer: document.getElementById('main-container'),
        tabelaCarrinho: document.getElementById('tabela-carrinho'),
        sumarioCarrinho: document.getElementById('sumario-carrinho')
    };
}

export async function initPagina() {
    try {
        const carrinho = await retornarCarrinho();
   
        if (carrinho.itensCarrinho.length === 0){
            getElementos().mainContainer.innerHTML =
                `<h3 class="text-center">Carrinho vazio</h3>`;

            return;
        }

        getElementos().tabelaCarrinho.append(
            TabelaCarrinho(
                carrinho,
                confirmarAtualizarItem,
                confirmarDelecaoItem
            )
        );

        getElementos().sumarioCarrinho.append(
            SumarioCarrinho(calcularValorTotal(carrinho))
        );

    } catch (error){
        alertarErro(error);
    }
}

async function confirmarAtualizarItem(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar quantidade desse item?"); 

        if (!confirmacaoUsuario){
            return;
        }
    
        await atualizarItemCarrinho(item);

        alert('Item atualizado com sucesso!');

        window.location.reload();
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarDelecaoItem(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse item?");

        if (!confirmacaoUsuario){
            return;
        }

        await deletarItemCarrinho(item);
        alert("Item deletado com sucesso!");
        window.location.reload();
    } catch (error){
        alertarErro(error);
    }
}
