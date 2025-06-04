import { formatarData } from "../../../../js/script.js";

export default class FormatadorAnalise {
    constructor(){

    }

    formatar(dados){
        const datasSet = new Set();

        dados.forEach(d => {
            d.dados.forEach(venda => {
                datasSet.add(formatarData(venda.data));
            });
        });

        const labels = Array.from(datasSet);

        const datasets = dados.map(dados => {
            const cor = this.gerarCorAleatoria();

            return {
                label: dados.titulo,
                data: labels.map(labelData => {
                    const venda = dados.dados.find(v => formatarData(v.data) === labelData);
                    return venda ? venda.quantidade : 0;
                }),
                borderColor: this.corParaRGB(cor),
                backgroundColor: this.clarear(this.corParaRGB(cor), 100),
                tension: 0.4
            };
        });

        return {
            labels: labels,
            datasets: datasets
        }
    }

    gerarCorAleatoria() {
        const r = Math.floor(Math.random() * 200);
        const g = Math.floor(Math.random() * 200);
        const b = Math.floor(Math.random() * 200);
        return { r, g, b };
    }
    
    corParaRGB(cor) {
        return `rgb(${Math.round(cor.r)}, ${Math.round(cor.g)}, ${Math.round(cor.b)})`;
    }
    
    clarear(cor, percentual) {
        return {
            r: Math.min(cor.r + cor.r * percentual, 255),
            g: Math.min(cor.g + cor.g * percentual, 255),
            b: Math.min(cor.b + cor.b * percentual, 255)
        };
    }
    
    escurecer(cor, percentual) {
        return {
            r: Math.max(cor.r - cor.r * percentual, 0),
            g: Math.max(cor.g - cor.g * percentual, 0),
            b: Math.max(cor.b - cor.b * percentual, 0)
        };
    } 

}