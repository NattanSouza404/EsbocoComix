export class CartaoDadosPessoais extends HTMLElement {
    constructor(cliente) {
        super();
        this.atualizar(cliente);
    }

    async atualizar(cliente) {
        this.innerHTML = `
            <p>Nome: ${cliente.nome}</p>
            <p>Data Nascimento: ${cliente.dataNascimento}</p>
            <p>CPF: ${cliente.cpf}</p>
            <p>E-mail: ${cliente.email}</p>
            <p>Gênero: ${cliente.genero}</p>
            <p>Ranking: ${cliente.ranking}</p>
            <hr>
            <p>Telefone ${cliente.telefone.tipo} : ${cliente.telefone.ddd} ${cliente.telefone.numero} </p>
        `;
    }
}

customElements.define('dados-pessoais-conta', CartaoDadosPessoais);