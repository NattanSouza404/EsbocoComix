package com.esboco_comix.analise.dto;

import java.util.List;

import com.esboco_comix.dto.ItemVendaDTO;

import lombok.Builder;

@Builder
public record AnaliseDTO(
    List<ItemVendaDTO> produtos,
    List<ItemVendaDTO> categorias
) {}