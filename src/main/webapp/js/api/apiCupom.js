const PATH = "/api/cupom";

export async function retornarCupons(idCliente){
    try {
        const response = await fetch(`${PATH}?idcliente=${idCliente}`);
        return await response.json();
    } catch (error) {
        console.error('Erro buscando dados:', error);
    }
}

export async function inserirCupom(cupom){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse cupom?");

        if (!confirmacaoUsuario){
            return;
        }

        const url = PATH;

        const option = {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(cupom)
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