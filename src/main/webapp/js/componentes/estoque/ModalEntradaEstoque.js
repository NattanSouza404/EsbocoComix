import { Modal } from "@componentes/common/modal.js";

export default class ModalEntradaEstoque extends Modal {
    constructor(cadastrarEntradaEstoque){
        const conteudoModal = ConteudoModal();

        super('modal-entrada-estoque', "Entrada Estoque", conteudoModal);

        this.conteudoModal = conteudoModal;

        /** @type {HTMLButtonElement} */
        (this.conteudoModal.querySelector('#btn-enviar-entrada-estoque')).onclick = async () => {
            const entradaEstoque = await this.confirmarCadastroEntradaEstoque();

            if (entradaEstoque){
                await cadastrarEntradaEstoque(entradaEstoque); 
            }
        }
         
        /** @type {HTMLInputElement} */
        const inputDataPadrao = this.conteudoModal.querySelector('[name = "dataPadrao"]');
        
        /** @type {HTMLInputElement} */
        const inputDataEntrada = this.conteudoModal.querySelector('[name = "dataEntrada"]')

        inputDataPadrao.addEventListener('change', () => {
            if (inputDataPadrao.checked){
                inputDataEntrada.disabled = true;
            } else {
                inputDataEntrada.disabled = false;
            }
        });
    }

    show(quadrinho){
        this.quadrinho = quadrinho;
        this.conteudoModal.querySelector("#titulo-quadrinho").textContent = `Quadrinho: ${this.quadrinho.titulo}`;
        super.show();
    }
    
    async confirmarCadastroEntradaEstoque(){
        if (!confirm("Deseja mesmo realizar essa entrada no estoque?")){
            return;    
        }
        
        const formData = new FormData(this.conteudoModal);

        const entradaEstoque = {
            idQuadrinho: this.quadrinho.id,
            quantidade: formData.get('quantidade'),
            valorCusto: formData.get('valorCusto'),
            fornecedor: formData.get('fornecedor'),
            dataEntrada: formData.get('dataEntrada')
        }

        /** @type {HTMLInputElement} */
        const inputDataPadrao = this.conteudoModal.querySelector('[name = "dataPadrao"]');

        if (!inputDataPadrao.disabled && entradaEstoque.dataEntrada === ""){
            alert('Insira a data corretamente: Dia, mês, ano, hora e minuto.');
            return;
        }

        if (inputDataPadrao.disabled){
            entradaEstoque.dataEntrada = "";
        }

        return entradaEstoque;
    }

}

function ConteudoModal(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-entrada-estoque";
    conteudoModal.className = 'container';
    conteudoModal.style.gap = '10px';

    conteudoModal.innerHTML = /* html */ `
        <h4 id="titulo-quadrinho"></h2>

        <label>
            Quantidade
            <input type="number" name="quantidade">
        </label>

        <label>
            Valor custo
            <input type="number" name="valorCusto">
        </label>

        <label>
            Fornecedor
            <input type="text" name="fornecedor">
        </label>

        <div class="sessao-data">
           <label>
                Usar data padrão (Agora)
                <input
                    type="checkbox"
                    name="dataPadrao"
                    checked="true"
                >
            </label>

            <label>
                Data da entrada
                <input
                    type="datetime-local"
                    name="dataEntrada"
                    disabled
                >
            </label>
        </div>

        <div>
            <button
                id="btn-enviar-entrada-estoque"
                type="button"
                class="btn btn-primary"
            >
                Realizar entrada
            </button>
        </div>
    `;

    return conteudoModal;
}