import { CartaoEndereco } from "./CartaoEndereco.js";
import { ModalAdicionarEndereco } from "./modalAdicionarEndereco.js";
import { ModalAlterarEndereco } from "./modalAlterarEndereco.js";

export class SecaoEndereco {
    constructor(enderecos) {
        this.modalAdicionarEndereco = new ModalAdicionarEndereco();
        this.modalAlterarEndereco = new ModalAlterarEndereco();

        this.elementoHTML = document.getElementById('secao-endereco');
        this.containerEnderecos = this.elementoHTML.querySelector('.container');

        this.atualizar(enderecos);
    }

    atualizar(enderecos) {

        if (enderecos && Array.isArray(enderecos)){
            this.containerEnderecos.innerHTML = "";

            let contador = 1;
    
            enderecos.forEach(
                (e) => {
                    const cartao = new CartaoEndereco(e, this.modalAlterarEndereco);
                    cartao.titulo.textContent = contador + "º Endereço";
                    this.containerEnderecos.append(cartao);
                    contador++;
                }
            )
        }

    }

}