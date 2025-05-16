import { CartaoCupom } from "./CartaoCupom.js";

export class SecaoCupom extends HTMLElement {
    constructor(cupons) {
        super();

        this.id = 'secao-cupons';
        this.style.display = 'none';

        this.containerCupons = document.createElement('div');
        this.append(this.containerCupons);

        this.atualizar(cupons);
    }

    atualizar(cupons) {

        if (cupons && Array.isArray(cupons)){
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
        }

    }

}

customElements.define('secao-cupom', SecaoCupom, { extends: 'section' });