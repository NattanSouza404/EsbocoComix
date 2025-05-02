package com.esboco_comix.dao.impl.quadrinho;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.utils.ConexaoFactory;

public class EstoqueDAO {

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) throws Exception {

        Connection conn = ConexaoFactory.getConexao();

        String call = "CALL inserir_entrada_estoque(?, ?, ?, ?, ?)";

        int id = 0;
        
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, entradaEstoque.getIdQuadrinho());
            cs.setInt(2, entradaEstoque.getQuantidade());
            cs.setDouble(3, entradaEstoque.getValorCusto());
            cs.setString(4, entradaEstoque.getFornecedor());
            cs.setNull(5, Types.NUMERIC);

            if (cs.execute()) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                }
            }

            return consultarByID(id);
        }

    }

    public EntradaEstoque consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
            SELECT * FROM entrada_estoque WHERE ees_id = ?;
            """
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Entrada de estoque não encontrado!");
            }

            return mapearEntidade(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    private EntradaEstoque mapearEntidade(ResultSet rs) throws Exception {
        EntradaEstoque e = new EntradaEstoque();

        e.setId(rs.getInt("ees_id"));
        e.setFornecedor(rs.getString("ees_fornecedor"));
        e.setIdQuadrinho(rs.getInt("ees_qua_id"));
        e.setQuantidade(rs.getInt("ees_quantidade"));
        e.setValorCusto(rs.getDouble("ees_valor_custo"));
        e.setDataEntrada(rs.getTimestamp("ees_dt_entrada").toLocalDateTime());

        return e;
    }

}
