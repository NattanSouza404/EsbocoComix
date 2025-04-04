import { retornarAllClientes } from "../api.js";
import { montarClientePorForm } from "../script.js";
import TabelaClientes from "./tabelaClientes.js";
import { Modal } from "../componentes/modal.js";

const tabelaClientes = new TabelaClientes();
document.body.append(tabelaClientes);

async function atualizarTabelaClientes(){
    const formPesquisa = document.getElementById('filtro-clientes');
    const filtro = montarClientePorForm(formPesquisa);
    const clientes  = await retornarAllClientes(filtro);

    tabelaClientes.atualizarTabelaClientes(clientes);
}

window.atualizarTabelaClientes = () => atualizarTabelaClientes();

const clientes = await retornarAllClientes();
await tabelaClientes.atualizarTabelaClientes(clientes);