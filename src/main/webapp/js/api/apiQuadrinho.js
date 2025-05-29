const PATH = "/api/quadrinho";

export async function consultarTodosQuadrinhos(){
    try {
        let url = PATH;

        const response = await fetch(url);
        return await response.json();        
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function retornarQuadrinho(id) {
    try {
        const url = `${PATH}?id=${id}`;

        const response = await fetch(url);
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function filtrarTodosQuadrinhos(filtro) {
    try {
        let url = PATH;
        if (filtro !== undefined){
            url += "?opcao=consultarporfiltro";
            url += "&titulo="+filtro.titulo;
            url += "&autor="+filtro.autor;
        }

        const response = await fetch(url);
        return await response.json();        
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}