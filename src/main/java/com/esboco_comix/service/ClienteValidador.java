package com.esboco_comix.service;

import com.esboco_comix.controller.pedidos.PedidoCadastrarCliente;
import com.esboco_comix.model.entidades.Cliente;
import com.esboco_comix.utils.CriptografadorSenha;

public class ClienteValidador {
    public void validarCadastro(PedidoCadastrarCliente pedido) throws Exception{
        validarCliente(pedido.getCliente());
        validarSenhas(pedido.getSenhaNova(), pedido.getSenhaConfirmacao());
    }

    private void validarCliente(Cliente cliente) throws Exception {
        if (checarSeVazio(cliente.getNome())){
            throw new Exception("Nome é obrigatório!");
        }

        if (checarSeVazio(cliente.getCpf())){
            throw new Exception("CPF é obrigatório!");
        }

        if (checarSeVazio(cliente.getGenero())){
            throw new Exception("Gênero é obrigatório!");
        }

        if (checarSeVazio(cliente.getEmail())){
            throw new Exception("E-mail é obrigatório!");
        }

        if (cliente.getDataNascimento() == null){
            throw new Exception("Data de nascimento é obrigatório!");
        }

        if (cliente.getTelefone() == null){
            throw new Exception("Telefone é obrigatório!");
        }

        if (checarSeVazio(cliente.getTelefone().getTipo())){
            throw new Exception("Tipo de telefone é obrigatório!");
        }

        if (checarSeVazio(cliente.getTelefone().getDdd())){
            throw new Exception("DDD de telefone é obrigatório!");
        }

        if (checarSeVazio(cliente.getTelefone().getNumero())){
            throw new Exception("Número de telefone é obrigatório!");
        }
    }

    private boolean checarSeVazio(String string) {
        
        if (string == null){
            return true;
        }
        
        if (string.isEmpty()){
            return true;
        }
        
        return false;
    }

    public void validarSenhas(String senhaNova, String senhaConfirmacao) throws Exception {
        if (checarSeVazio(senhaNova) || checarSeVazio(senhaNova)){
            throw new Exception("Senha e senha de confirmação obrigatórias!");
        }

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
