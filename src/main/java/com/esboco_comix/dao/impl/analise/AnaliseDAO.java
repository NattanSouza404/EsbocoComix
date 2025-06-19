package com.esboco_comix.dao.impl.analise;

import com.esboco_comix.dto.ItemVendaDTO;
import com.esboco_comix.service.impl.FiltroAnaliseDTO;
import com.esboco_comix.utils.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnaliseDAO {
    public List<ItemVendaDTO> consultarProdutos(FiltroAnaliseDTO filtro) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        StringBuilder query = new StringBuilder( 
            """
            SELECT qua_titulo, ite_quantidade, ped_data FROM itens_pedido
                JOIN quadrinhos ON ite_qua_id = qua_id
                JOIN pedidos ON ite_ped_id = ped_id
            WHERE 1 = 1
            """);

        List<LocalDateTime> params = new ArrayList<>();

        if (filtro.getDataInicio() != null){
            query.append(" AND ped_data >= ?");
            params.add(filtro.getDataInicio());
        }

        if (filtro.getDataFinal() != null){
            query.append(" AND ped_data <= ?");
            params.add(filtro.getDataFinal());
        }

        query.append(" ORDER BY ped_data;");

        PreparedStatement pst = conn.prepareStatement(
            query.toString(),
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );
        
        try {

            for (int i = 0; i < params.size(); i++) {
                pst.setTimestamp(i + 1, java.sql.Timestamp.valueOf(params.get(i)));
            }

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de venda.");
            }
            rs.beforeFirst();

            List<ItemVendaDTO> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVendaDTO.DadosItem dados = new ItemVendaDTO.DadosItem();
                dados.setQuantidade(rs.getInt("ite_quantidade"));
                dados.setData(rs.getTimestamp("ped_data").toLocalDateTime());

                boolean valorRepetido = false;
                for (ItemVendaDTO i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("qua_titulo"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
                itemVendaDTO.setTitulo(rs.getString("qua_titulo"));

                itemVendaDTO.getDados().add(dados);

                itensVendidos.add(itemVendaDTO);
            }

            return itensVendidos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<ItemVendaDTO> consultarCategorias(FiltroAnaliseDTO filtro) throws Exception{
        Connection conn = ConexaoFactory.getConexao();

        StringBuilder query = new StringBuilder( 
            """
            SELECT cat_nome, ite_quantidade, ped_data FROM categorias
                JOIN categorias_quadrinho ON cat_id = cqu_cat_id\s
                JOIN quadrinhos ON qua_id = cqu_qua_id
                JOIN itens_pedido ON ite_qua_id = qua_id
                JOIN pedidos ON ite_ped_id = ped_id
            WHERE 1 = 1
            """);

        List<LocalDateTime> params = new ArrayList<>();

        if (filtro.getDataInicio() != null){
            query.append(" AND ped_data >= ?");
            params.add(filtro.getDataInicio());
        }

        if (filtro.getDataFinal() != null){
            query.append(" AND ped_data <= ?");
            params.add(filtro.getDataFinal());
        }

        query.append(" ORDER BY ped_data;");

        PreparedStatement pst = conn.prepareStatement(
            query.toString(),
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            for (int i = 0; i < params.size(); i++) {
                pst.setTimestamp(i + 1, java.sql.Timestamp.valueOf(params.get(i)));
            }

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de venda.");
            }
            rs.beforeFirst();

            List<ItemVendaDTO> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVendaDTO.DadosItem dados = new ItemVendaDTO.DadosItem();
                dados.setQuantidade(rs.getInt("ite_quantidade"));
                dados.setData(rs.getTimestamp("ped_data").toLocalDateTime());

                boolean valorRepetido = false;
                for (ItemVendaDTO i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("cat_nome"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
                itemVendaDTO.setTitulo(rs.getString("cat_nome"));

                itemVendaDTO.getDados().add(dados);

                itensVendidos.add(itemVendaDTO);
            }

            return itensVendidos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }
}
