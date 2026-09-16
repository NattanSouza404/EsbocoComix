package com.esboco_comix.cupom.service;

import java.util.List;

import com.esboco_comix.cupom.dao.CupomDAO;
import com.esboco_comix.cupom.dominio.Cupom;
import com.esboco_comix.cupom.dto.CadastrarCupomDTO;

public class CupomService {

    private final CupomDAO cupomDAO = new CupomDAO();
    
    public List<Cupom> consultarByIDCliente(int idCliente) {
        return cupomDAO.consultarByIDCliente(idCliente);
    }

    public Cupom inserir(CadastrarCupomDTO dto) {
        Cupom cupomToAdd = Cupom.builder()
            .isAtivo(true)
            .valor(dto.valor())
            .idCliente(dto.idCliente())
            .isPromocional(dto.isPromocional())
            .isTroca(dto.isTroca())
        .build();
        
        return cupomDAO.inserir(cupomToAdd);
    }

}
