const PATH = "/api/pedido_pos_venda";

export async function retornarPedidosPosVenda(){
    try {
        let url = PATH;
        const response = await fetch(url);

        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function retornarPedidosPosVendaByCliente(idCliente){
    try {
        let url = `${PATH}?idcliente=${idCliente}`;
        const response = await fetch(url);

        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function inserirPedidoPosVenda(pedido) {
    try {
        const url = PATH;

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

export async function atualizarStatusPedidoPosVenda(pedido){
    try {
        let url = PATH;

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