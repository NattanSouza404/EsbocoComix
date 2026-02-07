import { FormularioDadosPessoais } from "@componentes/forms/formDadosPessoais.js";

export class FormularioCadastrarDadosPessoais extends FormularioDadosPessoais {
    constructor(){
        super();

        this.id = "form-dados-pessoais";

        this.insertAdjacentHTML('beforeend', /* html */ `
            <div class="header-dados-cadastro">
                <p><strong>Senha</strong></p>
            </div>

            <div class="dados-formulario secao-senha">
                <div class="aviso-senha">
                    <p>
                        A senha cadastrada pelo usuário deve ser composta<br/>
                        de pelo menos 8 caracteres, ter letras maiúsculas e<br/>
                        minúsculas além de conter caracteres especiais.<br/>
                    </p>
                </div>

                <div>
                    <label>
                        Senha
                        <input name="senhaNova" type="password">
                    </label>

                    <label>
                        Confirme a senha
                        <input name="senhaConfirmacao" type="password">
                    </label>
                </div>
            </div>
        `);
    }

}

customElements.define('form-cadastrar-dados-pessoais', FormularioCadastrarDadosPessoais, {extends: "form"});