import { alertarErro } from "@api/alertErro.js";
import { inserirCupom } from "@api/cupom.api.js";
import { Modal } from "@componentes/common/modal.js";

export default class ModalCupomPromocional extends Modal {
    constructor(){
        const conteudoModal = ConteudoModalCupom();

        super('modal-cupom-promocional', "Adicionar cupom promocional", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('button').onclick = async () => {
            await this.enviarFormulario()
        }
    }

    show(cliente){
        this.cliente = cliente;
        this.conteudoModal.querySelector("#nome-cliente").textContent = `Cliente: ${this.cliente.nome}`;
        super.show();
    }
    
    async enviarFormulario(){
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse cupom?");

        if (!confirmacaoUsuario){
            return;
        }
                
        const formData = new FormData(this.conteudoModal);

        const cupom = {
            idCliente: this.cliente.id,
            valor: formData.get('valor'),
            isPromocional: true
        }

        try {
            await inserirCupom(cupom);
            alert('Cadastrado com sucesso');    
        } catch (error){
            alertarErro(error);
        }
        
    }

}

function ConteudoModalCupom(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-adicionar-cupom-promocional";
    conteudoModal.className = 'container';
    conteudoModal.style.gap = '10px';

    conteudoModal.innerHTML = `
        <p id="nome-cliente"></p>

        <label>
            Valor do cupom

            <input type="number" name="valor"></input>
        </label>

        <div>
            <button type="button" class="btn btn-primary">Cadastrar cupom</button>
        </div>
    `;

    return conteudoModal;
}