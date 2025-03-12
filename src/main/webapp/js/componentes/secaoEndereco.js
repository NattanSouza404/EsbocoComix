import { criarElemento } from "../script.js";
import { FormularioEndereco } from "./forms.js";

export class SecaoFormsEndereco extends HTMLElement {
    constructor(){
        super();

        this.append(criarElemento("p", "Endereços"));

        this.container = document.createElement('div');
        this.container.id = 'container-enderecos';
        this.append(this.container);

        const footer = document.createElement('div');
        footer.id = 'footer-secao-endereco';
        this.append(footer);

        this.botaoAddEndereco = criarElemento('button', '+ Novo Endereço');
        this.botaoAddEndereco.type = 'button';
        this.botaoAddEndereco.onclick = () => this.adicionarEndereco();
        footer.append(this.botaoAddEndereco);

        const aviso = criarElemento('p', 'Observações: Deve haver ao menos 1 endereço de'+
                        'residência, um de entrega e um de cobrança');
        aviso.className = 'aviso';

        footer.append(aviso);

        this.container.append(this.montarEnderecoObrigatorio());
    }

    adicionarEndereco(){
        const nEnderecosNaTela = this.querySelectorAll('.endereco').length + 1;
        const form = new FormularioEndereco();
        form.setNumeroTitulo(nEnderecosNaTela);
        this.container.append(form);
        return form;
    }

    montarEnderecoObrigatorio(){
        const form = new FormularioEndereco();
        const button = form.querySelector('button');
        form.removeChild(button);
    
        const isResidencial = form.querySelector('[name = "isResidencial"]');
        isResidencial.value = 'true';
        const opcaoNao = isResidencial.querySelector('[value = "false"]');
        opcaoNao.disabled = true;
    
        const fraseCurta = form.querySelector('[name = "fraseCurta"]');
        fraseCurta.placeholder = "Casa";
    
        return form;
    }
}

customElements.define('secao-form-endereco', SecaoFormsEndereco, {extends:"section"});