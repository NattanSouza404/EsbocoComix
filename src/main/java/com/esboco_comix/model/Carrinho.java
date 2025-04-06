package com.esboco_comix.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Carrinho {
    private List<ItemCarrinho> itensCarrinho = new ArrayList<>();
}
