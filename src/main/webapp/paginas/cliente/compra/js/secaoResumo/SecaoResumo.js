import { calcularValorTotal, formatarPreco } from "/js/script.js";

export class ResumoPedido {
    constructor(secaoSelecaoEndereco, secaoSelecaoCartao, secaoCupons) {
        this.resumo = document.getElementById('resumo');
        this.tabelaProdutosBody = document.querySelector('#tabela-produtos tbody');

        document.body.querySelector('#endereco').addEventListener('change', () => {
            this.atualizarEndereco(secaoSelecaoEndereco.getEnderecoSelecionado());
        });

        const observerCupons = new MutationObserver(() => {
            this.atualizarCupons(secaoCupons.getCuponsPedido());
        });
        
        observerCupons.observe(
            document.getElementById('cupons'), 
            {
                childList: true,
                subtree: true,
            }
        );

        document.getElementById('cupons').addEventListener('change', (e) => {
            if (e.target.tagName === 'SELECT') {
                this.atualizarCupons(secaoCupons.getCuponsPedido());
            }
        });

        const observerCartoes = new MutationObserver(() => {
            secaoSelecaoCartao.adicionarInputListeners(this);
            this.atualizarCartoes(secaoSelecaoCartao.getCartoesCreditoPedido())
        });
        
        observerCartoes.observe(
            document.getElementById('cartoes'), 
            {
                childList: true,
                subtree: true,
                attributes: true
            }
        );

        secaoSelecaoCartao.adicionarInputListeners(this);

        this.valorCartao = 0;
        this.descontoCupom = 0;
        this.valorFrete = 0;
    }

    preencherResumo(carrinho, endereco) {
        carrinho.itensCarrinho.forEach(item => {
            this.tabelaProdutosBody.insertAdjacentHTML('beforeend', `
               <tr>
                <td>${item.nome}</td>
                <td>${item.quantidade}</td>
                <td>${formatarPreco(item.preco)}</td>
                <td>${formatarPreco(item.preco * item.quantidade)}</td>
               </tr>
            `);
        });

        this.valorTotal = calcularValorTotal(carrinho);
        this.valorFrete = endereco.valorFrete;
        this.descontoCupom = 0;

        this.resumo.insertAdjacentHTML('beforeend', `
            <div id="info-resumo">
                <p>Valor dos Produtos: ${formatarPreco(this.valorTotal)}</p>
                <p class="frete">Frete: ${formatarPreco(this.valorFrete)}</p>
                <hr>
                <p>Forma de pagamento</p>
                <p class="valor-cartao">Valor pago em cartão: R$ 0,00</p>
                <p class="desconto-cupom">Desconto do Cupom: R$ 0,00</p>
                <hr>
                <p class="total">Total: ${formatarPreco(this.valorTotal + this.valorFrete)}</p>
                <p class="total-restante">Total Restante: R$ 0,00</p>
            </div>
        `);
    }

    atualizarEndereco(endereco){
        this.valorFrete = endereco.valorFrete;

        this.resumo.querySelector('.frete').textContent = `
            Frete: ${formatarPreco(this.valorFrete)}
        `;

        this.resumo.querySelector('.total').textContent = `
            Total: ${formatarPreco(this.valorTotal + this.valorFrete)}
        `;
    }

    atualizarCupons(cupons){
        this.descontoCupom = 0;
        cupons.forEach((cupom) => {
            this.descontoCupom += cupom.valor;
        });

        this.resumo.querySelector('.desconto-cupom').textContent = `
            Desconto do cupom: ${formatarPreco(this.descontoCupom)}
        `;

        this.atualizarTotalRestante();
    }

    atualizarCartoes(cartoes){
        this.valorCartao = 0;
        cartoes.forEach((cartao) => {
            this.valorCartao += cartao.valor ? cartao.valor : 0;
        });

        this.resumo.querySelector('.valor-cartao').textContent = `
            Valor pago em cartão: ${formatarPreco(this.valorCartao)}
        `;

        this.atualizarTotalRestante();
    }

    atualizarTotalRestante(){
        this.resumo.querySelector('.total-restante').textContent = `
            Total Restante: ${formatarPreco(
                this.valorCartao + this.descontoCupom 
            )}
        `;
    }

}