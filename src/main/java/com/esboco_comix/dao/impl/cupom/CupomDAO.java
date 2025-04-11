package com.esboco_comix.dao.impl.cupom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.utils.ConexaoFactory;

public class CupomDAO {

    public Cupom consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM cupons WHERE cup_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cupom não encontrado!");
            }
            
            return mapearEntidade(rs);
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    private Cupom mapearEntidade(ResultSet rs) throws SQLException {
        Cupom cupom = new Cupom();

        cupom.setId(rs.getInt("cup_id"));
        cupom.setAtivo(rs.getBoolean("cup_is_ativo"));
        cupom.setPromocional(rs.getBoolean("cup_is_promocional"));
        cupom.setTroca(rs.getBoolean("cup_is_troca"));
        cupom.setValor(rs.getInt("cup_valor"));

        return cupom;
    }

}
