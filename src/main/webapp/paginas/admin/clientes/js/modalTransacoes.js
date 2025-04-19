import { retornarPedidos } from "/js/api/apiPedido.js";
import { Modal } from "/js/componentes/modal.js";

export default class ModalTransacoes extends Modal {
    constructor(){
        const conteudoModal = ConteudoModalTransacoes();
        super('modal-consultar-transacoes', "Transações", conteudoModal);
        
        this.conteudoModal = conteudoModal;
        this.tabela = conteudoModal.querySelector('table');
        this.tbody = conteudoModal.querySelector('tbody');
        this.aviso = conteudoModal.querySelector('.aviso');

        this.esconderTabela();
    }

    async show(cliente){
        this.cliente = cliente;

        super.mudarTitulo(`Transações de ${this.cliente.nome}`);
        const pedidos = await retornarPedidos(this.cliente.id);

        if (Array.isArray(pedidos)){
            this.tbody.innerHTML = '';

            this.mostrarTabela();
            
            pedidos.forEach(pedido => {
                const tr = document.createElement('tr');

                tr.innerHTML = `
                    <td>${pedido.data}</td>
                    <td>????</td>
                    <td>${pedido.status}</td>
                `;

                this.tbody.append(tr);
            });

            super.show();

            return;
        }

        this.esconderTabela();

        super.show();
    }

    mostrarTabela(){
        this.tabela.style.display = 'block';
        this.aviso.style.display = 'none';
    }

    esconderTabela(){
        this.tabela.style.display = 'none';
        this.aviso.style.display = 'block';
    }
}

function ConteudoModalTransacoes(){
    const conteudoModal = document.createElement('div');
    conteudoModal.id = 'consultar-transacoes';

    conteudoModal.innerHTML = `
        <table>
            <thead>
                <th>Data e hora</th>
                <th>Valor Transação</th>
                <th>Status</th>
            </thead>
            <tbody></tbody>
        </table>

        <p class="aviso text-center">Nenhum pedido.</p>
    `;

    return conteudoModal;
}