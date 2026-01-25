import { consultarTodosQuadrinhos } from "@api/quadrinho.api.js";
import { CartaoProduto } from "@componentes/quadrinho/CartaoProduto.js";
import { consultarTodasCategorias, filtrarTodosQuadrinhos } from "@api/quadrinho.api.js";
import { alertarErro } from "@api/alertErro.js";
import { formToObject } from "../script.js";

export async function initPagina(){
    let quadrinhos;
    let categorias;

    try {
        quadrinhos = await consultarTodosQuadrinhos();
        categorias = await consultarTodasCategorias();
    } catch (error){
        alertarErro(error);
    }

    quadrinhos.forEach(quadrinho => {
        document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
    });

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
        const valor = /** @type {HTMLInputElement} */
            (document.querySelector('[name = "numeroPaginas"]')).value;
        document.getElementById('input-maximo-paginas').textContent = `Máximo de Páginas = ${valor}`;
    });

    async function pesquisarQuadrinhos(){
        const filtro = formToObject((document.getElementById('busca-quadrinhos')));
        const categorias = [];
        document.querySelectorAll('[name = "categorias"').forEach(
            (/** @type {HTMLInputElement} */
                input
            ) => {
                if (input.checked){
                    categorias.push(input.value);
                }
            }
        );

        filtro.categorias = categorias;

        let quadrinhos;
        try {
            quadrinhos = await filtrarTodosQuadrinhos(filtro);
        } catch (error){
            alertarErro(error);
        }

        if (!Array.isArray(quadrinhos) || quadrinhos.length === 0){
            document.getElementById('container-produtos').innerHTML = `<h1 class="text-center">Nenhum item encontrado!</h1>`; 
            return;
        }

        document.getElementById('container-produtos').innerHTML = "";  
        quadrinhos.forEach(quadrinho => {
            document.getElementById('container-produtos').append(new CartaoProduto(quadrinho))
        });
    }
}