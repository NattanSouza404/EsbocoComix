package com.esboco_comix.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemVendaDTO {
    private String titulo;

    private List<DadosItem> dados = new ArrayList<>();

    @Getter
    @Setter
    public static class DadosItem {
        private int quantidade;
        private LocalDateTime data;
    }
}
