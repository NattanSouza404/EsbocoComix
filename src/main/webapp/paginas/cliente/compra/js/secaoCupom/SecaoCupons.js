import {  } from "../secaoCartaoCredito/SelecaoCartaoCredito.js";
import { SelecaoCupom } from "./SelecaoCupons.js";

export class SecaoCupom {
    constructor(cupons){

        this.cupons = cupons;
        this.containerCupons = document.getElementById('cupons');
        this.btnAdicionarCupom = document.getElementById('btn-adicionar-cupom');

        if (!Array.isArray(cupons) || cupons.length == 0){
            this.containerCupons.innerHTML = '<p>Você não possui nenhum cupom.</p>'
            this.btnAdicionarCupom.parentNode.removeChild(this.btnAdicionarCupom);
            return;
        }

        this.adicionarCupom();

        this.btnAdicionarCupom.onclick = () => this.adicionarCupom();
    }

    adicionarCupom(){
        this.containerCupons.append(
            new SelecaoCupom(this.cupons)
        );
    }

    getCuponsPedido(){
        const selectCupons = document.querySelectorAll('.selecao-cupom');

        const cuponsPedido = [];
        selectCupons.forEach(select => {
            cuponsPedido.push(
                select.getCupomPedido()
            )
        });

        return cuponsPedido;
    }
   
}