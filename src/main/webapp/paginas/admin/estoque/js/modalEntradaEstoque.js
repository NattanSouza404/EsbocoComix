import { alertarErro } from "../../../../js/api/alertErro.js";
import { inserirEntradaEstoque } from "../../../../js/api/apiEstoque.js";
import { Modal } from "/js/componentes/modal.js";

export default class ModalEntradaEstoque extends Modal {
    constructor(){
        const conteudoModal = ConteudoModal();

        super('modal-entrada-estoque', "Entrada Estoque", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('#btn-enviar-entrada-estoque').onclick = () => {
            this.enviarFormulario();
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
    
    async enviarFormulario(){
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

        const inputDataPadrao = this.conteudoModal.querySelector('[name = "dataPadrao"]');

        if (!inputDataPadrao.disabled && entradaEstoque.dataEntrada === ""){
            alert('Insira a data corretamente: Dia, mês, ano, hora e minuto.');
            return;
        }

        if (inputDataPadrao.disabled){
            entradaEstoque.dataEntrada = "";
        }

        try {
            await inserirEntradaEstoque(entradaEstoque);
            alert('Entrada realizada!');
        } catch (error){
            alertarErro(error);
        }

    }

}

function ConteudoModal(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-entrada-estoque";
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
            <button id="btn-enviar-entrada-estoque" type="button" class="btn btn-primary">Realizar entrada</button>
        </div>
    `;

    return conteudoModal;
}