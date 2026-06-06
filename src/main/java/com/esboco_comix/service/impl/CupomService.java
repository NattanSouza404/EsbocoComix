package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.model.entidades.Cupom;

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

    public Cupom gerarCupomTroca(int idCliente, double valor) {
        Cupom cupom = new Cupom();
        cupom.setAtivo(true);
        cupom.setIdCliente(idCliente);
        cupom.setTroca(true);
        cupom.setPromocional(false);
        cupom.setValor(valor);

        cupom.validar();

        return inserir(cupom);
    }

}
