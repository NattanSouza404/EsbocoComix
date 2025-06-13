import { consultarTodosQuadrinhos } from "/js/api/apiQuadrinho.js";
import { CartaoProduto } from "./CartaoProduto.js";
import { consultarTodasCategorias, filtrarTodosQuadrinhos } from "../../../../js/api/apiQuadrinho.js";

const quadrinhos = await consultarTodosQuadrinhos();

quadrinhos.forEach(quadrinho => {
    document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
});

const categorias = await consultarTodasCategorias();

categorias.forEach(c => {
    document.getElementById("fieldset-categorias").insertAdjacentHTML(
        "beforeend", 
        `
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="categorias" id="cat${c.nome.replaceAll(" ", "")}" value="${c.nome}">
            <label class="form-check-label" for="cat${c.nome.replaceAll(" ", "")}">${c.nome}</label>
        </div>
        `
    );
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

document.querySelector('[name = "numeroPaginas"]').addEventListener('change', (e) => {
    const valor = document.querySelector('[name = "numeroPaginas"]').value;
    document.getElementById('input-maximo-paginas').textContent = `Máximo de Páginas = ${valor}`;
});

async function pesquisarQuadrinhos(){
    const formData = new FormData(document.getElementById('busca-quadrinhos'));
    
    const filtro = Object.fromEntries(formData);

    const categorias = [];
    document.querySelectorAll('[name = "categorias"').forEach(
        (input) => {
            if (input.checked){
                categorias.push(input.value);
            }
        }
    );

    filtro.categorias = categorias;

    const quadrinhos = await filtrarTodosQuadrinhos(
        filtro
    );

    if (!Array.isArray(quadrinhos) || quadrinhos.length === 0){
        document.getElementById('container-produtos').innerHTML = `<h1 class="text-center">Nenhum item encontrado!</h1>`; 
        return;
    }

    document.getElementById('container-produtos').innerHTML = "";  
    quadrinhos.forEach(quadrinho => {
        document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
    });
}