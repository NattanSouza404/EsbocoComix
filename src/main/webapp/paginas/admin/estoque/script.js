import { alertarErro } from "@api/alertErro.js";
import { consultarEntradasEstoque, inserirEntradaEstoque } from "@api/estoque.api.js";
import ModalEntradaEstoque from "./componentes/ModalEntradaEstoque.js";
import { consultarTodosQuadrinhos } from "@api/quadrinho.api.js";
import { TabelaEntradaEstoque } from "./componentes/TabelaEntradaEstoque.js";
import { TabelaEstoque } from "./componentes/TabelaEstoque.js";

const getElementos = () => {
    return {
        containerTabelaEstoque: document.getElementById('container-tabela-estoque'),
        containerTabelaEntradaEstoque: document.getElementById('container-tabela-entrada-estoque'),
    };
}

initPagina();

export async function initPagina() {
    try {
        const el = getElementos();

        const entradasEstoque = await consultarEntradasEstoque();
        const quadrinhos = await consultarTodosQuadrinhos();

        const modal = new ModalEntradaEstoque(cadastrarEntradaEstoque);

        el.containerTabelaEstoque.appendChild(
            TabelaEstoque(quadrinhos, modal)
        );

        el.containerTabelaEntradaEstoque.appendChild(
            TabelaEntradaEstoque(entradasEstoque)
        );
    } catch (error){
        alertarErro(error);
    }

}

async function cadastrarEntradaEstoque(entradaEstoque){
    try {
        await inserirEntradaEstoque(entradaEstoque);
        alert('Entrada realizada!');
    } catch (error){
        alertarErro(error);
    }
}