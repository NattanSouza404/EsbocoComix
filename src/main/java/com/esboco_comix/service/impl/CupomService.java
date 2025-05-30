package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.service.validador.impl.CupomValidador;

public class CupomService {

    private final CupomDAO cupomDAO = new CupomDAO();
    private final CupomValidador cupomValidador = new CupomValidador();

    public Cupom consultarByID(int id) throws Exception{
        return cupomDAO.consultarByID(id);
    }

    public List<Cupom> consultarByIDCliente(int idCliente) throws Exception {
        return cupomDAO.consultarByIDCliente(idCliente);
    }

    public Cupom inserir(Cupom cupom) throws Exception {
        cupomValidador.validar(cupom);
        return cupomDAO.inserir(cupom);
    }

    public Cupom inativar(int id) throws Exception {
        return cupomDAO.inativar(id);
    }

    public Cupom gerarCupomTroca(int idCliente, double valor) throws Exception {
        Cupom cupom = new Cupom();
        cupom.setAtivo(true);
        cupom.setIdCliente(idCliente);
        cupom.setTroca(true);
        cupom.setPromocional(false);
        cupom.setValor(valor);

        cupomValidador.validar(cupom);

        return inserir(cupom);
    }

}
