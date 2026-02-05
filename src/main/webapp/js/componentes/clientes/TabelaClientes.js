import { formatarData, mascararCpf } from "../../script.js";

export default class TabelaClientes extends HTMLTableElement {
    
    constructor(
        modalTransacoes,
        modalCupomPromocional,
        confirmarInativarCliente
    ) {
        super();

        this.confirmarInativarCliente = confirmarInativarCliente;
        this.id = 'tabela-clientes';

        this.innerHTML = /* html */ ` 
            <thead>
                <th>#</th>
                <th>Nome</th>
                <th>Gênero</th>
                <th>Data de Nascimento</th>
                <th>CPF</th><th>E-mail</th>
                <th>Ranking</th>
                <th>Status</th>
                <th>Operações</th>
            </thead>
            <tbody></tbody>
        `;

        this.tBody = this.querySelector('tbody');

        this.modalTransacoes = modalTransacoes;
        this.modalCupomPromocional = modalCupomPromocional;
    }

    async atualizarTabelaClientes(clientes){
        this.tBody.textContent = '';

        for (let i = 0; i < clientes.length; i++){
            this.tBody.append(
                LinhaTabelaCliente(
                    i+1,
                    clientes[i],
                    this.modalTransacoes,
                    this.modalCupomPromocional,
                    this.confirmarInativarCliente
                )
            ); 
        }        
    }

}

function LinhaTabelaCliente(
    index,
    cliente,
    modalTransacoes,
    modalCupomPromocional,
    confirmarInativarCliente
){
    const tr = document.createElement('tr');

    tr.innerHTML = /* html */ `
        <td>${index}</td>
        <td>${cliente.nome}</td>
        <td>${cliente.genero}</td>
        <td>${formatarData(cliente.dataNascimento)}</td>
        <td>${mascararCpf(cliente.cpf)}</td>
        <td>${cliente.email}</td>
        <td>${cliente.ranking}</td>
        <td>${(cliente.isAtivo === true) ? 'Ativo' : "Inativo"}</td>

        <td>
            <button type="button" class="btn-transacoes">
                Consultar Transações
            </button>

            <a href="/conta?idcliente=${cliente.id}">
                <button type="button" class="btn-editar">
                    Editar
                </button>
            </a>

            <button type="button" class="btn-adicionar-cupom">
                Adicionar Cupom
            </button>

            <button type="button" class="btn-inativar">
                ${cliente.isAtivo === true ? 'Inativar' : 'Ativar'}
            </button>
        </td>
    `;

    /** @type {HTMLButtonElement} */
    (tr.querySelector('.btn-transacoes')).onclick = () => {
        modalTransacoes.show(cliente);
    };

    /** @type {HTMLButtonElement} */
    (tr.querySelector('.btn-adicionar-cupom')).onclick = () => {
        modalCupomPromocional.show(cliente);
    };

    /** @type {HTMLButtonElement} */
    (tr.querySelector('.btn-inativar')).onclick = async () => {
        confirmarInativarCliente(cliente);
    };

    return tr;
}

customElements.define('tabela-clientes', TabelaClientes, { extends: 'table' });