import { formatarData } from "../../../../script.js"

export const CartaoDadosPessoais = (cliente) => {
    const div = document.createElement('div');

    div.innerHTML = /* html */ `
        <p>Nome: ${cliente.nome}</p>
        <p>Data Nascimento: ${formatarData(cliente.dataNascimento)}</p>
        <p>CPF: ${cliente.cpf}</p>
        <p>E-mail: ${cliente.email}</p>
        <p>Gênero: ${cliente.genero}</p>
        <p>Ranking: ${cliente.ranking}</p>
        <hr>
        <p>
            Telefone ${cliente.telefone.tipo} : ${cliente.telefone.ddd} ${cliente.telefone.numero} 
        </p>
    `;

    return div;
}