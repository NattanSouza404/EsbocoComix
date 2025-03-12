export async function retornarCliente(id) {
    try {
        const response = await fetch("/cliente?id="+id);
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

export async function retornarAllClientes() {
    try {
        const response = await fetch("/cliente");
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

export async function retornarEnderecos(idCliente){
    try {
        const response = await fetch("/endereco?idcliente="+idCliente);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
} 

export async function retornarCartoesCredito(idCliente) {
    try {
        const response = await fetch("/cartaocredito?idcliente="+idCliente);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
        document.getElementById("resultados").textContent = "Erro carregando dados.";
    }
}

export async function inserirCliente(pedidoCadastrarCliente){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = "/cliente";

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(pedidoCadastrarCliente)
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
        document.getElementById("resultados").textContent = "Erro ao cadastrar.";
    }
}

export async function atualizarCliente(cliente, opcao){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar ?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/cliente";
        if (opcao != null){
            url += "?opcao="+opcao;
        }

        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(cliente)
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

export async function atualizarEndereco(endereco){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar esse endereço?"); 

        if (!confirmacaoUsuario){
            return;
        }

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

export async function deletar(id){
    
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar o cadastro desse cliente?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = '/cliente'+id;

        const response = await fetch(url, {
            method: 'DELETE'
        });

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