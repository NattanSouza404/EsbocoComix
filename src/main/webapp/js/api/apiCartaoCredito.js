const PATH = "/api/cartaocredito";

export async function retornarCartoesCredito(idCliente) {
    try {
        const response = await fetch(`${PATH}?idcliente=${idCliente}`);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

export async function inserirCartaoCredito(cartao){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse cartão de crédito?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(cartao)
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
    catch (error){
        console.error('Erro ao cadastrar:', error);
    }
} 

export async function atualizarCartaoCredito(cartaoCredito){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse cartão de crédito?"); 

        if (!confirmacaoUsuario){
            return;
        }

        cartaoCredito.idCliente = localStorage.getItem('idcliente');

        let url = PATH;

        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(cartaoCredito)
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
    }
    
}

export async function deletarCartaoCredito(cartaoCredito){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse cartão de crédito?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

        const option = {
            method: 'DELETE',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(cartaoCredito)
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