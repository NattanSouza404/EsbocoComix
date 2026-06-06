import { formatarDateTime, formatarPreco } from "../../script.js";
import { Modal } from "@componentes/common/modal.js";

export default class ModalTransacoes extends Modal {
    constructor(getPedidos){
        const conteudoModal = ConteudoModalTransacoes();
        super('modal-consultar-transacoes', "Transações", conteudoModal);
        
        this.conteudoModal = conteudoModal;
        this.tabela = conteudoModal.querySelector('table');
        this.tbody = conteudoModal.querySelector('tbody');

        this.aviso = /** @type {HTMLParagraphElement} */
            conteudoModal.querySelector('.aviso');

        this.esconderTabela();

        this.getPedidos = getPedidos;
    }

    async show(cliente){
        const pedidos = await this.getPedidos(cliente);

        super.mudarTitulo(`Transações de ${cliente.nome}`);

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

        /** @type {HTMLParagraphElement} */
        (this.aviso).style.display = 'none';
    }

    esconderTabela(){
        this.tabela.style.display = 'none';

        /** @type {HTMLParagraphElement} */
        (this.aviso).style.display = 'block';
    }
}

function ConteudoModalTransacoes(){
    const conteudoModal = document.createElement('div');
    conteudoModal.id = 'consultar-transacoes';

    conteudoModal.innerHTML = /* html */ `
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