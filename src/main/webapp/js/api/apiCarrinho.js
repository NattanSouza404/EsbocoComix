import { alertarErro } from "./alertErro.js";

const PATH = '/api/carrinho';

export async function retornarCarrinho() {
    const response = await fetch(PATH);

    if (response.status !== 200){
        throw new Error((await response.json()).erro);
    }

    return await response.json();
}

export async function adicionarItemAoCarrinho(item){
    const option = {
        method: 'POST',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }

    const result = await fetch(PATH, option);

    if (result.status !== 201) {
        const resposta = await result.json();
        throw new Error(resposta.erro);
    }
}

export async function atualizarItemCarrinho(item){
    try {
        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const result = await fetch(PATH, option);

        if (result.status === 200) {
            alert('Item atualizado com sucesso!');
            return;
        }

        const resposta = await result.json();
        throw new Error("Erro ao atualizar: "+resposta.erro);
    } catch (error){
        alertarErro(error);
    }
}

export async function  deletarItemCarrinho(item){
    try {
        const option = {
            method: 'DELETE',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const response = await fetch(PATH, option);

        if (response.status === 204) {
            alert('Deletado com sucesso');
            return;
        }

        const resposta = await result.json();
        throw new Error('Erro ao deletar: '+resposta.erro);

    } catch (error){
        alertarErro(error);
    }
}