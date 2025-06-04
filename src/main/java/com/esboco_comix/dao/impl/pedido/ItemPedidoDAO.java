package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.enuns.StatusItemPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class ItemPedidoDAO {

    public ItemPedido inserir(ItemPedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO itens_pedido("+
                "ite_ped_id, ite_qua_id, ite_quantidade)"+
                "VALUES (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdPedido());
            pst.setInt(2, e.getIdQuadrinho());
            pst.setInt(3, e.getQuantidade());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de item de pedido não executada!");
            }

            return consultarByID(e.getIdPedido(), e.getIdQuadrinho());
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public ItemPedido consultarByID(int idPedido, int idQuadrinho) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM itens_pedido WHERE ite_ped_id = ? AND ite_qua_id = ?;"
        );

        try {
            pst.setInt(1, idPedido);
            pst.setInt(2, idQuadrinho);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Item do pedido não encontrado!");
            }
            
            return mapearEntidade(rs);
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }

    }

    public List<ItemPedidoDTO> consultarByIDPedido(int idPedido) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT * FROM itens_pedido WHERE ite_ped_id = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Pedido não possui nenhum item.");
            }
            rs.beforeFirst();
    
            List<ItemPedidoDTO> itens = new ArrayList<>();
            while(rs.next()){
                itens.add(
                    new ItemPedidoDTO(
                        mapearEntidade(rs),
                        null,
                        null
                    )
                );
            }

            return itens;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<ItemPedidoDTO> consultarPedidosTroca() throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
            SELECT itens_pedido.*, qua_titulo, qua_preco, cli_nome FROM itens_pedido
                JOIN quadrinhos ON ite_qua_id = qua_id
                JOIN pedidos ON ite_ped_id = ped_id
                JOIN clientes ON ped_cli_id = cli_id
            WHERE ite_status IS NOT NULL;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();
    
            List<ItemPedidoDTO> itens = new ArrayList<>();
            while(rs.next()){
                    itens.add(new ItemPedidoDTO(
                    mapearEntidade(rs),
                    rs.getString("qua_titulo"),
                    rs.getString("cli_nome")
                ));
            }

            return itens;    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public ItemPedido atualizarStatus(ItemPedido item) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE itens_pedido set "+
                "ite_status = ? WHERE ite_ped_id = ? AND ite_qua_id = ?;"
        );
    
        try {
            pst.setString(1, item.getStatus().name());
            pst.setInt(2, item.getIdPedido());
            pst.setInt(3, item.getIdQuadrinho());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(item.getIdPedido(), item.getIdQuadrinho());
        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        } 
    }

    private ItemPedido mapearEntidade(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();  
        item.setIdPedido(rs.getInt("ite_ped_id"));
        item.setIdQuadrinho(rs.getInt("ite_qua_id"));
        item.setQuantidade(rs.getInt("ite_quantidade"));

        String status = rs.getString("ite_status");
        if (status != null){
            item.setStatus(StatusItemPedido.valueOf(status));
        }

        return item;
    }

}
