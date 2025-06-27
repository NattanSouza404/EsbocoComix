import { retornarAnalise } from "../../../../js/api/apiAnalise.js";
import FormatadorAnalise from "./FormatadorAnalise.js";
import GraficoLinha from "./GraficoLinha.js";

document.addEventListener('DOMContentLoaded', async function () {
    const formatador = new FormatadorAnalise();

    const analise = await retornarAnalise();

    const graficoQuantCategorias = new GraficoLinha(
        document.getElementById('grafico-linha-quant-categorias'),
        formatador.formatarQuantidade(analise.categorias),
        "Quantidade de Categorias"
    );

    const graficoQuantProdutos = new GraficoLinha(
        document.getElementById('grafico-linha-quant-produtos'),
        formatador.formatarQuantidade(analise.produtos),
        "Quantidade de Produtos"
    );

    const graficoValorProdutos = new GraficoLinha(
        document.getElementById('grafico-linha-valor-produtos'),
        formatador.formatarValor(analise.produtos),
        "Valor dos Produtos"
    );

    const graficoValorCategorias = new GraficoLinha(
        document.getElementById('grafico-linha-valor-categorias'),
        formatador.formatarValor(analise.categorias),
        "Valor das Categorias"
    );

    document.getElementById('btn-pesquisar').onclick = async () => {
        const filtro = {
            dataInicio: document.querySelector('[name = "dataInicio"]').value,
            dataFinal: document.querySelector('[name = "dataFinal"]').value
        }

        try {
            const dadosAnalise = await retornarAnalise(filtro);

            graficoQuantProdutos.atualizar(formatador.formatarQuantidade(dadosAnalise.produtos));
            graficoQuantCategorias.atualizar(formatador.formatarQuantidade(dadosAnalise.categorias));
            graficoValorProdutos.atualizar(formatador.formatarValor(dadosAnalise.produtos));
            graficoValorCategorias.atualizar(formatador.formatarValor(dadosAnalise.categorias));

        } catch (error){
            alert(error)
        }
    }
})
