package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.model.entidades.Cupom;

public class CupomService {

    private CupomDAO cupomDAO = new CupomDAO();

    public List<Cupom> consultarByIDCliente(int idCliente) throws Exception {
        return cupomDAO.consultarByIDCliente(idCliente);
    }

}
