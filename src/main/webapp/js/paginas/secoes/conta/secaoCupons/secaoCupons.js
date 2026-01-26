import { CartaoCupom } from "./CartaoCupom.js";

export class SecaoCupom {
    constructor(cupons) {

        this.elementoHTML = document.getElementById('secao-cupons');
        this.containerCupons = this.elementoHTML.querySelector('.container-cupom');

        this.atualizar(cupons);
    }

    atualizar(cupons) {

        if (cupons && Array.isArray(cupons) && cupons.length > 0){
            this.containerCupons.innerHTML = "";
    
            let contador = 1;
            cupons.forEach(
                (c) => {
                    const cartao = new CartaoCupom(c);

                    cartao.querySelector('.titulo-cupom').textContent =`
                        ${contador++}º ${cartao.querySelector('.titulo-cupom').textContent}
                    `;

                    this.containerCupons.append(
                        cartao
                    );

                }
            )
        } else {
            this.containerCupons.innerHTML = "<h3>Nenhum cupom disponível.</h3>"
        }

    }
}
