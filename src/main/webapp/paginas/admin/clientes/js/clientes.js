import { retornarAllClientes } from "/js/api/apiCliente.js";
import { montarClientePorForm } from "/js/script.js";
import TabelaClientes from "/paginas/admin/clientes/js/tabelaClientes.js";

const tabelaClientes = new TabelaClientes();

window.atualizarTabelaClientes = () => atualizarTabelaClientes();

const clientes = await retornarAllClientes();
await tabelaClientes.atualizarTabelaClientes(clientes);

async function atualizarTabelaClientes(){
    const formPesquisa = document.getElementById('filtro-clientes');
    const filtro = montarClientePorForm(formPesquisa);
    const clientes  = await retornarAllClientes(filtro);

    tabelaClientes.atualizarTabelaClientes(clientes);
}