import { alertarErro } from "@api/alertErro.js";
import { consultarTodasCategorias, consultarTodosQuadrinhos, filtrarTodosQuadrinhos } from "@api/quadrinho.api.js";
import { CarrosselHome } from "@componentes/layout/CarrosselHome.js";
import { CartaoProduto } from "@componentes/quadrinho/CartaoProduto.js";
import { CheckCategoria } from "@componentes/quadrinho/CheckCategoria.js";
import { formToObject } from "../script.js";
import { InputMaximoPaginas } from "@componentes/quadrinho/InputMaximoPaginas.js";

const getElementos = () => {
    return {
        containerCarrossel: document.getElementById('container-carrossel'),
        containerProdutos: document.getElementById('container-produtos'),
        btnBuscarQuadrinho: document.getElementById('btn-buscar-quadrinho'),
        buscaQuadrinhos: document.getElementById('busca-quadrinhos'),
        fieldSetCategorias: document.getElementById('fieldset-categorias'),
        inputMaximoPaginas: document.getElementById("input-maximo-paginas"),
        checksCategorias: document.querySelectorAll('[name = "categorias"]')
    };
}

export async function initPagina(){
    try {
        const el = getElementos();

        el.containerCarrossel.append(CarrosselHome());

        const quadrinhos = await consultarTodosQuadrinhos();
        const categorias = await consultarTodasCategorias();
        
        quadrinhos.forEach(quadrinho => {
            el.containerProdutos.append(new CartaoProduto(quadrinho))
        });

        categorias.forEach(c => {
            el.fieldSetCategorias.append(CheckCategoria(c));
        });

        el.btnBuscarQuadrinho.onclick = async () => {
            pesquisarQuadrinhos();
        }

        el.inputMaximoPaginas.append(InputMaximoPaginas());

        el.buscaQuadrinhos.addEventListener(
            "keypress", 
            e => {
                if (e.key === "Enter") {
                    e.preventDefault();
                    pesquisarQuadrinhos();
                }
            }
        );

    } catch (error){
        alertarErro(error);
    }
}

async function pesquisarQuadrinhos(){
    try {
        const el = getElementos();

        const form = el.buscaQuadrinhos;

        const dadosFormulario = formToObject(form);
        const categorias = listarCategoriasSelecionadas();

        const filtro = {
            ...dadosFormulario,
            categorias
        };

        const quadrinhos = await filtrarTodosQuadrinhos(filtro);

        if (!Array.isArray(quadrinhos) || quadrinhos.length === 0){
            el.containerProdutos.innerHTML = /* html */
                `<h1 class="text-center">Nenhum item encontrado!</h1>`; 
            
            return;
        }

        el.containerProdutos.innerHTML = "";  
        quadrinhos.forEach(quadrinho => {
            el.containerProdutos.append(new CartaoProduto(quadrinho))
        });

    } catch (error){
        alertarErro(error);
    }
}

function listarCategoriasSelecionadas(){
    const categoriasSelecionadas = [];

    getElementos().checksCategorias.forEach(
        (/** @type {HTMLInputElement} */
            input
        ) => {
            if (input.checked){
                categoriasSelecionadas.push(input.value);
            }
        }
    );

    return categoriasSelecionadas;
}