export function calcularValorTotal(carrinho) {
    let valorTotal = 0;

    carrinho.itensCarrinho.forEach(item => {
        valorTotal += item.preco * item.quantidade;
    });

    return valorTotal;
}

export function calcularFrete(cep) {
    let hash = 0;

    for (let i = 0; i < cep.length; i++) {
        hash += cep.charCodeAt(i);
    }

    // Deixa o valor entre 5 e 20
    return (hash % (20 - 5 + 1)) + 5;
}