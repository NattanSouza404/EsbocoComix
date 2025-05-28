import { retornarAnalise } from "../../../../js/api/apiAnalise.js";
import FormatadorAnalise from "./FormatadorAnalise.js";
import GraficoLinha from "./GraficoLinha.js";

document.addEventListener('DOMContentLoaded', async function () {
    const formatador = new FormatadorAnalise();

    const analise = await retornarAnalise();

    const graficoCategorias = new GraficoLinha(
        document.getElementById('graficoLinhaCategorias'),
        formatador.formatar(analise.categorias),
        "Categorias"
    );

    const graficoProdutos = new GraficoLinha(
        document.getElementById('graficoLinhaProdutos'),
        formatador.formatar(analise.produtos),
        "Produtos"
    );
})