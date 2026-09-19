package com.esboco_comix.analise.dto;

import java.util.List;

import com.esboco_comix.analise.dominio.ItemVenda;

import lombok.Builder;

@Builder
public record AnaliseDTO(
    List<ItemVenda> produtos,
    List<ItemVenda> categorias
) {}