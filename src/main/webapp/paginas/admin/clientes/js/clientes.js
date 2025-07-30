import { retornarAllClientes } from "/js/api/apiCliente.js";
import { montarClientePorForm } from "/js/script.js";
import TabelaClientes from "./tabelaClientes.js";
import { alertarErro } from "../../../../js/api/alertErro.js";

const tabelaClientes = new TabelaClientes();

window.atualizarTabelaClientes = () => atualizarTabelaClientes();

try {
    const clientes = await retornarAllClientes();
    await tabelaClientes.atualizarTabelaClientes(clientes);
} catch (error){
    alertarErro(clientes);
}

async function atualizarTabelaClientes(){
    const formPesquisa = document.getElementById('filtro-clientes');
    const filtro = montarClientePorForm(formPesquisa);
    
    try {
        const clientes  = await retornarAllClientes(filtro);
        tabelaClientes.atualizarTabelaClientes(clientes);
    } catch (error){
        alertarErro(clientes);
    }

}