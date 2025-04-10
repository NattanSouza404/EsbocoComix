import { atualizarCliente } from "/js/api/apiCliente.js";
import ModalTransacoes from "./modalTransacoes.js";

export default class TabelaClientes extends HTMLTableElement {
    
    constructor(){
        super();

        this.modalTransacoes = new ModalTransacoes();

        this.colunas = [
            'Nome', "Gênero", "Data de Nascimento", "CPF", "E-mail", "Ranking", "Status", "Operações"
        ];
        this.atributosTabelaCliente = [
            'nome', 'genero', "dataNascimento", 'cpf', 'email', 'ranking', 'isAtivo'
        ];

        this.id = "tabela-clientes";
        
        this.tBody = document.createElement('tbody');
        this.append(this.tBody);
    }

    initCabecalho(){
        let tr = document.createElement('tr');
        this.colunas.forEach(c => {
            let th = document.createElement('th');
            th.textContent = c;
            tr.append(th);
        })
        return tr;
    }

    async atualizarTabelaClientes(clientes){
        this.tBody.textContent = '';
        this.tBody.append(this.initCabecalho());

        if (clientes === null || clientes === undefined){
            return;
        }

        clientes.forEach(c => {

            let tableRow = document.createElement('tr');

            Object.entries(c).forEach(
                ([chave, valor]) => {
                    if (!this.atributosTabelaCliente.includes(chave)){
                        return;
                    }

                    let td = document.createElement('td');
                    tableRow.append(td);

                    if (chave === 'isAtivo'){
                        td.textContent = (valor === true) ? 'Ativo' : "Inativo";
                        return;
                    }

                    td.textContent = valor; 
                }
            );
        
            let td = document.createElement('td');
        
            let btn = document.createElement('button');
            btn.textContent = "Consultar Transações";
            btn.type = "button";
            btn.onclick = () => this.modalTransacoes.show();
            td.append(btn);
        
            btn = document.createElement('button');
            btn.textContent = "Editar";
            btn.type = "button";
            btn.onclick = () => this.editarCliente(c);
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
        });
        
    }

    editarCliente(c){
       localStorage.setItem('idcliente', c.id);
       window.location.replace('/conta');
    }
}

customElements.define('tabela-clientes', TabelaClientes, { extends: 'table'}); 
