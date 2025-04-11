import { CartaoEndereco } from "./CartaoEndereco.js";
import { ModalAlterarEndereco } from "./modalAlterarEndereco.js";

export class SecaoEndereco extends HTMLElement {
    constructor(enderecos) {
        super();

        this.id = 'secao-endereco';
        this.style.display = 'none';

        this.modalAlterarEndereco = new ModalAlterarEndereco();

        this.containerEnderecos = document.createElement('div');
        this.append(this.containerEnderecos);

        const div = document.createElement('div');

        div.innerHTML = `
            <button type="button" class="btn btn-primary btn-sm btn-primary btn-lg" data-bs-toggle="modal"
                data-bs-target="#modal-alterar-endereco">
                Editar Endereço
            </button>
        `;

        this.append(div);

        this.atualizar(enderecos);
    }

    atualizar(enderecos) {

        if (enderecos){
            this.containerEnderecos.innerHTML = "";

            let contador = 1;
    
            enderecos.forEach(
                (e) => {
                    const endereco = new CartaoEndereco(e);
                    endereco.titulo.textContent = contador + "º Endereço";
                    this.containerEnderecos.append(endereco);
                    contador++;
                }
            )
    
            this.modalAlterarEndereco.atualizar(enderecos)
        }

    }

}

customElements.define('secao-endereco', SecaoEndereco, { extends: 'section' });