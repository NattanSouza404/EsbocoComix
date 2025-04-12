export async function retornarCupons(idCliente){
    try {
        const response = await fetch("/cupom?idcliente="+idCliente);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
} 