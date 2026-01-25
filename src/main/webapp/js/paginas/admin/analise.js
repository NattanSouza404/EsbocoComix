import { alertarErro } from "@api/alertErro.js";
import { retornarAnalise } from "@api/analise.api.js";
import FormatadorAnalise from "@utils/FormatadorAnalise.js";
import GraficoLinha from "@componentes/analise/GraficoLinha.js";

export async function initPagina() {

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
            dataInicio: /** @type {HTMLInputElement} */(document.querySelector('[name = "dataInicio"]')).value,
            dataFinal: /** @type {HTMLInputElement} */ (document.querySelector('[name = "dataFinal"]')).value
        }

        try {
            const dadosAnalise = await retornarAnalise(filtro);

            graficoQuantProdutos.atualizar(formatador.formatarQuantidade(dadosAnalise.produtos));
            graficoQuantCategorias.atualizar(formatador.formatarQuantidade(dadosAnalise.categorias));
            graficoValorProdutos.atualizar(formatador.formatarValor(dadosAnalise.produtos));
            graficoValorCategorias.atualizar(formatador.formatarValor(dadosAnalise.categorias));

        } catch (error){
            alertarErro(error);
        }
    }
}