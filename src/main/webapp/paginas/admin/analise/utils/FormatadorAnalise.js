export default class FormatadorAnalise {
    constructor(){

    }

    formatarQuantidade(dados){
        return this.formatar(dados, "quantidade");
    }

    formatarValor(dados){
        return this.formatar(dados, "valor");
    }

    formatar(dados, tipo){
        const labels = this.formatarLabels(dados);

        const datasets = dados.map(dados => {
            const cor = this.gerarCorAleatoria();

            const borderColor = this.corParaRGB(cor);

            const color = this.clarear(cor, 0.5);

            const bgColor = this.corParaRGB(color);

            return {
                label: dados.titulo,
                data: labels.map(labelData => {
                    const venda = dados.dados.find(v => formatarDataISO(v.data) === labelData);

                    if (tipo === "quantidade"){
                        return venda ? venda.quantidade : 0;
                    }

                    if (tipo === "valor"){
                        return venda ? venda.valorTotal : 0;
                    }

                    return 0;
                }),
                borderColor: borderColor,
                backgroundColor: bgColor,
                tension: 0.4
            };
        });

        const labels2 = labels.map(label => {
            return formatarDataBR(label);
        });

        return {
            labels: labels2,
            datasets: datasets
        }
    }

    formatarLabels(dados){
        const datasSet = new Set();

        dados.forEach(d => {
            d.dados.forEach(venda => {
                datasSet.add(formatarDataISO(venda.data));
            });
        });

        let labels = Array.from(datasSet);

        labels.sort((a, b) => a.localeCompare(b));

        return this.gerarDateRange(labels[0], labels[labels.length - 1]);
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
            r: Math.min(Math.round(cor.r + (255 - cor.r) * percentual), 255),
            g: Math.min(Math.round(cor.g + (255 - cor.g) * percentual), 255),
            b: Math.min(Math.round(cor.b + (255 - cor.b) * percentual), 255)
        };
    }
    
    escurecer(cor, percentual) {
        return {
            r: Math.max(cor.r - cor.r * percentual, 0),
            g: Math.max(cor.g - cor.g * percentual, 0),
            b: Math.max(cor.b - cor.b * percentual, 0)
        };
    }

    gerarDateRange(dataInicial, dataFinal) {
        let dataAtual = new Date(dataInicial);

        const fim = new Date(dataFinal);
        fim.setDate(fim.getDate() + 1);

        const datas = [];
        while (dataAtual < fim) {
            const d = new Date(dataAtual);
            const dataFormatoISO = d.toISOString().split('T')[0];
            
            datas.push(dataFormatoISO);

            dataAtual.setDate(dataAtual.getDate() + 1);
        }

        return datas;
    }

}

function formatarDataBR(label) {
    const [year, month, day] = label.split('-');
    const data = new Date(year, month - 1, day);
    
    return data.toLocaleDateString('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    });
}

function formatarDataISO(array){
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);

    return `${ano}-${mes}-${dia}`;
}