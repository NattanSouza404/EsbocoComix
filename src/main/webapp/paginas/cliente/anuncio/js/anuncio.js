import { retornarQuadrinho } from "../../../../js/api/apiQuadrinho.js";
import { adicionarItemAoCarrinho } from "../../../../js/api/apiCarrinho.js";
import { formatarData, formatarPreco } from "/js/script.js";

const uRLSearchParams = new URLSearchParams(window.location.search);

let quadrinho;
try {
    quadrinho = await retornarQuadrinho(uRLSearchParams.get('id'));

    carregarPagina(quadrinho);

} catch (error){
    document.getElementById('container-anuncio').innerHTML = `
        <h3 class="text-center">Produto não encontrado!</h3>
    `;
}

function carregarPagina(){
    document.querySelector('title').textContent = quadrinho.titulo;

    document.getElementById('secao-imagem').innerHTML = `
        <img src="${quadrinho.urlImagem}" class="img-fluid rounded-start" alt="Imagem do produto"></img>
    `;

    document.getElementById('header-produto').innerHTML = `
        <h1 class="card-title">${quadrinho.titulo}</h1>
        <p class="card-text fs-4 text-success">${formatarPreco(quadrinho.preco)}</p>
    `

    let estoque = quadrinho.quantidadeEstoque;
    if (quadrinho.isForaDeEstoque){
        estoque = "Fora de Estoque";
    }

    document.getElementById('estoque').textContent = `Estoque: ${estoque}`;

    let categorias = '';
    quadrinho.categorias.forEach(categoria => {
        categorias += categoria.nome+", ";
    });

    const corpoTabela = document.createElement('tbody');
    corpoTabela.innerHTML = `
        <tbody>
            <tr>
                <td>Ano: ${formatarData(quadrinho.ano)}</td>
            </tr>
            <tr>
                <td>Autor: ${quadrinho.autor}</td>
            </tr>
            <tr>
                <td>Editora: ${quadrinho.editora}</td>
            </tr>
            <tr>
                <td>Edição: ${quadrinho.edicao}</td>
            </tr>
            <tr>
                <td>ISBN: ${quadrinho.isbn}</td>
            </tr>
            <tr>
                <td>Número de Páginas: ${quadrinho.numeroPaginas}</td>
            </tr>
            <tr>
                <td>Sinopse: ${quadrinho.sinopse}</td>
            </tr>
            <tr>
                <td>Dimensões: ${quadrinho.altura}cm X ${quadrinho.largura}cm X ${quadrinho.profundidade}cm</td>
            </tr>
            <tr>
                <td>Peso: ${quadrinho.peso}g</td>
            </tr>
            <tr>
                <td>Código de Barras: ${quadrinho.codigoBarras}</td>
            </tr>
            <tr>
                <td>Categorias: ${categorias}</td>
            </tr>
            <tr>
                <td>Grupo de precificação: ${quadrinho.grupoPrecificacao.nome}
            </tr>
        </tbody>
    `

    document.getElementById('tabela-info').append(corpoTabela);

    window.adicionarItem = async () => {

        try {
            await adicionarItemAoCarrinho(
                {
                    idQuadrinho: quadrinho.id,
                    preco: quadrinho.preco,
                    quantidade: document.getElementsByName('quantidade')[0].value,
                    nome: quadrinho.titulo,
                    urlImagem: quadrinho.urlImagem
                } 
            );

            alert('Item adicionado com sucesso!');
        } catch (error){
            alert("Erro ao adicionar quadrinho: "+error);
        }

    };
}