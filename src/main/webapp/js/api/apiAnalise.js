const PATH = "/api/analise";

export async function retornarAnalise(filtro) {
    let url = PATH;

    if (filtro){
        url += `?dataInicio=${filtro.dataInicio}`;
        url += `&dataFinal=${filtro.dataFinal}`;
    }

    const response = await fetch(url);

    if (response.status === 500){
        throw Error('Nenhum dado encontrado!');
    }

    return await response.json();
}