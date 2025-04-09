package com.esboco_comix.webapp.paginas.cadastrar_cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.utils.FormElement;

public class FormDadosPessoais {

    private WebDriver driver;
    private FormElement form;
        
    public FormDadosPessoais(WebDriver webDriver){
        this.driver = webDriver;
        this.form = new FormElement(webDriver.findElement(By.id("cadastrar-dados-pessoais")));
    }

    public void preencherCliente(CadastrarClienteDTO pedido) throws InterruptedException {
        Cliente c = pedido.getCliente();

        form.preencherInput("nome", c.getNome());
        form.preencherInput("cpf", c.getCpf());
        form.preencherInput("email", c.getEmail());
        form.preencherInput("tipoTelefone", c.getTelefone().getTipo());
        form.preencherInput("ddd", c.getTelefone().getDdd());
        form.preencherInput("numero", c.getTelefone().getNumero());
        form.preencherInputData("dataNascimento", c.getDataNascimento(), driver);
        form.preencherInput("senhaNova", pedido.getSenhaNova());
        form.preencherInput("senhaConfirmacao", pedido.getSenhaConfirmacao());
    }

}
