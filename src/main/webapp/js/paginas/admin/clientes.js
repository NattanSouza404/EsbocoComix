import { alertarErro } from "@api/alertErro.js";
import { inativarCliente, retornarAllClientes } from "@api/cliente.api.js";
import { retornarPedidos } from "@api/pedido.api.js";
import TabelaClientes from "@componentes/clientes/TabelaClientes.js";
import ModalCupomPromocional from "@componentes/cupom/ModalCupomPromocional.js";
import ModalTransacoes from "@componentes/pedido/ModalTransacoes.js";
import { montarClientePorForm } from "../../script.js";

const getElementos = () => {
    return {
        tabelaClientes: document.getElementById('container-tabela-clientes'),
        filtroClientes: document.getElementById('filtro-clientes'),
        botaoPesquisarClientes: document.getElementById('botao-pesquisar-clientes')
    };
}

export async function initPagina() {
    try {
        const el = getElementos();

        const clientes = await retornarAllClientes();

        const modalTransacoes = new ModalTransacoes(getPedidos);
        const modalCupom = new ModalCupomPromocional(inserirCupom);

        const tabelaClientes = new TabelaClientes(
            modalTransacoes,
            modalCupom,
            confirmarInativarCliente
        );

        el.tabelaClientes.appendChild(
            tabelaClientes
        );

        await tabelaClientes.atualizarTabelaClientes(clientes);

        el.botaoPesquisarClientes.onclick = () => {
            pesquisarClientes(tabelaClientes);
        };
    } catch (error){
        alertarErro(error);
    }
}

async function getPedidos(cliente){
    try {
        return await retornarPedidos(cliente.id);
    } catch (error){
        alertarErro(error);
    }
}

async function inserirCupom(cupom){
    try {
        await inserirCupom(cupom);
        alert('Cadastrado com sucesso');    
    } catch (error){
        alertarErro(error);
    }
}
        
async function pesquisarClientes(tabelaClientes){
    try {
        const formPesquisa = getElementos().filtroClientes;
        const filtro = montarClientePorForm(formPesquisa);
        const clientes = await retornarAllClientes(filtro);
        tabelaClientes.atualizarTabelaClientes(clientes);
    } catch (error){
        alertarErro(error);
    }
}

async function confirmarInativarCliente(cliente){
    try {
        const confirmacaoUsuario = confirm("Deseja mesmo ativar/inativar cliente?"); 

        if (!confirmacaoUsuario){
            return;
        }
    
        await inativarCliente(cliente);
        alert('Atualizado com sucesso!');

        window.location.reload();
    } catch (error){
        alertarErro(error);
    }
}