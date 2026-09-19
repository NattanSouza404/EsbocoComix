package com.esboco_comix.analise.dao;

import com.esboco_comix.analise.dominio.ItemVenda;
import com.esboco_comix.core.dao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnaliseDAO {
    public List<ItemVenda> consultarProdutos(
        LocalDateTime dataInicio,
        LocalDateTime dataFinal
    ) {
        StringBuilder query = new StringBuilder(
            """
            SELECT * FROM vw_analise_produtos WHERE 1 = 1
            """
        );

        List<LocalDateTime> params = new ArrayList<>();

        if (dataInicio != null){
            query.append(" AND data >= ?");
            params.add(dataInicio);
        }

        if (dataFinal != null){
            query.append(" AND data <= ?");
            params.add(dataFinal);
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

            List<ItemVenda> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVenda.DadosItem dados = new ItemVenda.DadosItem();
                dados.setQuantidade(rs.getInt("quantidade"));
                dados.setData(rs.getTimestamp("data").toLocalDateTime());
                dados.setValorTotal(rs.getDouble("valor_total"));

                boolean valorRepetido = false;
                for (ItemVenda i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("titulo_quadrinho"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setTitulo(rs.getString("titulo_quadrinho"));

                itemVenda.getDados().add(dados);

                itensVendidos.add(itemVenda);
            }

            return itensVendidos;
        } catch (Exception e){
            throw new IllegalStateException(e);
        }
    }

    public List<ItemVenda> consultarCategorias(LocalDateTime dataInicio, LocalDateTime dataFinal) {
        StringBuilder query = new StringBuilder( 
            """
            SELECT * FROM vw_analise_categorias WHERE 1 = 1
            """
        );

        List<LocalDateTime> params = new ArrayList<>();

        if (dataInicio != null){
            query.append(" AND data >= ?");
            params.add(dataInicio);
        }

        if (dataFinal != null){
            query.append(" AND data <= ?");
            params.add(dataFinal);
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

            List<ItemVenda> itensVendidos = new ArrayList<>();

            while (rs.next()){

                ItemVenda.DadosItem dados = new ItemVenda.DadosItem();
                dados.setQuantidade(rs.getInt("quantidade"));
                dados.setData(rs.getTimestamp("data").toLocalDateTime());
                dados.setValorTotal(rs.getDouble("valor_total"));

                boolean valorRepetido = false;
                for (ItemVenda i: itensVendidos){
                    if (i.getTitulo().equals(rs.getString("categoria"))){
                        valorRepetido = true;

                        i.getDados().add(dados);
                        break;
                    }
                }

                if (valorRepetido){
                    continue;
                }

                ItemVenda itemVendaDTO = new ItemVenda();
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
