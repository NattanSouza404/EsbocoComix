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
import com.esboco_comix.utils.ConexaoFactory;

public class ItemPedidoDAO {

    public ItemPedido inserir(ItemPedido e) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO itens_pedido("+
                    "ite_ped_id, ite_qua_id, ite_quantidade, ite_valor_unitario)"+
                    "VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ){
            pst.setInt(1, e.getIdPedido());
            pst.setInt(2, e.getIdQuadrinho());
            pst.setInt(3, e.getQuantidade());
            pst.setDouble(4, e.getPreco());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de item de pedido não executada!");
            }

            return consultarByID(e.getIdPedido(), e.getIdQuadrinho());
        }
    }

    public ItemPedido consultarByID(int idPedido, int idQuadrinho) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM itens_pedido WHERE ite_ped_id = ? AND ite_qua_id = ?;"
            );
        ){
            pst.setInt(1, idPedido);
            pst.setInt(2, idQuadrinho);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Item do pedido não encontrado!");
            }
            
            return mapearEntidade(rs);
        }
    }

    public List<ItemPedidoDTO> consultarByIDPedido(int idPedido) throws Exception {
        try(
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM itens_pedido WHERE ite_ped_id = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
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
                        null,
                        null
                    )
                );
            }

            return itens;    
        }
    }

    private ItemPedido mapearEntidade(ResultSet rs) throws SQLException {
        ItemPedido item = new ItemPedido();  
        item.setIdPedido(rs.getInt("ite_ped_id"));
        item.setIdQuadrinho(rs.getInt("ite_qua_id"));
        item.setQuantidade(rs.getInt("ite_quantidade"));
        item.setPreco(rs.getDouble("ite_valor_unitario"));
        return item;
    }

}
