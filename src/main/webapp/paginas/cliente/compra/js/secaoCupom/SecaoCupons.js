import {  } from "../secaoCartaoCredito/SelecaoCartaoCredito.js";
import { SelecaoCupom } from "./SelecaoCupons.js";

export class SecaoCupom {
    constructor(cupons){

        this.cupons = cupons;

        this.containerCupons = document.getElementById('cupons');

        this.adicionarCupom();

        document.getElementById('btn-adicionar-cupom').onclick = () => this.adicionarCupom();
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