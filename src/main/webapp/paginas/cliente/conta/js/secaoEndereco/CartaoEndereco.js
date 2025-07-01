import { deletarEndereco } from "../../../../../js/api/apiEndereco.js";

export class CartaoEndereco extends HTMLDivElement {
    constructor(endereco, modalAlterarEndereco){

        super();
        this.modalAlterarEndereco = modalAlterarEndereco;

        this.className = 'endereco-conta';

        this.insertAdjacentHTML('beforeend', `
            <h3>Endereço</h3>
        `);

        this.titulo = this.querySelector('h3');

        this.atualizar(endereco);

        this.endereco = endereco;
    }

    atualizar(endereco){
        let msgTipoEndereco = "";

        if (endereco.isResidencial === true){
            msgTipoEndereco += "[Residencial] ";
        }

        if (endereco.isEntrega === true){
            msgTipoEndereco += "[Entrega] ";
        }

        if (endereco.isCobranca === true){
            msgTipoEndereco += "[Cobranca] ";
        }

        const observacoes = endereco.observacoes !== null ? endereco.observacoes : "N/A";

        this.insertAdjacentHTML('beforeend', `
            <p>Frase Curta: ${endereco.fraseCurta}</p>
            <p>Tipo do Endereço: ${msgTipoEndereco}</p>
            <p>Tipo de Residência: ${endereco.tipoResidencial}</p>
            <p>Nº: ${endereco.numero}</p>
            <p>Tipo Logradouro: ${endereco.tipoLogradouro}</p>
            <p>CEP: ${endereco.cep}</p>
            <p>Bairro: ${endereco.bairro}</p>
            <p>Cidade: ${endereco.cidade}</p>
            <p>Estado: ${endereco.estado}</p>
            <p>País: ${endereco.pais}</p>
            <p>Observações: ${observacoes}</p>

            <button class="btn-atualizar">Atualizar</button>
            <button class="btn-deletar">Remover</button> 
        `);

        const btnAtualizar = this.querySelector('.btn-atualizar');
        btnAtualizar.onclick = () => {
            this.modalAlterarEndereco.show(this.endereco);
        };

        const btnDeletar = this.querySelector('.btn-deletar');
        btnDeletar.onclick = () => {
            deletarEndereco(endereco);
        };
    }
}

customElements.define('dados-endereco-conta', CartaoEndereco, {extends:"div"});