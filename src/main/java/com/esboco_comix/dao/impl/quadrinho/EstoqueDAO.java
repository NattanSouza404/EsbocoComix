package com.esboco_comix.dao.impl.quadrinho;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.model.entidades.Estoque;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class EstoqueDAO {

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) throws Exception {

        Connection conn = ConexaoFactory.getConexao();

        String call = "CALL inserir_entrada_estoque(?, ?, ?, ?, ?, ?)";

        int id = 0;
        
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.setInt(1, entradaEstoque.getIdQuadrinho());
            cs.setInt(2, entradaEstoque.getQuantidade());
            cs.setDouble(3, entradaEstoque.getValorCusto());
            cs.setString(4, entradaEstoque.getFornecedor());
            cs.setObject(5, entradaEstoque.getDataEntrada());
            cs.setNull(6, Types.NUMERIC);

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

    public Estoque consultarEstoqueByIDQuadrinho(int idQuadrinho) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
            SELECT * FROM estoque WHERE est_qua_id = ?;
            """
        );

        try {
            pst.setInt(1, idQuadrinho);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Estoque não encontrado!");
            }

            return mapearEntidadeEstoque(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    public Estoque retornarAoEstoque(ItemPedido itemPedido) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            """
            UPDATE estoque
                SET est_quantidade_total = est_quantidade_total + ?
            WHERE est_qua_id = ?;
            """
        );

        try {
            pst.setInt(1, itemPedido.getQuantidade());
            pst.setInt(2, itemPedido.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarEstoqueByIDQuadrinho(itemPedido.getIdQuadrinho());
        } catch (Exception ex){
            throw ex;
        } finally {
            pst.close();
            conn.close();
        }
    }

    private Estoque mapearEntidadeEstoque(ResultSet rs) throws SQLException {
        Estoque e  = new Estoque();

        e.setIdQuadrinho(rs.getInt("est_qua_id"));
        e.setQuantidadeTotal(rs.getInt("est_quantidade_total"));

        return e;
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

    public Estoque retirarDoEstoque(ItemPedido item) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            """
            UPDATE estoque
                SET est_quantidade_total = est_quantidade_total - ?
            WHERE est_qua_id = ?;
            """
        );

        try {
            pst.setInt(1, item.getQuantidade());
            pst.setInt(2, item.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarEstoqueByIDQuadrinho(item.getIdQuadrinho());
        } catch (Exception ex){
            throw ex;
        } finally {
            pst.close();
            conn.close();
        }
    }

}
