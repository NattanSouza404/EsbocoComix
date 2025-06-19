package com.esboco_comix.service.impl;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroAnaliseDTO {
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinal;
}
