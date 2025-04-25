package com.esboco_comix.service.impl;

import java.util.List;

import com.esboco_comix.dao.impl.cupom.CupomDAO;
import com.esboco_comix.model.entidades.Cupom;

public class CupomService {

    private CupomDAO cupomDAO = new CupomDAO();

    public Cupom consultarByID(int id) throws Exception{
        return cupomDAO.consultarByID(id);
    }

    public List<Cupom> consultarByIDCliente(int idCliente) throws Exception {
        return cupomDAO.consultarByIDCliente(idCliente);
    }

    public Cupom inserir(Cupom cupom) throws Exception {
        if (cupom.getValor() < 0){
            throw new Exception("Cupom não pode ter valor negativo!");
        }

        if (cupom.isPromocional() && cupom.isTroca()){
            throw new Exception("Cupom não pode ser promocional e de troca!");
        }

        if (!cupom.isPromocional() && !cupom.isTroca()){
            throw new Exception("Cupom deve ser de troca ou promocional!");
        }

        return cupomDAO.inserir(cupom);
    }

    public Cupom gerarCupomTroca(int idCliente, double valor) throws Exception {
        Cupom cupom = new Cupom();
        cupom.setAtivo(true);
        cupom.setIdCliente(idCliente);
        cupom.setTroca(true);
        cupom.setPromocional(false);
        cupom.setValor(valor);

        return inserir(cupom);
    }

}
