package com.esboco_comix.webapp.testes;

import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.FiltrarClienteDTO;
import com.esboco_comix.model.entidades.CartaoCredito;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.enuns.BandeiraCartao;
import com.esboco_comix.model.enuns.Genero;
import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.base.factories.CadastrarClienteFactory;
import com.esboco_comix.webapp.casos_de_uso.CadastrarCliente;
import com.esboco_comix.webapp.casos_de_uso.ConsultarClientes;
import com.esboco_comix.webapp.casos_de_uso.FluxoCadastroCartao;
import com.esboco_comix.webapp.casos_de_uso.conta.FluxoCadastroEndereco;
import org.junit.Test;

import java.time.LocalDate;

public class CadastroClienteTest extends BaseTest {

    @Test
    public void cadastrarCliente() {
        try {
            CadastrarCliente fluxo = new CadastrarCliente(driver, wait);

            fluxo.cadastrarCliente(CadastrarClienteFactory.criar());
            fluxo.mostrarPaginaConta();

            Cliente c = new Cliente();
            c.setNome("Jorge dos Santos Menezes");
            c.setDataNascimento(LocalDate.of(1998, 12, 20));

            fluxo.editarNovoCliente(c);

            AlterarSenhaDTO alterarSenhaDTO = new AlterarSenhaDTO();
            alterarSenhaDTO.setSenhaAntiga("1234abC!");
            alterarSenhaDTO.setSenhaNova("1234abC!");
            alterarSenhaDTO.setSenhaConfirmacao("1234abC!");

            fluxo.editarSenhaCliente(alterarSenhaDTO);
            fluxo.inativarNovoCliente();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void visualizarClientes(){

        try {
            ConsultarClientes fluxo = new ConsultarClientes(driver, wait);

            FiltrarClienteDTO filtro = new FiltrarClienteDTO();
            filtro.setGenero(Genero.FEMININO);

            FiltrarClienteDTO filtro2 = new FiltrarClienteDTO();
            filtro2.setNome("Jorge");

            fluxo.consultarPorFiltro(filtro);
            fluxo.consultarPorFiltro(filtro2);

            fluxo.visualizarPedidosCliente(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cadastroEnderecoConta() {
        try {
            FluxoCadastroEndereco fluxo = new FluxoCadastroEndereco(driver, wait);

            Endereco e = new Endereco();
            e.setFraseCurta("Casa");

            fluxo.adicionarEndereco(e);

            e.setFraseCurta("Casa longe daqui");

            fluxo.alterarNovoEndereco(e);

            fluxo.deletarNovoEndereco();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cadastroCartaoCreditoConta() {
        try {
            FluxoCadastroCartao fluxo = new FluxoCadastroCartao(driver, wait);

            CartaoCredito c = new CartaoCredito();
            c.setNumero("1111222233334444");
            c.setNomeImpresso("JORGE DOS SANTOS MENEZES");
            c.setCodigoSeguranca("111");
            c.setBandeiraCartao(BandeiraCartao.MASTERCARD);

            fluxo.adicionarCartao(c);

            c.setCodigoSeguranca("222");

            fluxo.alterarCartao(c);

            fluxo.deletarNovoCartao();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
