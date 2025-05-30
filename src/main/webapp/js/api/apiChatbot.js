const PATH = "http://localhost:8000/";

export async function retornarRespostaIA(mensagem){
    try {
        const url = `${PATH}get-message`;

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(
                { 
                    idcliente: localStorage.getItem('idcliente'),
                    mensagem: mensagem,
                }
            )
        }

        const result = await fetch(url, option);

        if (result.status !== 200) {
            alert("Erro no assistente virtual: "+result.erro);
        }

        return result.json();

    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}