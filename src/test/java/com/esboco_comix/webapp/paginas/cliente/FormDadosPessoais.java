package com.esboco_comix.webapp.paginas.cliente;

import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.webapp.base.web_element.FormElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormDadosPessoais extends FormElement{
        
    public FormDadosPessoais(WebDriver webDriver, WebElement form){
        super(webDriver, form);
    }

    public void preencherCliente(CadastrarClienteDTO pedido) throws InterruptedException {
        Cliente c = pedido.getCliente();

        preencherInput("nome", c.getNome());
        preencherInput("cpf", c.getCpf());
        preencherInput("email", c.getEmail());
        preencherInputSelect("tipoTelefone", c.getTelefone().getTipo().name());
        preencherInput("ddd", c.getTelefone().getDdd());
        preencherInput("numero", c.getTelefone().getNumero());
        preencherInputData("dataNascimento", c.getDataNascimento());
        preencherInput("senhaNova", pedido.getSenhaNova());
        preencherInput("senhaConfirmacao", pedido.getSenhaConfirmacao());
    }

}
