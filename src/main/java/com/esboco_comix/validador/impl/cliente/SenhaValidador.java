package com.esboco_comix.validador.impl.cliente;

import com.esboco_comix.dto.AlterarSenhaDTO;
import com.esboco_comix.dto.CadastrarClienteDTO;
import com.esboco_comix.utils.CriptografadorSenha;
import com.esboco_comix.validador.AbstractValidador;
import com.esboco_comix.validador.IValidador;

public class SenhaValidador extends AbstractValidador implements IValidador<CadastrarClienteDTO> {
    @Override
    public void validar(CadastrarClienteDTO pedido) {
        String senhaNova = pedido.getSenhaNova();
        String senhaConfirmacao = pedido.getSenhaConfirmacao();

        validarAtributoObrigatorio(senhaNova, "Senha");
        validarAtributoObrigatorio(senhaNova, "Senha de confirmação");

        if (!(senhaNova.equals(senhaConfirmacao))){
            throw new IllegalArgumentException("Senha e senha de confirmação devem ser iguais!");
        }

        if (senhaNova.length() < 8 || senhaNova.length() > 64){
            throw new IllegalArgumentException("Senha deve ter entre 8 e 64 caracteres!");
        }

        if (!(senhaNova.matches(".*[A-Z].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere maiúsculo!");
        }

        if (!(senhaNova.matches(".*[a-z].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere minúsculo!");
        }

        if (!(senhaNova.matches(".*[!@#$%^&*(),.?\":{}|<>].*"))){
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere especial!");
        }
    }

    public void validar(AlterarSenhaDTO pedido) {
        CadastrarClienteDTO c = new CadastrarClienteDTO();
        c.setSenhaNova(pedido.getSenhaNova());
        c.setSenhaConfirmacao(pedido.getSenhaConfirmacao());

        validar(c);
    }

    public void validarSenhaAntiga(String senhaNova, String hashGuardado, String saltGuardado) {
        String hashNovo = CriptografadorSenha.hashSenha(senhaNova, saltGuardado);
        if (!hashNovo.equals(hashGuardado)){
            throw new IllegalArgumentException("Senha antiga não consta com senha inserida pelo usuário!");
        }
    }

}
