package com.esboco_comix.service.validador;

import static com.esboco_comix.service.validador.ValidadorUtil.*;

import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteValidador {
    public void validarCadastro(PedidoCadastrarCliente pedido) throws Exception{
        validarCliente(pedido.getCliente());
        validarSenhas(pedido.getSenhaNova(), pedido.getSenhaConfirmacao());
    }

    private void validarCliente(Cliente cliente) throws Exception {
        validarAtributoObrigatorio(cliente.getNome(), "Nome");
        validarAtributoObrigatorio(cliente.getCpf(), "CPF");
        validarAtributoObrigatorio(cliente.getGenero(), "Gênero");
        validarAtributoObrigatorio(cliente.getEmail(), "E-mail");
        validarAtributoObrigatorio(cliente.getDataNascimento(), "Data de Nascimento");
        validarAtributoObrigatorio(cliente.getTelefone(), "Telefone");
        validarAtributoObrigatorio(cliente.getTelefone().getDdd(), "DDD");
        validarAtributoObrigatorio(cliente.getTelefone().getNumero(), "Número de telefone");
        validarAtributoObrigatorio(cliente.getTelefone().getTipo(), "Tipo de telefone");

        validarSeApenasNumeros(cliente.getCpf(), "CPF");
        validarSeApenasNumeros(cliente.getTelefone().getDdd(), "DDD");
        validarSeApenasNumeros(cliente.getTelefone().getNumero(), "Número de telefone");
        validarEmail(cliente.getEmail());
    }

    public void validarSenhas(String senhaNova, String senhaConfirmacao) throws Exception {
        validarAtributoObrigatorio(senhaNova, "Senha");
        validarAtributoObrigatorio(senhaNova, "Senha de confirmação");

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new Exception("Senha e senha de confirmação devem ser iguais!");
        }

        if (senhaNova.length() < 8 || senhaNova.length() > 64){
            throw new Exception("Senha deve ter entre 8 e 64 caracteres!");
        }

        if (!(senhaNova.matches(".*[A-Z].*"))){
            throw new Exception("Senha deve conter pelo menos um caractere maiúsculo!");
        }

        if (!(senhaNova.matches(".*[a-z].*"))){
            throw new Exception("Senha deve conter pelo menos um caractere minúsculo!");
        }

        if (!(senhaNova.matches(".*[!@#$%^&*(),.?\\\":{}|<>].*"))){
            throw new Exception("Senha deve conter pelo menos um caractere especial!");
        }
    }

    public void validarSenhaAntiga(String senhaNova, String hashGuardado, String saltGuardado) throws Exception {
        String hashNovo = CriptografadorSenha.hashSenha(senhaNova, saltGuardado); 
        if (!hashNovo.equals(hashGuardado)){
            throw new Exception("Senha antiga não consta com senha inserida pelo usuário!");
        }    
    }

}
