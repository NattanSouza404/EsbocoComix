import { inserirEntradaEstoque } from "../../../../js/api/apiEstoque.js";
import { Modal } from "/js/componentes/modal.js";

export default class ModalEntradaEstoque extends Modal {
    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-entrada-estoque', "Entrada Estoque", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('button').onclick = () => {
            this.enviarFormulario()
        }

        const inputDataPadrao = this.conteudoModal.querySelector('[name = "dataPadrao"]');
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
    
    enviarFormulario(){
        const formData = new FormData(this.conteudoModal);

        const entradaEstoque = {
            idQuadrinho: this.quadrinho.id,
            quantidade: formData.get('quantidade'),
            valorCusto: formData.get('valorCusto'),
            fornecedor: formData.get('fornecedor'),
            dataEntrada: formData.get('dataEntrada')
        }

        const inputDataPadrao = this.conteudoModal.querySelector('[name = "dataPadrao"]');

        if (!inputDataPadrao.disabled && entradaEstoque.dataEntrada === ""){
            alert('Insira a data corretamente: Dia, mês, ano, hora e minuto.');
            return;
        }

        if (inputDataPadrao.disabled){
            entradaEstoque.dataEntrada = "";
        }

        if (confirm("Deseja mesmo realizar essa entrada no estoque?")){
            inserirEntradaEstoque(entradaEstoque);
        }

    }

}

function ConteudoModal(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-adicionar-cupom-promocional";
    conteudoModal.className = 'container';
    conteudoModal.style.gap = '10px';

    conteudoModal.innerHTML = `
        <h4 id="titulo-quadrinho"></h2>

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

        <div class="sessao-data">
           <label>
                Usar data padrão (Agora)
                <input type="checkbox" name="dataPadrao" checked="true">
            </label>

            <label>
                Data da entrada
                <input type="datetime-local" name="dataEntrada" disabled>
            </label>
        </div>

        <div>
            <button type="button" class="btn btn-primary">Realizar entrada</button>
        </div>
    `;

    return conteudoModal;
}