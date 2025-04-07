export async function retornarCarrinho() {
    try {
        let url = "/carrinhoapi";
        const response = await fetch(url);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function adicionarItemAoCarrinho(item){
    try {
        const url = "/carrinhoapi";

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const result = await fetch(url, option);

        if (result.status === 201) {
            alert('Cadastrado com sucesso');
        }
        else {
            const resposta = await result.json();
            alert("Erro ao cadastrar: "+resposta.erro);
        }
    }
    catch(error){

    }

}

export async function atualizarItemCarrinho(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar quantidade desse item?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/carrinhoapi";

        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const result = await fetch(url, option);

        if (result.status === 200) {
            alert('Atualizado com sucesso!');
        }
        else {
            const resposta = await result.json();
            alert("Erro ao atualizar: "+resposta.erro);
        }

    } catch (error){
        console.error('Erro ao atualizar:', error);
        document.getElementById("resultados").textContent = "Erro ao atualizar.";
    }
}

export async function  deletarItemCarrinho(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse item?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = '/carrinhoapi';

        const option = {
            method: 'DELETE',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }

        const response = await fetch(url, option);

        if (response.status === 204) {
            alert('Deletado com sucesso');
        }
        else {
            const resposta = await result.json();
            alert("Erro ao deletar: "+resposta.erro);
        }

    } catch (error){
        console.error('Erro ao deletar:', error);
    }
}