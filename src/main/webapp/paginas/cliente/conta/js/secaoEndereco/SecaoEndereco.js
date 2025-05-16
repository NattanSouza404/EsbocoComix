import { CartaoEndereco } from "./CartaoEndereco.js";
import { ModalAdicionarEndereco } from "./modalAdicionarEndereco.js";
import { ModalAlterarEndereco } from "./modalAlterarEndereco.js";

export class SecaoEndereco extends HTMLElement {
    constructor(enderecos) {
        super();

        this.id = 'secao-endereco';
        this.style.display = 'none';

        this.modalAdicionarEndereco = new ModalAdicionarEndereco();
        this.modalAlterarEndereco = new ModalAlterarEndereco();

        this.containerEnderecos = document.createElement('div');
        this.append(this.containerEnderecos);

        this.insertAdjacentHTML('beforeend', `
            <div>
                <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                data-bs-target="#modal-adicionar-endereco">
                    Adicionar Endereço
                </button>
            </div>
        `);

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

customElements.define('secao-endereco', SecaoEndereco, { extends: 'section' });