import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/quadrinho";

export async function consultarTodosQuadrinhos(){
    let url = PATH;

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();        
}

export async function consultarTodasCategorias(){
    let url = `${PATH}?opcao=consultarcategorias`;

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();        
}

export async function retornarQuadrinho(id) {
    const url = `${PATH}?id=${id}`;

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function filtrarTodosQuadrinhos(filtro) {

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

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();    
}