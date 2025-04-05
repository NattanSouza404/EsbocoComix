const ctxCategorias = document.getElementById('graficoLinhaCategorias').getContext('2d');
const graficoLinhaCategorias = new Chart(ctxCategorias, {
    type: 'line',
    data: {
        labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
        datasets: [
            {
                label: 'Super-Heróis',
                data: [10, 30, 25, 50, 35, 80],
                borderColor: 'rgba(255, 99, 132, 1)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'Fantasia',
                data: [20, 10, 40, 30, 60, 70],
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'Aventura',
                data: [15, 35, 20, 60, 45, 90],
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'História em Quadrinhos Clássica',
                data: [25, 20, 45, 55, 70, 85],
                borderColor: 'rgba(153, 102, 255, 1)',
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderWidth: 2,
                tension: 0.4
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: true,
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

const ctxProdutos = document.getElementById('graficoLinhaProdutos').getContext('2d');
const graficoLinhaProdutos = new Chart(ctxProdutos, {
    type: 'line',
    data: {
        labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho'],
        datasets: [
            {
                label: 'Conancraft',
                data: [15, 25, 40, 35, 65, 50],
                borderColor: 'rgba(255, 159, 64, 1)',
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'Homem Chiclete',
                data: [10, 20, 30, 45, 35, 70],
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'Menino Maluquinho',
                data: [20, 30, 25, 60, 50, 80],
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderWidth: 2,
                tension: 0.4
            },
            {
                label: 'Sonic',
                data: [25, 35, 45, 40, 70, 85],
                borderColor: 'rgba(153, 102, 255, 1)',
                backgroundColor: 'rgba(153, 102, 255, 0.2)',
                borderWidth: 2,
                tension: 0.4
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: true,
                position: 'top'
            }
        },
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});