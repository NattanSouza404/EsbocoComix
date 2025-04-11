export async function retornarEnderecos(idCliente){
    try {
        const response = await fetch("/endereco?idcliente="+idCliente);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
} 

export async function atualizarEndereco(endereco){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse endereço?"); 

        if (!confirmacaoUsuario){
            return;
        }

        endereco.idCliente = localStorage.getItem('idcliente');

        let url = "/endereco";

        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(endereco)
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

export async function deletarEndereco(endereco){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse endereço?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = '/endereco';

        const option = {
            method: 'DELETE',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(endereco)
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