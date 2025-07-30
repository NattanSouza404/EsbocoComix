package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.esboco_comix.dao.mapper.impl.CupomPedidoMapper;
import com.esboco_comix.model.entidades.CupomPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class CupomPedidoDAO {

    private final CupomPedidoMapper cupomPedidoMapper = new CupomPedidoMapper();

    public CupomPedido inserir(CupomPedido cupom) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO cupons_pedido("+
                    "cpe_cup_id, cpe_ped_id)"+
                    "VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, cupom.getIdCupom());
            pst.setInt(2, cupom.getIdPedido());

            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de cupom no pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            CupomPedido cupomPedidoInserido = null;
            if (rs.next()){
                cupomPedidoInserido = consultarByID(cupom.getIdCupom(), cupom.getIdPedido());
            }

            return cupomPedidoInserido;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public CupomPedido consultarByID(int idCupom, int idPedido) {
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
                throw new IllegalStateException("Cupom do pedido não encontrado!");
            }
            
            return cupomPedidoMapper.mapearEntidade(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

}
