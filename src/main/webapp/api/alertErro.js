export function alertarErro(error){
    alert(error.message);
    console.error(error.message);
}

export async function estourarErroAPI(resposta){
    const json = await resposta.json();
    throw new Error(json.erro);
}