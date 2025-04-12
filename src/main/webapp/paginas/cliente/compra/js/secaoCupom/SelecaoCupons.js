import { formatarPreco } from "/js/script.js";

export class SelecaoCupom extends HTMLDivElement {
    constructor(cupons){
        super();

        this.className = 'selecao-cupom d-flex mb-3'; 
        
        this.innerHTML = `
            <select class="form-select mt-2"></select>
            <button class="btn btn-primary btn-sm">Remover</button>
        `;

        const btnRemover = this.querySelector('button');
        btnRemover.onclick = () => {
            btnRemover.parentElement.remove(btnRemover);
        };

        this.preencher(cupons);
    }

    preencher(cupons){
        const select = this.querySelector('select');

        cupons.forEach(cupom => {
            const option = document.createElement('option');
            option.value = cupom.id;

            let tipoCupom = '';
            if (cupom.isPromocional){
                tipoCupom = 'Promocional';
            }

            if (cupom.isTroca){
                tipoCupom = 'de Troca'
            }

            option.textContent = `Cupom ${tipoCupom} ${formatarPreco(cupom.valor)}`;

            select.append(option);
        });
    }

    getCupomPedido(){
        return {
            idCupom: this.querySelector('select').value 
        };
    }

}

customElements.define('selecao-cupom', SelecaoCupom, { extends: 'div'});