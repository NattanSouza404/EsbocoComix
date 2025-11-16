import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/cliente";

export async function retornarCliente(id) {
    const resposta = await fetch(`${PATH}?id=${id}`);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function retornarAllClientes(filtro) {
    let url = PATH;
    if (filtro !== undefined){

        url += "?opcao=consultarporfiltro";
        url += "&nome="+filtro.nome;
        url += "&cpf="+filtro.cpf;
        url += "&dataNascimento="+filtro.dataNascimento;
        url += "&genero="+filtro.genero;
        url += "&email="+filtro.email;
        url += "&ranking="+filtro.ranking;
        url += "&isAtivo="+filtro.isAtivo
    }

    const resposta = await fetch(url);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json(); 
}

export async function inserirCliente(pedidoCadastrarCliente){
    const url = PATH;

    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(pedidoCadastrarCliente)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 201){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function atualizarCliente(cliente, opcao){
    let url = PATH;
    if (opcao != null){
        url += "?opcao="+opcao;
    }

    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cliente)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
    
}

export async function atualizarSenha(cliente){

    let url = `${PATH}?opcao=atualizarsenha`;

    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cliente)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
    
}

export async function inativarCliente(cliente){
    let url = `${PATH}?opcao=atualizarstatuscadastro`;

    const option = {
        method: 'PUT',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(cliente)
    }

    const resposta = await fetch(url, option);

    if (resposta.status !== 200) {
        await estourarErroAPI(resposta);
    }
}