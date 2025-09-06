package com.esboco_comix.dao.impl.analise;

import com.esboco_comix.dto.ItemVendaDTO;
import com.esboco_comix.dto.FiltroAnaliseDTO;
import com.esboco_comix.utils.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnaliseDAO {
    public List<ItemVendaDTO> consultarProdutos(FiltroAnaliseDTO filtro) {
        StringBuilder query = new StringBuilder(
            """
            SELECT * FROM vw_analise_produtos WHERE 1 = 1
            """
        );

        List<LocalDateTime> params = new ArrayList<>();

        if (filtro.getDataInicio() != null){
            query.append(" AND data >= ?");
            params.add(filtro.getDataInicio());
        }

        if (filtro.getDataFinal() != null){
            query.append(" AND data <= ?");
            params.add(filtro.getDataFinal());
        }

        try (
            Connection conn = ConexaoFactory.getConexao();
            PreparedStatement pst = conn.prepareStatement(
                    query.toString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
        ){
            for (int i = 0; i < params.size(); i++) {
                pst.setTimestamp(i + 1, java.sql.Timestamp.valueOf(params.get(i)));
            }

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new IllegalStateException("Nenhum registro encontrado de venda.");
            }
            rs.beforeFirst();

            List<ItemVendaDTO> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVendaDTO.DadosItem dados = new ItemVendaDTO.DadosItem();
                dados.setQuantidade(rs.getInt("quantidade"));
                dados.setData(rs.getTimestamp("data").toLocalDateTime());
                dados.setValorTotal(rs.getDouble("valor_total"));

                boolean valorRepetido = false;
                for (ItemVendaDTO i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("titulo_quadrinho"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
                itemVendaDTO.setTitulo(rs.getString("titulo_quadrinho"));

                itemVendaDTO.getDados().add(dados);

                itensVendidos.add(itemVendaDTO);
            }

            return itensVendidos;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<ItemVendaDTO> consultarCategorias(FiltroAnaliseDTO filtro) {
        StringBuilder query = new StringBuilder( 
            """
            SELECT * FROM vw_analise_categorias WHERE 1 = 1
            """
        );

        List<LocalDateTime> params = new ArrayList<>();

        if (filtro.getDataInicio() != null){
            query.append(" AND data >= ?");
            params.add(filtro.getDataInicio());
        }

        if (filtro.getDataFinal() != null){
            query.append(" AND data <= ?");
            params.add(filtro.getDataFinal());
        }

        try (
            Connection conn = ConexaoFactory.getConexao();
            PreparedStatement pst = conn.prepareStatement(
                    query.toString(),
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            )
        ) {
            for (int i = 0; i < params.size(); i++) {
                pst.setTimestamp(i + 1, java.sql.Timestamp.valueOf(params.get(i)));
            }

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new IllegalStateException("Nenhum registro encontrado de venda.");
            }
            rs.beforeFirst();

            List<ItemVendaDTO> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVendaDTO.DadosItem dados = new ItemVendaDTO.DadosItem();
                dados.setQuantidade(rs.getInt("quantidade"));
                dados.setData(rs.getTimestamp("data").toLocalDateTime());
                dados.setValorTotal(rs.getDouble("valor_total"));

                boolean valorRepetido = false;
                for (ItemVendaDTO i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("categoria"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
                itemVendaDTO.setTitulo(rs.getString("categoria"));

                itemVendaDTO.getDados().add(dados);

                itensVendidos.add(itemVendaDTO);
            }

            return itensVendidos;
        }  catch (Exception e){
            throw new IllegalStateException(e);
        }
    }
}
