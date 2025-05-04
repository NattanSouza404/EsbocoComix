export function mascararNumeroCartao(numero){
    return '**** **** **** ' + numero.slice(-4);
}

export function formatarDataParaInput(array){
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${ano}-${mes}-${dia}`;
}

export function formatarData(array) {
    const ano = array[0];
    const mes = ('0' + array[1]).slice(-2);
    const dia = ('0' + array[2]).slice(-2);
    return `${dia}/${mes}/${ano}`;
}

export function montarClientePorForm(form){
    const formData = new FormData(form);

    return {
        nome: formData.get("nome"),
        genero: formData.get("genero"),
        dataNascimento: formData.get("dataNascimento"),
        cpf: formData.get("cpf"),
        email: formData.get("email"),
        telefone: {
            tipo: formData.get("tipoTelefone"),
            ddd: formData.get("ddd"),
            numero: formData.get("numero"),
        },
        isAtivo: formData.get("isAtivo"),
        ranking: formData.get('ranking')
    };
    
}

export function montarEnderecoPorForm(form){
    const formData = new FormData(form);

    return {
        fraseCurta: formData.get('fraseCurta'),
        logradouro: formData.get('logradouro'),
        tipoLogradouro: formData.get('tipoLogradouro'),
        tipoResidencial: formData.get('tipoResidencial'),
        numero: formData.get('numero'),
        bairro: formData.get('bairro'),
        cep: formData.get('cep'),
        cidade: formData.get('cidade'),
        estado: formData.get('estado'),
        pais: formData.get('pais'),
        isResidencial: formData.get('isResidencial'),
        isEntrega: formData.get('isEntrega'),
        isCobranca: formData.get('isCobranca'),
        observacoes: formData.get('observacoes')
    };
}

export function montarCartaoCreditoPorForm(form){
    const formData = new FormData(form);

    return {
        numero: formData.get('numero'),
        nomeImpresso: formData.get('nomeImpresso'),
        codigoSeguranca: formData.get('codigoSeguranca'),
        isPreferencial: formData.get('isPreferencial'),
        bandeiraCartao: formData.get('bandeiraCartao')
    };
}

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

export function formatarPreco(preco) {
    return preco.toLocaleString('pt-BR', {
        style: 'currency',
        currency: 'BRL',
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

export function formatarDateTime(dateArray){
    const dataFormatada = new Date(
        dateArray[0], // Ano
        dateArray[1] - 1, // Mês (subtraia 1)
        dateArray[2], // Dia
        dateArray[3], // Hora
        dateArray[4], // Minutos
        dateArray[5], // Segundos
        dateArray[6] / 1000000 // Converter nanosegundos em milissegundos
    );
    
    return dataFormatada.toLocaleString('pt-BR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false // Formato de 24 horas
    });

}
