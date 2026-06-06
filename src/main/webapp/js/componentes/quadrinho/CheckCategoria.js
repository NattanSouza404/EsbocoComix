export const CheckCategoria = (categoria) => {
    const idCategoria = `cat${categoria.nome.replaceAll(" ", "")}`;
    
    const check = document.createElement("div");
    check.className = "form-check form-check-inline";

    check.innerHTML = /* html */ `
        <input 
            class="form-check-input"
            type="checkbox"
            name="categorias"
            id=${idCategoria}
            value="${categoria.nome}"
        >

        <label class="form-check-label" for=${idCategoria}>
            ${categoria.nome}
        </label>
    `
    return check;
}