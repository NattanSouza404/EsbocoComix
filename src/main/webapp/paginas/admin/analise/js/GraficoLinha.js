export default class GraficoLinha {
    constructor(elementoHTML, dados, title){
        const ctx = elementoHTML.getContext('2d');
        
        this.chart = new Chart(ctx, {
            type: 'line',
            data: dados,
            options: {
                responsive: true,
                interaction: {
                    intersect: false,
                    mode: 'index',
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'left',
                        maxWidth: 300  // Add scroll when exceeds width
                    },
                    title: {
                        display: true,
                        text: title,
                        font: {
                            size: 20
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        suggestedMax: 10,
                    }
                }
            }
        });
    }

    atualizar(dados){
        this.chart.data = dados;
        this.chart.update();
    }   

}