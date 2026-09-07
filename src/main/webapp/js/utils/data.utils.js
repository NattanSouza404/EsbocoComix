export function formatarDateTime(dateArray){
    const dataFormatada = new Date(
        dateArray[0], // Ano
        dateArray[1] - 1, // Mês (subtraia 1)
        dateArray[2], // Dia
        dateArray[3], // Hora
        dateArray[4], // Minutos
        dateArray[5] ? dateArray[5] : 0, // Segundos
        dateArray[6] ? dateArray[6] / 1000000 : 0 // Converter nanosegundos em milissegundos
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