import { TIPOS_LOGRADOURO, TIPOS_RESIDENCIAL } from "../../dados.js";

export class FormEndereco extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'endereco';

        this.innerHTML = /* html */`
            <div class="numeracao">
                <p class="numeroTitulo">1</p>
                <p>Endereço</p>
            </div>

            <div class="dados-endereco">
                <label>
                    Frase curta para identificação
                    <input name="fraseCurta" placeholder="Casa">
                </label>
                
                <label>
                    É um endereço residencial?

                    <select name="isResidencial">
                        <option value="true">Sim</option>
                        <option selected value="false">Não</option>
                    </select>
                </label>
                
                <label>
                    É um endereço de cobrança?

                    <select name="isCobranca">
                        <option value="true">Sim</option>
                        <option selected value="false">Não</option>
                    </select>
                </label>  

                <label>
                    É um endereço de entrega?

                    <select name="isEntrega">
                        <option value="true">Sim</option>
                        <option selected value="false">Não</option>
                    </select>
                </label>
                
                <label>
                    Tipo Residencial
                    <select name="tipoResidencial"></select>
                </label>
                
                <label>
                    Nº Endereço
                    <input name="numero" placeholder="34">
                </label>
                
                <label>
                    Logradouro
                    <input name="logradouro" placeholder="Flores">
                </label>
                
                <label>
                    Tipo de Logradouro
                    <select name="tipoLogradouro"></select>
                </label>

                <label>
                    CEP
                    <input
                        name="cep"
                        placeholder="00000-000"
                        minlength="8"
                        maxlength="8"
                    >
                </label>  

                <label>
                    Bairro
                    <input name="bairro" placeholder="Vila Celestina">
                </label>
                
                <label>
                    Cidade
                    <input name="cidade" placeholder="Embu">
                </label>

                <label>
                    Estado
                    <input name="estado" placeholder="São Paulo">
                </label>
                
                <label>
                    País
                    <input name="pais" placeholder="Brasil">
                </label>
                
                <label>
                    Observações
                    <input name="observacoes" placeholder="Perto do prédio...">
                </label>
            </div>

            <button
                class="btn-remover"
                type="button"
                style="display: none;"
            >
                Remover
            </button>
        `;

        this.numeroTitulo = this.getElementsByClassName('numeroTitulo')[0];

        let select = this.querySelector('select[name="tipoResidencial"]');
        TIPOS_RESIDENCIAL.forEach(({ nome, valor }) => {
            select.insertAdjacentHTML('beforeend',
                `<option value="${valor}">${nome}</option>`
            );
        });

        select = this.querySelector('select[name="tipoLogradouro"]');
        TIPOS_LOGRADOURO.forEach(({ nome, valor }) => {
            select.insertAdjacentHTML('beforeend',
                `<option value="${valor}">${nome}</option>`
            );
        });
    }

    setNumeroTitulo(numero){
        this.numeroTitulo.textContent = numero;
        return this;
    }

    tornarObrigatorio(){    
        /** @type {HTMLSelectElement} */
        (this.querySelector('[name = "isResidencial"]'))
            .value = 'true';

        /** @type {HTMLOptionElement} */
        (this.querySelector('[name = "isResidencial"]').querySelector('[value="false"]'))
            .disabled = true;
        return this;
    }

    habilitarBotaoRemover(){
        const btnRemover = /** @type {HTMLButtonElement} */
            (this.querySelector(".btn-remover"))

        btnRemover.onclick = () => {
             if (this.parentNode){
                this.parentElement.removeChild(this);
            }
        }

        btnRemover.style.display = "block";

        return this;
    }

    atualizar(endereco){
        this.endereco = endereco;

        Object.entries(this.endereco).forEach(
            ([chave, valor]) => {
                const elemento = /** @type {HTMLInputElement} */
                    (this.querySelector(`[name="${chave}"]`));

                if (!elemento){
                    return;
                }

                elemento.value = valor;
            }
        );
    }
}

customElements.define('form-dados-endereco', FormEndereco, { extends: 'form'});