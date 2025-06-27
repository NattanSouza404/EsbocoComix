const PATH = "/api/endereco";

export async function retornarEnderecos(idCliente){
    try {
        const response = await fetch(`${PATH}?idcliente=${idCliente}`);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function inserirEndereco(endereco){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse endereço?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(endereco)
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

export async function atualizarEndereco(endereco){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse endereço?"); 

        if (!confirmacaoUsuario){
            return;
        }

        endereco.idCliente = localStorage.getItem('idcliente');

        let url = PATH;

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
    }
    
}

export async function deletarEndereco(endereco){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar esse endereço?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

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
            const resposta = await response.json();
            alert("Erro ao deletar: "+resposta.erro);
        }

    } catch (error){
        alert(error);
    }
}