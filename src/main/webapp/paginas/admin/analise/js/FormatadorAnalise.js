export default class FormatadorAnalise {
    constructor(){

    }

    formatar(dados){
        const labels = dados[0].vendas.map(v => {
            const data = v.data;
            return `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`;
        });
    
        const datasets = dados.map(d => {
            const corBase = this.gerarCorAleatoria();
            
            return {
                label: d.label,
                data: d.vendas.map(v => v.quantidade),
                borderColor: this.corParaRGB(corBase),
                backgroundColor: this.corParaRGB(this.clarear(corBase, 0.2)),
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