package com.esboco_comix.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.entidades.GrupoPrecificacao;
import com.esboco_comix.model.entidades.Quadrinho;

public class QuadrinhoMapper implements ResultSetMapper<Quadrinho, QuadrinhoDTO> {

    @Override
    public Quadrinho mapearEntidade(ResultSet rs) throws SQLException {
        Quadrinho q = new Quadrinho();
        q.setId(rs.getInt("qua_id"));

        q.setAno(rs.getDate("qua_ano").toLocalDate());
        q.setTitulo(rs.getString("qua_titulo"));
        q.setEditora(rs.getString("qua_editora"));
        q.setEdicao(rs.getInt("qua_edicao"));
        q.setIsbn(rs.getString("qua_isbn"));
        q.setNumeroPaginas(rs.getInt("qua_numero_paginas"));
        q.setSinopse(rs.getString("qua_sinopse"));
        q.setAutor(rs.getString("qua_autor"));

        q.setAltura(rs.getInt("qua_altura_cm"));
        q.setLargura(rs.getInt("qua_largura_cm"));
        q.setProfundidade(rs.getInt("qua_profundidade"));
        q.setPeso(rs.getInt("qua_peso_gramas"));
        q.setIsAtivo(rs.getBoolean("qua_is_ativo"));

        q.setCodigoBarras(rs.getString("qua_codigo_barras"));

        q.setUrlImagem(rs.getString("qua_url_imagem"));

        GrupoPrecificacao grupo = new GrupoPrecificacao();
        grupo.setId(rs.getInt("qua_gpr_id"));
        grupo.setNome(rs.getString("gpr_nome"));
        grupo.setPorcentagem(rs.getInt("gpr_porcentagem"));

        q.setGrupoPrecificacao(grupo);

        q.setCategorias(new ArrayList<>());

        return q;
    }

    @Override
    public QuadrinhoDTO mapearDTO(ResultSet rs) throws SQLException {
        QuadrinhoDTO dto = new QuadrinhoDTO();

        dto.setQuadrinho(mapearEntidade(rs));
        dto.setQuantidadeEstoque(rs.getInt("est_quantidade_total"));
        dto.setPreco(rs.getDouble("preco"));

        return dto;
    }
    
}
