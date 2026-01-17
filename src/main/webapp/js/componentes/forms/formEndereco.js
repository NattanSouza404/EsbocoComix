import { TIPOS_LOGRADOURO, TIPOS_RESIDENCIAL } from "../../dados.js";

export class FormularioEndereco extends HTMLFormElement {
    constructor(){
        super();

        this.className = 'endereco';

        this.innerHTML = `
            <div class="numeracao">
                <p class="numeroTitulo">1</p>
                <p>Endereço</p>
            </div>

            <div class="dados-endereco">
                <label>
                    Frase curta para identificação
                    <input name="fraseCurta" placeholder="Casa"></input>
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
                    <input name="numero" placeholder="34"></input>
                </label>
                
                <label>
                    Logradouro
                    <input name="logradouro" placeholder="Flores"></input>
                </label>
                
                <label>
                    Tipo de Logradouro
                    <select name="tipoLogradouro"></select>
                </label>

                <label>
                    CEP
                    <input name="cep" placeholder="00000-000" minlength="8" maxlength="8"></input>
                </label>  

                <label>
                    Bairro
                    <input name="bairro" placeholder="Vila Celestina"></bairro>
                </label>
                
                <label>
                    Cidade
                    <input name="cidade" placeholder="Embu"></input>
                </label>

                <label>
                    Estado
                    <input name="estado" placeholder="São Paulo"></input>
                </label>
                
                <label>
                    País
                    <input name="pais" placeholder="Brasil"></input>
                </label>
                
                <label>
                    Observações
                    <input name="observacoes" placeholder="Perto do prédio..."></input>
                </label>
                
            </div>
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
    }

    atualizar(endereco){
        this.endereco = endereco;

        Object.entries(this.endereco).forEach(
            ([chave, valor]) => {
                let elemento = this.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                elemento.value = valor;
            }
        );
    }
}

customElements.define('form-dados-endereco', FormularioEndereco, { extends: 'form'});