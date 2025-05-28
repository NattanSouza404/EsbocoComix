export async function retornarAnalise(){
    try {
        const response = await fetch("/apianalise");
        return await response.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}