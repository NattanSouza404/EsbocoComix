export async function consultarTodosQuadrinhos(){
    try {
        let url = "/quadrinho";

        const response = await fetch(url);
        return await response.json();        
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function retornarQuadrinho(id) {
    try {
        const response = await fetch("/quadrinho?id="+id);
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}