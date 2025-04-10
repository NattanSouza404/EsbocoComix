import { retornarQuadrinho } from "/js/api/apiQuadrinho.js";
import { adicionarItemAoCarrinho } from "/js/api/apiCarrinho.js";
import { formatarPreco } from "/js/script.js";

const uRLSearchParams = new URLSearchParams(window.location.search);
const quadrinho = await retornarQuadrinho(uRLSearchParams.get('id'));

const img = document.createElement('img');
img.className = 'img-fluid rounded-start';
img.alt = 'Imagem do produto';
img.src = quadrinho.urlImagem;

document.getElementById('secao-imagem').append(img);

document.getElementById('header-produto').innerHTML = `
    <h1 class="card-title">${quadrinho.titulo}</h1>
    <p class="card-text fs-4 text-success">${formatarPreco(quadrinho.preco)}</p>
`

const corpoTabela = document.createElement('tbody');
corpoTabela.innerHTML = `
    <tbody>
        <tr>
            <td>Ano: ${quadrinho.ano}</td>
        </tr>
        <tr>
            <td>Autor: ${quadrinho.autor}</td>
        </tr>
        <tr>
            <td>Título: ${quadrinho.titulo}</td>
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
            <td>Categorias: ${quadrinho.categoria}</td>
        </tr>
    </tbody>
`

document.getElementById('tabela-info').append(corpoTabela);

window.adicionarItem = () => {

    adicionarItemAoCarrinho(
        {
            idQuadrinho: quadrinho.id,
            preco: quadrinho.preco,
            quantidade: document.getElementsByName('quantidade')[0].value,
            nome: quadrinho.titulo,
            urlImagem: quadrinho.urlImagem
        } 
    )

};