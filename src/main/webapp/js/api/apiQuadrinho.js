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

export async function consultarTodasCategorias(){
    try {
        let url = `${PATH}?opcao=consultarcategorias`;

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
            url += "&ano="+filtro.ano;
            url += "&numeroPaginas="+filtro.numeroPaginas;
            url += "&codigoBarras="+filtro.codigoBarras;
            url += "&edicao="+filtro.edicao;
            url += "&editora="+filtro.editora;
            url += "&isbn="+filtro.isbn;
            url += "&altura="+filtro.altura;
            url += "&largura="+filtro.largura;
            url += "&profundidade="+filtro.profundidade;
            url += "&peso="+filtro.peso;
            url += "&grupoPrecificacao="+filtro.grupoPrecificacao;
            url += "&categorias="+filtro.categorias;
        }

        const response = await fetch(url);
        return await response.json();        
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}