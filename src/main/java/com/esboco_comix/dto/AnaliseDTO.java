package com.esboco_comix.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record AnaliseDTO(
    List<ItemVendaDTO> produtos,
    List<ItemVendaDTO> categorias
) {}