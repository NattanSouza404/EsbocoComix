import { consultarTodosQuadrinhos } from "/js/api/apiQuadrinho.js";
import { CartaoProduto } from "./CartaoProduto.js";
import { filtrarTodosQuadrinhos } from "../../../../js/api/apiQuadrinho.js";

const quadrinhos = await consultarTodosQuadrinhos();

quadrinhos.forEach(quadrinho => {
    document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
});

document.getElementById('btn-buscar-quadrinho').onclick = async () => {
    pesquisarQuadrinhos();
}

document.getElementById('busca-quadrinhos').addEventListener(
    "keypress", 
    e => {
        if (e.key === "Enter") {
            e.preventDefault();
            pesquisarQuadrinhos();
        }
    }
);

async function pesquisarQuadrinhos(){
    const formData = new FormData(document.getElementById('busca-quadrinhos'));
    
    const quadrinhos = await filtrarTodosQuadrinhos(
        Object.fromEntries(formData)
    );

    if (Array.isArray(quadrinhos)){
        document.getElementById('container-produtos').innerHTML = "";
        
        quadrinhos.forEach(quadrinho => {
            document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
        });
    }
}