package com.esboco_comix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlterarSenhaDTO {
    private int idCliente;
    private String senhaAntiga;
    private String senhaNova;
    private String senhaConfirmacao;
}
