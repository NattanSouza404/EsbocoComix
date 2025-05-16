import { alertarErro } from "./alertErro.js";

const URL = '/carrinhoapi';

export async function retornarCarrinho() {
    try {
        const response = await fetch(URL);
        return await response.json();
    } catch (error) {
        alertarErro('Erro buscando dados:', error);
    }
}

export async function adicionarItemAoCarrinho(item){
    try {
        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const result = await fetch(URL, option);

        if (result.status === 201) {
            alert('Item adicionado com sucesso!');
            return;
        }

        const resposta = await result.json();

        throw new Error('Erro ao cadastrar: '+resposta.erro);
    }
    catch(error){
        alertarErro(error);
    }

}

export async function atualizarItemCarrinho(item){
    try {
        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const result = await fetch(URL, option);

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

        const response = await fetch(URL, option);

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