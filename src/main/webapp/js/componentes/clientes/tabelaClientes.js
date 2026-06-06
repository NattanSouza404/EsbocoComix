import { inativarCliente } from "../../api/cliente.api.js";
import ModalTransacoes from "../pedido/modalTransacoes.js";
import { formatarData } from "../../script.js";
import ModalCupomPromocional from "../cupom/modalCupomPromocional.js";
import { alertarErro } from "../../api/alertErro.js";
import { mascararCpf } from "../../script.js";

export default class TabelaClientes {
    
    constructor(){
        this.tBody = document.querySelector('#tabela-clientes tbody');

        this.modalTransacoes = new ModalTransacoes();
        this.modalCupomPromocional = new ModalCupomPromocional();
    }

    async atualizarTabelaClientes(clientes){
        this.tBody.textContent = '';

        let contador = 1;

        clientes.forEach(c => {

            let tableRow = document.createElement('tr');

            tableRow.innerHTML = `
                <td>${contador}</td>
                <td>${c.nome}</td>
                <td>${c.genero}</td>
                <td>${formatarData(c.dataNascimento)}</td>
                <td>${mascararCpf(c.cpf)}</td>
                <td>${c.email}</td>
                <td>${c.ranking}</td>
                <td>${(c.isAtivo === true) ? 'Ativo' : "Inativo"}</td>

                <td>
                    <button type="button" class="btn-transacoes">Consultar Transações</button>

                    <a href="/conta?idcliente=${c.id}">
                        <button type="button" class="btn-editar">Editar</button>
                    </a>

                    <button type="button" class="btn-adicionar-cupom">Adicionar Cupom<qbutton>

                    <button type="button" class="btn-inativar">${c.isAtivo === true ? 'Inativar' : 'Ativar'}</button>
                </td>
            `;

            /** @type {HTMLButtonElement} */
            (tableRow.querySelector('.btn-transacoes')).onclick = () => {
                this.modalTransacoes.show(c);
            };

            /** @type {HTMLButtonElement} */
            (tableRow.querySelector('.btn-adicionar-cupom')).onclick = () => {
                this.modalCupomPromocional.show(c);
            };

            /** @type {HTMLButtonElement} */
            (tableRow.querySelector('.btn-inativar')).onclick = async () => {
                this.confirmarInativarCliente(c);
            };
        
            this.tBody.append(tableRow); 

            contador++;
        });
        
    }

    async confirmarInativarCliente(cliente){
        const confirmacaoUsuario = confirm("Deseja mesmo ativar/inativar cliente?"); 

        if (!confirmacaoUsuario){
            return;
        }

        try {
            await inativarCliente(cliente);
            alert('Atualizado com sucesso!');

            window.location.reload();
        } catch (error){
            alertarErro(error);
        }
        
    }

}
