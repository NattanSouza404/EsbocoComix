package com.esboco_comix.cliente.dominio;

import com.esboco_comix.cliente.dominio.value_objects.Senha;

public interface CriptografadorSenha {
    String hashSenha(Senha senha, String salt);
    String generateSalt();
}
