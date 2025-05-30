const PATH = "/api/cliente";

export async function retornarCliente(id) {
    try {
        const response = await fetch(`${PATH}?id=${id}`);
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function retornarAllClientes(filtro) {
    try {
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

        const response = await fetch(url);
        return await response.json();        
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function inserirCliente(pedidoCadastrarCliente){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

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
    }
}

export async function atualizarCliente(cliente, opcao){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar ?"); 

        if (!confirmacaoUsuario){
            return;
        }

        cliente.id = localStorage.getItem('idcliente');

        let url = PATH;
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
    }
    
}

export async function atualizarSenha(cliente){

    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar ?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = `${PATH}?opcao=atualizarsenha`;

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
    }
    
}

export async function deletar(id){
    
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo deletar o cadastro desse cliente?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = `${PATH}?id=${id}`;

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