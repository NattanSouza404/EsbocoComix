import { inserirEntradaEstoque } from "/js/api/apiEstoque.js";
import { Modal } from "/js/componentes/modal.js";

export default class ModalEntradaEstoque extends Modal {
    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-entrada-estoque', "Entrada Estoque", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('button').onclick = () => {
            this.enviarFormulario()
        }
    }

    show(quadrinho){
        this.quadrinho = quadrinho;
        this.conteudoModal.querySelector("#titulo-quadrinho").textContent = `Quadrinho: ${this.quadrinho.titulo}`;
        super.show();
    }
    
    enviarFormulario(){
        const formData = new FormData(this.conteudoModal);

        const entradaEstoque = {
            idQuadrinho: this.quadrinho.id,
            quantidade: formData.get('quantidade'),
            valorCusto: formData.get('valorCusto'),
            fornecedor: formData.get('fornecedor')
        }

        inserirEntradaEstoque(entradaEstoque);
    }

}

function ConteudoModal(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-adicionar-cupom-promocional";
    conteudoModal.className = 'container';
    conteudoModal.style.gap = '10px';

    conteudoModal.innerHTML = `
        <p id="titulo-quadrinho"></p>

        <label>
            Quantidade
            <input type="number" name="quantidade"></input>
        </label>

        <label>
            Valor custo
            <input type="number" name="valorCusto"></input>
        </label>

        <label>
            Fornecedor
            <input type="text" name="fornecedor"></input>
        </label>

        <div>
            <button type="button" class="btn btn-primary">Realizar entrada</button>
        </div>
    `;

    return conteudoModal;
}