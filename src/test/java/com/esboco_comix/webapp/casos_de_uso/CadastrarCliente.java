package com.esboco_comix.webapp.casos_de_uso;

import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.paginas.admin.clientes.PaginaClientes;
import com.esboco_comix.webapp.paginas.cliente.cadastrar.PaginaCadastrar;
import com.esboco_comix.webapp.paginas.cliente.conta.ModaisConta;
import com.esboco_comix.webapp.paginas.cliente.conta.PaginaConta;
import com.esboco_comix.webapp.paginas.cliente.conta.SecoesConta;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CadastrarCliente {
    private final PaginaCadastrar paginaCadastrar;
    private final PaginaClientes paginaClientes;
    private final PaginaConta paginaConta;

    public CadastrarCliente(WebDriver driver, WebDriverWait wait) throws Exception {
        paginaCadastrar = new PaginaCadastrar(driver, wait);
        paginaClientes = new PaginaClientes(driver, wait);
        paginaConta = new PaginaConta(driver, wait);
    }

    public void cadastrarCliente(CadastrarClienteDTO cadastro) throws InterruptedException {
        paginaCadastrar.abrir();
        paginaCadastrar.preencherCliente(cadastro);
        paginaCadastrar.preencherEnderecos(cadastro.getEnderecos());
        paginaCadastrar.enviarCadastro();
    }

    public void mostrarPaginaConta() throws InterruptedException {
        paginaClientes.abrir();
        paginaClientes.logarComoNovoCliente();
        paginaConta.trocarSecao(SecoesConta.ENDERECO);
        paginaConta.trocarSecao(SecoesConta.CARTAO_CREDITO);
        paginaConta.trocarSecao(SecoesConta.CUPOM);
        paginaConta.trocarSecao(SecoesConta.DADOS_PESSOAIS);
    }

    public void editarNovoCliente(Cliente c) throws InterruptedException {
        paginaClientes.abrir();
        paginaClientes.logarComoNovoCliente();
        ModaisConta modal = ModaisConta.ALTERAR_DADOS_PESSOAIS;

        paginaConta.trocarSecao(SecoesConta.DADOS_PESSOAIS);
        paginaConta.abrirModal(modal);

        paginaConta.preencherInputModal(modal, "nome", c.getNome());
        paginaConta.preencherInputDataModal(modal , "dataNascimento", c.getDataNascimento());
        paginaConta.enviar(modal);
    }

    public void editarSenhaCliente(AlterarSenhaDTO dto) throws InterruptedException {
        paginaClientes.abrir();
        paginaClientes.logarComoNovoCliente();

        ModaisConta modal = ModaisConta.ALTERAR_SENHA;

        paginaConta.trocarSecao(SecoesConta.DADOS_PESSOAIS);
        paginaConta.abrirModal(modal);

        paginaConta.preencherInputModal(modal, "senhaAntiga", dto.getSenhaAntiga());
        paginaConta.preencherInputModal(modal, "senhaNova", dto.getSenhaNova());
        paginaConta.preencherInputModal(modal, "senhaConfirmacao", dto.getSenhaConfirmacao());

        paginaConta.enviar(modal);
    }

    public void inativarNovoCliente() throws InterruptedException {
        paginaClientes.abrir();
        paginaClientes.inativarNovoCliente();
    }

}
