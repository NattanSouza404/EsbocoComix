import { atualizarCliente } from "/js/api/apiCliente.js";
import ModalTransacoes from "./modalTransacoes.js";
import { formatarData } from "/js/script.js";
import ModalCupomPromocional from "./modalCupomPromocional.js";

export default class TabelaClientes extends HTMLTableElement {
    
    constructor(){
        super();

        this.modalTransacoes = new ModalTransacoes();
        this.modalCupomPromocional = new ModalCupomPromocional();

        this.id = "tabela-clientes";
        
        this.tBody = document.createElement('tbody');
        this.append(this.tBody);
    }

    initCabecalho(){
        let tr = document.createElement('tr');

        tr.innerHTML = `
            <th>#</th>
            <th>Nome</th>
            <th>Gênero</th>
            <th>Data de Nascimento</th>
            <th>CPF</th><th>E-mail</th>
            <th>Ranking</th>
            <th>Status</th>
            <th>Operações</th>
        `;

        return tr;
    }

    async atualizarTabelaClientes(clientes){
        this.tBody.textContent = '';
        this.tBody.append(this.initCabecalho());

        if (clientes === null || clientes === undefined){
            return;
        }

        let contador = 1;

        clientes.forEach(c => {

            let tableRow = document.createElement('tr');

            tableRow.innerHTML = `
                <td>${contador}</td>
                <td>${c.nome}</td>
                <td>${c.genero}</td>
                <td>${formatarData(c.dataNascimento)}</td>
                <td>${c.cpf}</td>
                <td>${c.email}</td>
                <td>${c.ranking}</td>
                <td>${(c.isAtivo === true) ? 'Ativo' : "Inativo"}</td>
            `;
        
            let td = document.createElement('td');
        
            let btn = document.createElement('button');
            btn.textContent = "Consultar Transações";
            btn.type = "button";
            btn.onclick = () => this.modalTransacoes.show(c);
            td.append(btn);
        
            btn = document.createElement('button');
            btn.textContent = "Editar";
            btn.type = "button";
            btn.onclick = () => this.editarCliente(c);
            td.append(btn);

            btn = document.createElement('button');
            btn.textContent = "Adicionar Cupom";
            btn.type = "button";
            btn.onclick = () => this.modalCupomPromocional.show(c);
            td.append(btn);
        
            btn = document.createElement('button');
            btn.type = "button";
            btn.textContent = "Inativar";
            btn.onclick = () => {
                c.isAtivo = (c.isAtivo === true) ? false : true;
                atualizarCliente(c, "atualizarstatuscadastro");
            };
            td.append(btn);
        
            tableRow.append(td);
        
            this.tBody.append(tableRow); 

            contador++;
        });
        
    }

    editarCliente(c){
       localStorage.setItem('idcliente', c.id);
       window.location.replace('/conta');
    }
}

customElements.define('tabela-clientes', TabelaClientes, { extends: 'table'}); 
