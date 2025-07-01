import { formatarData } from "/js/script.js"
export class CartaoDadosPessoais extends HTMLDivElement {
    constructor() {
        super();
    }

    async atualizar(cliente) {
        this.innerHTML = `
            <p>Nome: ${cliente.nome}</p>
            <p>Data Nascimento: ${formatarData(cliente.dataNascimento)}</p>
            <p>CPF: ${cliente.cpf}</p>
            <p>E-mail: ${cliente.email}</p>
            <p>Gênero: ${cliente.genero}</p>
            <p>Ranking: ${cliente.ranking}</p>
            <hr>
            <p>Telefone ${cliente.telefone.tipo} : ${cliente.telefone.ddd} ${cliente.telefone.numero} </p>
        `;
    }
}

customElements.define('dados-pessoais-conta', CartaoDadosPessoais, {extends:"div"});