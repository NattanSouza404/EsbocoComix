import { FormularioDadosPessoais } from "/js/componentes/forms/formDadosPessoais.js";

export class FormularioCadastrarDadosPessoais extends FormularioDadosPessoais {
    constructor(){
        super();

        this.insertAdjacentHTML('beforeend', `
            <div class="header-dados-cadastro">
                <p>Senha</p>
            </div>

            <div class="dados-formulario">
                <label>
                    Senha
                    <input name="senhaNova" type="password"></input> 
                </label>

                <label>
                    Confirme a senha
                    <input name="senhaConfirmacao" type="password"></input> 
                </label>
            </div>
        `);
    }

}

customElements.define('form-cadastrar-dados-pessoais', FormularioCadastrarDadosPessoais, {extends: "form"});