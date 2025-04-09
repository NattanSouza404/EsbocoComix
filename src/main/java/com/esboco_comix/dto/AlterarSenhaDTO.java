package com.esboco_comix.dto;

import com.esboco_comix.model.entidades.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlterarSenhaDTO {
    private Cliente cliente;
    private String senhaAntiga;
    private String senhaNova;
    private String senhaConfirmacao;
}
