export const InputMaximoPaginas = () => {
    const container = document.createElement("div");
    container.className = "d-flex align-items-center mb-3";

    container.innerHTML = /* html */ `
        <label class="form-label me-2">
            <p>Máximo de Páginas = 300</p>
            <input
                type="range"
                name="numeroPaginas"
                class="form-control"
                placeholder="20"
                min="0"
                max="300"
                value="300"
            >
        </label>
    `;

    const input = /** @type {HTMLInputElement} */ 
        container.querySelector('input');

    const p = container.querySelector('p');

    input.addEventListener('change', (e) => {
        p.textContent = `Máximo de Páginas = ${input.value}`;
    });

    return container;
}