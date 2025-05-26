import { retornarCategoriasAnalise, retornarProdutosAnalise } from "./dados.js";
import FormatadorAnalise from "./FormatadorAnalise.js";
import GraficoLinha from "./GraficoLinha.js";

document.addEventListener('DOMContentLoaded', async function () {
    const formatador = new FormatadorAnalise();

    const categorias = retornarCategoriasAnalise();
    const produtos = retornarProdutosAnalise();

    const graficoCategorias = new GraficoLinha(
        document.getElementById('graficoLinhaCategorias'),
        formatador.formatar(categorias),
        "Categorias"
    );

    const graficoProdutos = new GraficoLinha(
        document.getElementById('graficoLinhaProdutos'),
        formatador.formatar(produtos),
        "Produtos"
    );
})