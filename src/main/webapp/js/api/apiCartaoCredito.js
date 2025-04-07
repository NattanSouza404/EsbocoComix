export async function retornarCartoesCredito(idCliente) {
    try {
        const response = await fetch("/cartaocredito?idcliente="+idCliente);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

export async function atualizarCartaoCredito(cartaoCredito){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse cartão de crédito?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/cartaocredito";

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
        document.getElementById("resultados").textContent = "Erro ao atualizar.";
    }
    
}

export async function deletarCartaoCredito(cartaoCredito){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse cartão de crédito?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = '/cartaocredito';

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