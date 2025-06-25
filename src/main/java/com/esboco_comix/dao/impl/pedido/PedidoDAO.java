package com.esboco_comix.dao.impl.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dto.ItemPedidoDTO;
import com.esboco_comix.dto.PedidoDTO;
import com.esboco_comix.model.entidades.Endereco;
import com.esboco_comix.model.entidades.ItemPedido;
import com.esboco_comix.model.entidades.Pedido;
import com.esboco_comix.model.enuns.StatusPedido;
import com.esboco_comix.utils.ConexaoFactory;

public class PedidoDAO {

    public Pedido inserir(Pedido e) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "INSERT INTO pedidos("+
                "ped_cli_id, ped_status, ped_end_id, ped_valor_total, ped_valor_frete)"+
                "VALUES (?, ?, ?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        );

        try {
            pst.setInt(1, e.getIdCliente());
            pst.setString(2, e.getStatus().name());
            pst.setInt(3, e.getEnderecoEntrega().getId());
            pst.setDouble(4, e.getValorTotal());
            pst.setDouble(5, e.getValorFrete());

            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de pedido não executada!");
            }

            ResultSet rs = pst.getGeneratedKeys();
            Pedido pedidoInserido = null;
            if (rs.next()){
                pedidoInserido = consultarByID(rs.getInt(1));
            }

            return pedidoInserido;
        } catch (Exception ex){
            throw ex;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public List<PedidoDTO> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
        """
            SELECT
                pedidos.*,
                itens_pedido.*,
                qua_titulo,
                qua_preco,
                qua_url_imagem,
                cli_nome,
                enderecos.*
            FROM
                pedidos
                JOIN clientes ON ped_cli_id = cli_id
                JOIN enderecos ON ped_end_id = end_id
                JOIN itens_pedido ON ite_ped_id = ped_id
                JOIN quadrinhos ON ite_qua_id = qua_id
            ORDER BY ped_data DESC;
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

            return mapearPedidosDTO(rs);
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<PedidoDTO> consultarByIDCliente(int idCliente) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
        """
            SELECT
                pedidos.*,
                itens_pedido.*,
                qua_titulo,
                qua_preco,
                qua_url_imagem,
                cli_nome,
                enderecos.*
            FROM
                pedidos
                JOIN clientes ON ped_cli_id = cli_id
                JOIN enderecos ON ped_end_id = end_id
                JOIN itens_pedido ON ite_ped_id = ped_id
                JOIN quadrinhos ON ite_qua_id = qua_id
            WHERE cli_id = ?
            ORDER BY ped_data DESC;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            return mapearPedidosDTO(rs);
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public Pedido consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            "SELECT pedidos.*, enderecos.* FROM pedidos JOIN enderecos ON ped_end_id = end_id WHERE ped_id = ?;"
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Pedido não encontrado.");
            }
            return mapearEntidade(rs);    
        } catch (Exception e){
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    public Pedido atualizarStatus(Pedido p) throws Exception {
        Connection conn = ConexaoFactory.getConexao(); 
    
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE pedidos set "+
                "ped_status = ? WHERE ped_id = ?;"
        );
    
        try {
            pst.setString(1, p.getStatus().name());
            pst.setInt(2, p.getId());

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(p.getId());
        } catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        } 
    }

    private List<PedidoDTO> mapearPedidosDTO(ResultSet rs) throws Exception {
        List<PedidoDTO> pedidos = new ArrayList<>();

        while (rs.next()){
            PedidoDTO dto = null;

            int id = rs.getInt("ped_id");
            for (PedidoDTO p: pedidos){
                if (p.getPedido().getId() == id){
                    dto = p;
                    break;
                }
            }

            if (dto == null){
                dto = mapearPedidoDTO(rs);
                pedidos.add(dto);
            }

            dto.getItensPedidoDTO().add(mapearItemPedidoDTO(rs));
        }

        return pedidos;
    }

    private Pedido mapearEntidade(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("ped_id"));
        pedido.setIdCliente(rs.getInt("ped_cli_id"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("ped_status")));
        pedido.setValorTotal(rs.getDouble("ped_valor_total"));
        
        Endereco endereco = new Endereco();
        endereco.setId(rs.getInt("ped_end_id"));
        endereco.setFraseCurta(rs.getString("end_frase_curta"));
        endereco.setCidade(rs.getString("end_cidade"));
        endereco.setEstado(rs.getString("end_estado"));
        endereco.setPais(rs.getString("end_pais"));
        endereco.setCep(rs.getString("end_cep"));
        pedido.setEnderecoEntrega(endereco);

        pedido.setValorFrete(rs.getDouble("ped_valor_frete"));
        pedido.setData(rs.getTimestamp("ped_data").toLocalDateTime());

        return pedido;
    }

    private PedidoDTO mapearPedidoDTO(ResultSet rs) throws Exception {
        PedidoDTO dto = new PedidoDTO();
        dto.setPedido(mapearEntidade(rs));
        dto.setItensPedidoDTO(new ArrayList<>());
        dto.setNomeCliente(rs.getString("cli_nome"));
        return dto;
    }

    private ItemPedidoDTO mapearItemPedidoDTO(ResultSet rs) throws SQLException{
        ItemPedidoDTO dto = new ItemPedidoDTO();

        ItemPedido item = new ItemPedido();
        item.setIdPedido(rs.getInt("ped_id"));
        item.setQuantidade(rs.getInt("ite_quantidade"));

        item.setIdQuadrinho(rs.getInt("ite_qua_id"));

        dto.setItemPedido(item);

        dto.setNomeCliente(rs.getString("cli_nome"));
        dto.setNomeQuadrinho(rs.getString("qua_titulo"));
        dto.setUrlImagem(rs.getString("qua_url_imagem"));

        return dto;
    }
    
}
