export async function retornarPedidos(idCliente){
    try {

        let url = '/pedido';
        if (idCliente !== null && idCliente !== undefined){
            url += '?idcliente='+idCliente;
        }
        const response = await fetch(url);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function enviarPedido(pedido) {
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo realizar essa compra?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = "/pedido";

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(pedido)
        }

        const result = await fetch(url, option);

        if (result.status === 201) {
            alert('Pedido realizado');
        }
        else {
            const resposta = await result.json();
            alert("Erro ao realizar pedido: "+resposta.erro);
        }
        
    }
    catch (error){
        console.error('Erro ao realizar pedido:', error);
    }
}

export async function atualizarStatusPedido(pedido){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo atualizar o status desse pedido?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/pedido?opcao=atualizarstatuspedido";

        const option = {
            method: 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(pedido)
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

export async function atualizarStatusItemPedido(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo pedir troca para esse item?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/pedido?opcao=atualizarstatusitem";

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
    }
}