import { alertarErro } from "@api/alertErro.js";
import { retornarAnalise } from "@api/analise.api.js";
import GraficoLinha from "@componentes/analise/GraficoLinha.js";
import FormatadorAnalise from "@utils/FormatadorAnalise.js";
import { capitalizar } from "../../script.js";

const getElementos = () => {
    return {
        containerGraficos: document.getElementById('container-graficos'),
        btnPesquisar: document.getElementById('btn-pesquisar'),
        inputDataInicio: document.querySelector('[name = "dataInicio"]'),
        inputDataFinal: document.querySelector('[name = "dataFinal"]')
    };
}

export async function initPagina() {

    const formatador = new FormatadorAnalise();

    const analise = await retornarAnalise();

    const graficos = definicoesGraficos.map(definicao => {
        const dados = obterDadosFormatados(formatador, definicao, analise);

        return {
            ...definicao,
            instancia: new GraficoLinha(definicao.id, dados, definicao.titulo)
        };
    });

    graficos.forEach(g => {
        getElementos().containerGraficos.append(g.instancia);
    });

    getElementos().btnPesquisar.onclick = async () => {
        try {
            const filtro = {
                dataInicio: /** @type {HTMLInputElement} */ 
                    (getElementos().inputDataInicio).value,

                dataFinal: /** @type {HTMLInputElement} */
                    (getElementos().inputDataFinal).value
            };

            const dadosAnalise = await retornarAnalise(filtro);

            graficos.forEach(g => {
                const dados = obterDadosFormatados(formatador, g, dadosAnalise);
                g.instancia.atualizar(dados);
            });
        } catch (error) {
            alertarErro(error);
        }
    };

}

function obterDadosFormatados(formatador, def, analise) {
  const metodo = `formatar${capitalizar(def.tipo)}`;
  return formatador[metodo](analise[def.origem]);
}

const definicoesGraficos = [
  {
    id: 'grafico-linha-quant-categorias',
    tipo: 'quantidade',
    origem: 'categorias',
    titulo: 'Quantidade de Categorias'
  },
  {
    id: 'grafico-linha-quant-produtos',
    tipo: 'quantidade',
    origem: 'produtos',
    titulo: 'Quantidade de Produtos'
  },
  {
    id: 'grafico-linha-valor-produtos',
    tipo: 'valor',
    origem: 'produtos',
    titulo: 'Valor dos Produtos'
  },
  {
    id: 'grafico-linha-valor-categorias',
    tipo: 'valor',
    origem: 'categorias',
    titulo: 'Valor das Categorias'
  }
];
