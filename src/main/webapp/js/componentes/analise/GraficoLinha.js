/**
 * @typedef {Window & { Chart?: any }} WindowComChart
 */

/** @type {WindowComChart} */
const win = window;

export default class GraficoLinha extends HTMLDivElement {
    constructor(id, dados, title){
        super();

        this.innerHTML = /* html */ `
            <canvas id="${id}" class="grafico-linha"></canvas>
            <hr/>
        `;

        const ctx = /** @type {HTMLCanvasElement} */
            (this.querySelector('canvas')).getContext('2d');
        
        this.chart = new win.Chart(ctx, {
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

customElements.define('grafico-linha', GraficoLinha, { extends: 'div' });