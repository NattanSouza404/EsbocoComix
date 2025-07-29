package com.esboco_comix.dao.impl.quadrinho;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.EntradaEstoqueMapper;
import com.esboco_comix.dto.EntradaEstoqueDTO;
import com.esboco_comix.model.entidades.EntradaEstoque;
import com.esboco_comix.model.entidades.Estoque;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class EstoqueDAO {

    private EntradaEstoqueMapper entradaEstoqueMapper = new EntradaEstoqueMapper();

    public EntradaEstoque inserir(EntradaEstoque entradaEstoque) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao();
            CallableStatement cs = conn.prepareCall(
                "CALL inserir_entrada_estoque(?, ?, ?, ?, ?, ?)"
            );
        ){
            cs.setInt(1, entradaEstoque.getIdQuadrinho());
            cs.setInt(2, entradaEstoque.getQuantidade());
            cs.setDouble(3, entradaEstoque.getValorCusto());
            cs.setString(4, entradaEstoque.getFornecedor());
            cs.setObject(5, entradaEstoque.getDataEntrada());
            cs.setNull(6, Types.NUMERIC);

            int id = 0;
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
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                """
                SELECT * FROM entrada_estoque WHERE ees_id = ?;
                """
            );
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Entrada de estoque não encontrado!");
            }

            return entradaEstoqueMapper.mapearEntidade(rs);
        }

    }

    public Estoque consultarEstoqueByIDQuadrinho(int idQuadrinho) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                """
                SELECT * FROM estoque WHERE est_qua_id = ?;
                """
            );
        ) {
            pst.setInt(1, idQuadrinho);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Estoque não encontrado!");
            }

            return mapearEntidadeEstoque(rs);
        }
    }

    public Estoque retornarAoEstoque(ItemPedido itemPedido) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao(); 
    
            PreparedStatement pst = conn.prepareStatement(
                """
                UPDATE estoque
                    SET est_quantidade_total = est_quantidade_total + ?
                WHERE est_qua_id = ?;
                """
            );
        ) {
            pst.setInt(1, itemPedido.getQuantidade());
            pst.setInt(2, itemPedido.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarEstoqueByIDQuadrinho(itemPedido.getIdQuadrinho());
        }
    }

    private Estoque mapearEntidadeEstoque(ResultSet rs) throws SQLException {
        Estoque e  = new Estoque();

        e.setIdQuadrinho(rs.getInt("est_qua_id"));
        e.setQuantidadeTotal(rs.getInt("est_quantidade_total"));

        return e;
    }

    public Estoque retirarDoEstoque(ItemPedido item) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao(); 
    
            PreparedStatement pst = conn.prepareStatement(
                """
                UPDATE estoque
                    SET est_quantidade_total = est_quantidade_total - ?
                WHERE est_qua_id = ?;
                """
            );
        ) {
            pst.setInt(1, item.getQuantidade());
            pst.setInt(2, item.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarEstoqueByIDQuadrinho(item.getIdQuadrinho());
        }
    }

    public List<EntradaEstoqueDTO> consultarEntradasEstoque() throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao();

            PreparedStatement pst = conn.prepareStatement(
                """
                SELECT *, qua_titulo FROM entrada_estoque JOIN quadrinhos ON ees_qua_id = qua_id ORDER BY ees_id;
                """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<EntradaEstoqueDTO> entradasEstoque = new ArrayList<>();
            while(rs.next()){
                entradasEstoque.add(entradaEstoqueMapper.mapearDTO(rs));
            }

            return entradasEstoque;
        }
    }

}
