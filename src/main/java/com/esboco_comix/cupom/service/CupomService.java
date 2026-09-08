package com.esboco_comix.cupom.service;

import java.util.List;

import com.esboco_comix.cupom.dao.CupomDAO;
import com.esboco_comix.cupom.dominio.Cupom;

public class CupomService {

    private final CupomDAO cupomDAO = new CupomDAO();

    public Cupom consultarByID(int id){
        return cupomDAO.consultarByID(id);
    }

    public List<Cupom> consultarByIDCliente(int idCliente) {
        return cupomDAO.consultarByIDCliente(idCliente);
    }

    public Cupom inserir(Cupom cupom) {
        cupom.validar();
        return cupomDAO.inserir(cupom);
    }

    public Cupom inativar(int id) {
        return cupomDAO.inativar(id);
    }

}
