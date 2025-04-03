import { Modal } from "../componentes/modal.js";

export const modalTransacoes = new Modal('modal-consultar-transacoes', "Transações", 
    `
        <div id="consultar-transacoes">
            <h3>Transações de Maurício da Silva</h3>
  
            <table>
            <tbody><tr>
                <th>ID</th>
                <th>Data e hora</th>
                <th>Valor Transação</th>
                <th>Status</th>
            </tr>
            <tr>
                <td>876</td>
                <td>01/03/2024 11:00:00</td>
                <td>R$ 80,00</td>
                <td>Entregue</td>
            </tr>
            <tr>
                <td>879</td>
                <td>01/07/2024 12:00:00</td>
                <td>R$ 40,00</td>
                <td>Entregue</td>
            </tr>
            </tbody></table>

        </div>
      `)