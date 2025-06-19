import { retornarAnalise } from "../../../../js/api/apiAnalise.js";
import FormatadorAnalise from "./FormatadorAnalise.js";
import GraficoLinha from "./GraficoLinha.js";

document.addEventListener('DOMContentLoaded', async function () {
    const formatador = new FormatadorAnalise();

    const analise = await retornarAnalise();

    const graficoCategorias = new GraficoLinha(
        document.getElementById('graficoLinhaCategorias'),
        formatador.formatar(analise.categorias),
        "Quantidade de Categorias"
    );

    const graficoProdutos = new GraficoLinha(
        document.getElementById('graficoLinhaProdutos'),
        formatador.formatar(analise.produtos),
        "Quantidade de Produtos"
    );

    document.getElementById('btn-pesquisar').onclick = async () => {
        const filtro = {
            dataInicio: document.querySelector('[name = "dataInicio"]').value,
            dataFinal: document.querySelector('[name = "dataFinal"]').value
        }

        try {
            const dadosAnalise = await retornarAnalise(filtro);

            graficoProdutos.atualizar(formatador.formatar(dadosAnalise.produtos));
            graficoCategorias.atualizar(formatador.formatar(dadosAnalise.categorias));

        } catch (error){
            alert(error)
        }
    }
})
