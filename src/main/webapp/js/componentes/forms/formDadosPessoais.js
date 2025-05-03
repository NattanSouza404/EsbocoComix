import { GENEROS, TIPOS_TELEFONE } from "../../dados.js";
import { criarElemento, criarElementoInput, formatarDataParaInput } from "../../script.js";

export class FormularioDadosPessoais extends HTMLFormElement {
    constructor(){
        super();

        this.id = 'cadastrar-dados-pessoais';

        this.innerHTML = `
            <div class="header-dados-cadastro">
                <p>Dados Pessoais</p>
            </div>

            <div class="dados-formulario">
                <label>
                    Nome
                    <input name="nome" placeholder="Seu nome"></input>
                </label>

                <label>
                    Gênero
                    <select name="genero"></select>
                </label>

                <label>
                    Data de Nascimento
                    <input name="dataNascimento" type="date"></input>
                </label>

                <label>
                    CPF
                    <input name="cpf" type="date" placeholder="111.111.111-11"></input>
                </label>

                <label>
                    E-mail
                    <input name="email" type="email" placeholder="seuemail@email.com"></input>
                </label>
            </div>

            <div class="header-dados-cadastro">
                <p>Telefone</p>
            </div>

            <div class="dados-formulario">
            
                <label>
                    Tipo de Telefone
                    <select name="tipoTelefone"></select>
                </label>

                <label>
                    DDD
                    <input name="ddd" placeholder="11"></input>
                </label>

                <label>
                    Número do Telefone
                    <input name="numero" placeholder="11111-1111"></input>
                </label>

            </div>
        `;
        
        let select = this.querySelector('[name="genero"]');
        GENEROS.forEach(({ nome, valor }) => {
            select.insertAdjacentHTML('beforeend',
                `<option value="${valor}">${nome}</option>`
            );
        });

        select = this.querySelector('[name="tipoTelefone"]');
        TIPOS_TELEFONE.forEach(({ nome, valor }) => {
            select.insertAdjacentHTML('beforeend',
                `<option value="${valor}">${nome}</option>`
            );
        });
    }

    atualizar(cliente){
        this.cliente = cliente;
        Object.entries(cliente).forEach(
            ([chave, valor]) => {
                let elemento = this.querySelector(`[name="${chave}"]`);

                if (!elemento){
                    return;
                }

                if (chave === 'dataNascimento'){
                    elemento.value = formatarDataParaInput(valor);
                    return;
                }

                elemento.value = valor;
            }
        );

        Object.entries(cliente.telefone).forEach(
            ([chave, valor]) => {
                let elemento = document.querySelector(`[name="${chave}"]`);

                if (elemento){
                    elemento.value = valor;
                }
            }
        );
    }
}

customElements.define('form-dados-pessoais', FormularioDadosPessoais, { extends: 'form'});