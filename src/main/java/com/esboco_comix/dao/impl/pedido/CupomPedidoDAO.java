package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class CupomPedidoDAO {
    public CupomPedido inserir(CupomPedido e) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO cupons_pedido("+
                    "cpe_cup_id, cpe_ped_id)"+
                    "VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, e.getIdCupom());
            pst.setInt(2, e.getIdPedido());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de cupom no pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            CupomPedido cupomPedidoInserido = null;
            if (rs.next()){
                cupomPedidoInserido = consultarByID(e.getIdCupom(), e.getIdPedido());
            }

            return cupomPedidoInserido;
        }
    }

    public CupomPedido consultarByID(int idCupom, int idPedido) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM cupons_pedido WHERE cpe_cup_id = ? AND cpe_ped_id = ?;"
            );
        ) {
            pst.setInt(1, idCupom);
            pst.setInt(2, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cupom do pedido não encontrado!");
            }
            
            return mapearEntidade(rs);
        }
    }

    private CupomPedido mapearEntidade(ResultSet rs) throws SQLException {
        CupomPedido cupomPedido = new CupomPedido();

        cupomPedido.setIdCupom(rs.getInt("cpe_cup_id"));
        cupomPedido.setIdPedido(rs.getInt("cpe_ped_id"));

        return cupomPedido;
    }
}
