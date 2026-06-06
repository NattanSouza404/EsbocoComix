import { retornarAllClientes } from "@api/cliente.api.js";
import { montarClientePorForm } from "../../script.js";
import TabelaClientes from "@componentes/clientes/tabelaClientes.js";
import { alertarErro } from "@api/alertErro.js";

/**
 * @typedef {Window & { atualizarTabelaClientes?: any }} WindowComFuncao
 */

const win = /** @type {WindowComFuncao} */ (window);

export async function initPagina() {

    const tabelaClientes = new TabelaClientes();

    win.atualizarTabelaClientes = () => atualizarTabelaClientes();

    try {
        const clientes = await retornarAllClientes();
        await tabelaClientes.atualizarTabelaClientes(clientes);
    } catch (error){
        alertarErro(error);
    }

    async function atualizarTabelaClientes(){
        const formPesquisa = document.getElementById('filtro-clientes');
        const filtro = montarClientePorForm(formPesquisa);
        
        try {
            const clientes  = await retornarAllClientes(filtro);
            tabelaClientes.atualizarTabelaClientes(clientes);
        } catch (error){
            alertarErro(error);
        }

    }

}