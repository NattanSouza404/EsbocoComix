const PATH = "/api/analise";

export async function retornarAnalise(){
    try {
        const response = await fetch(PATH);
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}