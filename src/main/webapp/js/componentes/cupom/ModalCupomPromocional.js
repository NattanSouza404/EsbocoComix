import { Modal } from "@componentes/common/modal.js";

export default class ModalCupomPromocional extends Modal {
    constructor(inserirCupom){
        const conteudoModal = ConteudoModalCupom();

        super('modal-cupom-promocional', "Adicionar cupom promocional", conteudoModal);

        this.conteudoModal = conteudoModal;

        this.conteudoModal.querySelector('button').onclick = async () => {
            const cupom = this.confirmarCadastroCupom();

            if (cupom){
                await inserirCupom(cupom);
            }
        }
    }

    show(cliente){
        this.conteudoModal.querySelector("#nome-cliente").textContent = `
            Cliente: ${cliente}
        `;
        this.cliente = cliente;
        super.show();
    }
    
    confirmarCadastroCupom(){
        const confirmacaoUsuario = confirm("Deseja mesmo cadastrar esse cupom?");

        if (!confirmacaoUsuario){
            return;
        }
                
        const formData = new FormData(this.conteudoModal);

        return {
            idCliente: this.cliente.id,
            valor: formData.get('valor'),
            isPromocional: true
        };
        
    }

}

function ConteudoModalCupom(){
    const conteudoModal = document.createElement('form');
    conteudoModal.id = "form-adicionar-cupom-promocional";
    conteudoModal.className = 'container';
    conteudoModal.style.gap = '10px';

    conteudoModal.innerHTML = /* html */ `
        <p id="nome-cliente"></p>

        <label>
            Valor do cupom

            <input type="number" name="valor">
        </label>

        <div>
            <button type="button" class="btn btn-primary">Cadastrar cupom</button>
        </div>
    `;

    return conteudoModal;
}