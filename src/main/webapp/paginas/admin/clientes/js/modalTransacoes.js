import { formatarDateTime, formatarPreco } from "/js/script.js";
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

        if (Array.isArray(pedidos) && pedidos.length > 0){
            this.tbody.innerHTML = '';

            this.mostrarTabela();
            
            pedidos.forEach(pedido => {
                this.tbody.insertAdjacentHTML('beforeend', `
                    <tr>
                        <td>${formatarDateTime(pedido.data)}</td>
                        <td>${formatarPreco(pedido.valorTotal)}</td>
                        <td>${formatarPreco(pedido.valorFrete)}</td>
                        <td>${pedido.status}</td>
                    </tr>
                    
                `);
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
                <th>Valor frete</th>
                <th>Status</th>
            </thead>
            <tbody></tbody>
        </table>

        <p class="aviso text-center">Nenhum pedido.</p>
    `;

    return conteudoModal;
}