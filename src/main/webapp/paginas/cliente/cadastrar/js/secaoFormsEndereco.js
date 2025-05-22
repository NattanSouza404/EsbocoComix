import { FormularioEndereco } from "/js/componentes/forms/formEndereco.js";

export class SecaoFormsEndereco extends HTMLElement {
    constructor(){
        super();

        this.insertAdjacentHTML('beforeend', `
            <p>Endereços</p> 

            <div id="container-enderecos"></div>

            <div class="footer-secao-endereco">
                <button type="button" class="btn-adicionar-endereco">+ Novo Endereço</button>

                <p class="aviso">
                    Observações: Deve haver ao menos 1 endereço de
                    residência, um de entrega e um de cobrança
                </p>
            </div>
        `);

        this.botaoAddEndereco = this.querySelector('.btn-adicionar-endereco');
        this.botaoAddEndereco.onclick = () => this.adicionarEndereco();

        this.container = this.querySelector("#container-enderecos");
        this.container.append(this.montarEnderecoObrigatorio());
    }

    adicionarEndereco(){
        const nEnderecosNaTela = this.querySelectorAll('.endereco').length + 1;
        const form = new FormularioEndereco();
        
        form.setNumeroTitulo(nEnderecosNaTela);

        form.insertAdjacentHTML('beforeend', `
            <button class="btn-remover" type="button">Remover</button>
        `);

        form.querySelector('.btn-remover').onclick = () => {
            if (form.parentNode){
                form.parentElement.removeChild(form);
            }
        };

        this.container.append(form);
        return form;
    }

    montarEnderecoObrigatorio(){
        const form = new FormularioEndereco();
    
        form.querySelector('[name = "isResidencial"]')
            .value = 'true';

        form.querySelector('[name = "isResidencial"]').querySelector('[value="false"]')
            .disabled = true;
    
        return form;
    }
}

customElements.define('secao-form-endereco', SecaoFormsEndereco, {extends:"section"});