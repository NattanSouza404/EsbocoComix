import { formatarData } from "../../script.js";

export const TabelaInfoProduto = (quadrinho) => {
    const tabela = document.createElement('table');
    tabela.className = "table table-hover";

    const categorias = (quadrinho.categorias ?? [])
        .map(c => c.nome)
        .join(", ");

    tabela.innerHTML =  /* html */ `
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
    `;

    return tabela;
}