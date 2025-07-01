const PATH = "/api/estoque";

export async function inserirEntradaEstoque(entradaEstoque) {
    try {
        const url = PATH;

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(entradaEstoque)
        }

        const result = await fetch(url, option);

        if (result.status === 201) {
            alert('Entrada realizada!');
        }
        else {
            const resposta = await result.json();
            alert("Erro ao realizar entrada: "+resposta.erro);
        }
        
    }
    catch (error){
        console.error('Erro ao realizar pedido:', error);
    }
}

export async function consultarEntradasEstoque(){
     try {
        const response = await fetch(PATH);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}