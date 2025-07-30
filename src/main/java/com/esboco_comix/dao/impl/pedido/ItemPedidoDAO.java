package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.ItemPedidoMapper;
import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class ItemPedidoDAO {

    private final ItemPedidoMapper itemPedidoMapper = new ItemPedidoMapper();

    public ItemPedido inserir(ItemPedido item) {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO itens_pedido("+
                    "ite_ped_id, ite_qua_id, ite_quantidade, ite_valor_unitario)"+
                    "VALUES (?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
        ){
            pst.setInt(1, item.getIdPedido());
            pst.setInt(2, item.getIdQuadrinho());
            pst.setInt(3, item.getQuantidade());
            pst.setDouble(4, item.getPreco());

            if (pst.executeUpdate() == 0){
                throw new IllegalStateException("Inserção de item de pedido não executada!");
            }

            return consultarByID(item.getIdPedido(), item.getIdQuadrinho());
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public ItemPedido consultarByID(int idPedido, int idQuadrinho) {
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
                throw new IllegalStateException("Item do pedido não encontrado!");
            }
            
            return itemPedidoMapper.mapearEntidade(rs);
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<ItemPedidoDTO> consultarByIDPedido(int idPedido) {
        try(
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                """
                SELECT
                    itens_pedido.*,
                    qua_titulo,
                    qua_url_imagem,
                    cli_nome
                FROM
                    itens_pedido
                    JOIN pedidos ON ite_ped_id = ped_id
                    JOIN clientes ON ped_cli_id = cli_id
                    JOIN quadrinhos ON ite_qua_id = qua_id;""",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            pst.setInt(1, idPedido);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new IllegalStateException("Pedido não possui nenhum item.");
            }
            rs.beforeFirst();
    
            List<ItemPedidoDTO> itens = new ArrayList<>();
            while(rs.next()){
                itens.add(
                    itemPedidoMapper.mapearDTO(rs)
                );
            }

            return itens;    
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

}
