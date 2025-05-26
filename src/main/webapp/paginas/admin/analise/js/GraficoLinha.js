export default class GraficoLinha {
    constructor(elementoHTML, dados, title){
        const ctx = elementoHTML.getContext('2d');
        
        this.chart = new Chart(ctx, {
            type: 'line',
            data: dados,
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    },
                    title: {
                        display: true,
                        text: title
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }

    atualizar(){
        this.chart.update();
    }   

}