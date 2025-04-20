export async function retornarPedidosTroca(){
    try {
        const response = await fetch('/pedidotroca');
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function atualizarStatusItemPedido(item){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo pedir troca para esse item?"); 

        if (!confirmacaoUsuario){
            return;
        }

        let url = "/pedidotroca?opcao=atualizarstatus";

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