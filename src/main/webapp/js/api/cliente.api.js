import { estourarErroAPI } from "./alertErro.js";

const PATH = "/api/cliente";

export async function retornarCliente(id) {
    const resposta = await fetch(`${PATH}/id?id=${id}`);

    if (resposta.status !== 200){
        await estourarErroAPI(resposta);
    }

    return await resposta.json();
}

export async function retornarAllClientes(filtro) {
    let url = PATH;
    if (filtro !== undefined){
        url += "/filtrar";
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

export async function atualizarCliente(cliente){
    let url = PATH;

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
    let url = `${PATH}/atualizar-senha`;
    
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
    let url = `${PATH}/atualizar-status-cadastro`;

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