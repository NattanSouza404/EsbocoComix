export class ContainerItens extends HTMLDivElement {
    constructor(itensPedido){
        super();

        this.className = "container-itens ps-4";

        this.cartoesItens = new Map();

        itensPedido.forEach(item => {

            const div = document.createElement('div');
            div.className = "p-3 mb-2 bg-light rounded border";

            div.innerHTML = `
                <div class="d-flex justify-content-between align-items-center">
                    <img src="${item.urlImagem}">
                    <div>
                        <h6>Produto: ${item.nomeQuadrinho}</h6>
                        <p>Quantidade: ${item.quantidade} unidades</p>
                        ${item.status !== null && item.status !== undefined ? 
                            `<p>${item.status}</p>` : ''
                        }
                    </div>
                    <div>
                        <button class="btn btn-secondary btn-sm botao-troca-item">Pedir Troca</button>
                        <button class="btn btn-secondary btn-sm botao-devolucao-item">Pedir Devolução</button>
                    </div>
                </div>
            `;

            this.cartoesItens.set(div, item);

            this.append(div);
        });
    }
    
}

customElements.define('container-itens', ContainerItens, { extends: "div" });