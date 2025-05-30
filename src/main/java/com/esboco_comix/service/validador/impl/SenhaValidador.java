package com.esboco_comix.service.validador.impl;

import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.service.validador.AbstractValidador;
import com.esboco_comix.service.validador.IValidador;
import com.esboco_comix.utils.CriptografadorSenha;

public class SenhaValidador extends AbstractValidador implements IValidador<CadastrarClienteDTO> {
    @Override
    public void validar(CadastrarClienteDTO pedido) throws Exception {
        String senhaNova = pedido.getSenhaNova();
        String senhaConfirmacao = pedido.getSenhaConfirmacao();

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

        if (!(senhaNova.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))){
            throw new Exception("Senha deve conter pelo menos um caractere especial!");
        }
    }

    public void validar(AlterarSenhaDTO pedido) throws Exception {
        CadastrarClienteDTO c = new CadastrarClienteDTO();
        c.setSenhaNova(pedido.getSenhaNova());
        c.setSenhaConfirmacao(pedido.getSenhaConfirmacao());

        validar(c);
    }

    public void validarSenhaAntiga(String senhaNova, String hashGuardado, String saltGuardado) throws Exception {
        String hashNovo = CriptografadorSenha.hashSenha(senhaNova, saltGuardado);
        if (!hashNovo.equals(hashGuardado)){
            throw new Exception("Senha antiga não consta com senha inserida pelo usuário!");
        }
    }

}
