import { calcularValorTotal, formatarPreco } from "/js/script.js";

export class ResumoPedido {
    constructor() {
        this.resumo = document.getElementById('resumo');
        this.tabelaProdutosBody = document.querySelector('#tabela-produtos tbody');
    }

    preencherResumo(carrinho, endereco) {
        carrinho.itensCarrinho.forEach(item => {
            const tr = document.createElement('tr');

            tr.innerHTML = `
                <td>${item.nome}</td>
                <td>${item.quantidade}</td>
                <td>${formatarPreco(item.preco)}</td>
                <td>${formatarPreco(item.preco * item.quantidade)}</td>
            `;

            this.tabelaProdutosBody
                .append(tr);
        });

        const valorTotal = calcularValorTotal(carrinho);
        const valorFrete = endereco.valorFrete;

        const info = document.createElement('div');
        info.innerHTML = `
            <p>Valor dos Produtos: ${formatarPreco(valorTotal)}</p>
            <p>Frete: ${formatarPreco(valorFrete)}</p>
            <p>Desconto do Cupom: R$ 0,00</p>
            <hr>
            <p>Total Restante: ${formatarPreco(valorTotal + valorFrete)}</p>
        `;

        this.resumo.append(info);
    }
}