package com.esboco_comix.webapp.testes.crud_cliente;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.conta.*;
import com.esboco_comix.webapp.paginas.cliente.conta.*;
import org.junit.Test;

import java.time.LocalDate;

public class PaginaContaTest extends BaseTest {

    @Test
    public void abrirConta(){
        try {
            PaginaConta conta = new PaginaConta(driver, wait);
            conta.logar();

            conta.trocarSecao(SecoesConta.ENDERECO);
            conta.trocarSecao(SecoesConta.CARTAO_CREDITO);
            conta.trocarSecao(SecoesConta.CUPOM);
            conta.trocarSecao(SecoesConta.DADOS_PESSOAIS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void editarCadastro() {
        try {
            EditarCadastroCliente conta = new EditarCadastroCliente(driver, wait);
            conta.logar();
            conta.trocarSecaoDadosPessoais();

            conta.abrirModalAlterarDadosPessoais();
            conta.preencherInputModal("nome", "Jorge dos Santos Menezes");
            conta.preencherInputDataModal("dataNascimento", LocalDate.of(1998, 12, 20));
            conta.enviarAtualizacao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void atualizarSenha() {
        try {
            EditarSenha conta = new EditarSenha(driver, wait);
            conta.logar();

            conta.trocarSecaoDadosPessoais();
            conta.abrirModalAlterarSenha();
            conta.preencherInputModal("senhaAntiga", "1234abC!");
            conta.preencherInputModal("senhaNova", "1234abC!");
            conta.preencherInputModal("senhaConfirmacao", "1234abC!");

            conta.enviarAtualizacao();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void atualizarEndereco() {
        try {
            AlterarEndereco conta = new AlterarEndereco(driver, wait);
            conta.logar();

            conta.trocarSecaoEndereco();
            conta.editarEndereco(0);

            conta.preencherInputModal("numero", "25");

            conta.enviar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inserirCartaoCredito() {
        
        try {
            AdicionarCartao fluxo = new AdicionarCartao(driver, wait);
            fluxo.logar();

            fluxo.trocarSecaoCartaoCredito();
            fluxo.abrirModal();

            fluxo.preencherInput("numero", "1111222233334444");
            fluxo.preencherInput("nomeImpresso", "JORGE DOS SANTOS MENEZES");
            fluxo.preencherInput("codigoSeguranca", "111");
            fluxo.preencherInputSelect("bandeiraCartao", "MASTERCARD");
            
            fluxo.enviar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarEndereco() {
        
        try {
            RemoverEndereco fluxo = new RemoverEndereco(driver, wait);

            fluxo.logar();
            fluxo.trocarSecaoEndereco();
            fluxo.removerEndereco(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deletarCartaoCredito() {
        try {
            RemoverCartao fluxo = new RemoverCartao(driver, wait);

            fluxo.logar();
            fluxo.trocarSecaoCartaoCredito();
            fluxo.removerCartao(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}