package com.esboco_comix.dao.impl.quadrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dto.FiltrarQuadrinhoDTO;
import com.esboco_comix.dto.QuadrinhoDTO;
import com.esboco_comix.model.entidades.Categoria;
import com.esboco_comix.model.entidades.GrupoPrecificacao;
import com.esboco_comix.model.entidades.Quadrinho;
import com.esboco_comix.utils.ConexaoFactory;

public class QuadrinhoDAO {

    public List<QuadrinhoDTO> consultarTodos() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
                """
                        SELECT
                            quadrinhos.*,
                            est_quantidade_total,
                            grupos_precificacao.*,
                            categorias.*
                         FROM
                            quadrinhos
                            JOIN categorias_quadrinho ON qua_id = cqu_qua_id
                            JOIN categorias ON cat_id = cqu_cat_id
                            LEFT JOIN estoque ON est_qua_id = qua_id
                            JOIN grupos_precificacao ON qua_gpr_id = gpr_id
                         ORDER BY qua_id DESC;
                        """,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de quadrinho.");
            }
            rs.beforeFirst();

            List<QuadrinhoDTO> quadrinhos = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("qua_id");

                QuadrinhoDTO dto = null;
                for (QuadrinhoDTO q: quadrinhos){
                    if (q.getQuadrinho().getId() == id){
                        dto = q;
                        break;
                    }
                }

                if (dto == null){
                    dto = mapearDTO(rs);
                    quadrinhos.add(dto);
                }

                List<Categoria> categorias = dto.getQuadrinho().getCategorias();
                categorias.add(mapearCategoria(rs));
            }

            return quadrinhos;
        } catch (Exception e) {
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<QuadrinhoDTO> filtrarTodos(FiltrarQuadrinhoDTO filtro) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        StringBuilder query = new StringBuilder("""
            SELECT * FROM quadrinhos
                JOIN grupos_precificacao ON qua_gpr_id = gpr_id
                JOIN categorias_quadrinho ON qua_id = cqu_qua_id
                JOIN categorias ON cat_id = cqu_cat_id
                LEFT JOIN estoque ON est_qua_id = qua_id
            WHERE 1=1
          """);

        List<Object> params = new ArrayList<>();

        if (filtro.getTitulo() != null) {
            query.append(" AND qua_titulo ILIKE ?");
            params.add("%" + filtro.getTitulo() + "%");
        }

        if (filtro.getAutor() != null) {
            query.append(" AND qua_autor ILIKE ?");
            params.add("%" + filtro.getAutor() + "%");
        }

        if (filtro.getEdicao() != null) {
            query.append(" AND qua_edicao = ?");
            params.add(filtro.getEdicao());
        }

        if (filtro.getEditora() != null) {
            query.append(" AND qua_editora ILIKE ?");
            params.add("%" + filtro.getEditora() + "%");
        }

        if (filtro.getNumeroPaginas() != null) {
            query.append(" AND qua_numero_paginas < ?");
            params.add(filtro.getNumeroPaginas());
        }

        if (filtro.getAltura() != null) {
            query.append(" AND qua_altura = ?");
            params.add(filtro.getAltura());
        }

        if (filtro.getLargura() != null) {
            query.append(" AND qua_largura = ?");
            params.add(filtro.getLargura());
        }

        if (filtro.getProfundidade() != null) {
            query.append(" AND qua_profundidade = ?");
            params.add(filtro.getProfundidade());
        }

        if (filtro.getPeso() != null) {
            query.append(" AND qua_peso = ?");
            params.add(filtro.getPeso());
        }

        if (filtro.getCodigoBarras() != null) {
            query.append(" AND qua_codigo_barras ILIKE ?");
            params.add("%" + filtro.getCodigoBarras() + "%");
        }

        if (filtro.getIsbn() != null) {
            query.append(" AND qua_isbn ILIKE ?");
            params.add("%" + filtro.getIsbn() + "%");
        }

        query.append(" ORDER BY qua_id DESC;");

        PreparedStatement pst = conn.prepareStatement(
                query.toString(),
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        try {

            for (int i = 0; i < params.size(); i++) {

                if (params.get(i) instanceof Enum){
                    pst.setObject(i + 1, ((Enum<?>) params.get(i)).name());
                    continue;
                }

                pst.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de quadrinho.");
            }
            rs.beforeFirst();

            List<QuadrinhoDTO> quadrinhos = new ArrayList<>();

            while (rs.next()){
                QuadrinhoDTO dto = null;

                int id = rs.getInt("qua_id");
                for (QuadrinhoDTO q: quadrinhos){
                    if (q.getQuadrinho().getId() == id){
                        dto = q;
                        break;
                    }
                }

                if (dto == null){
                    dto = mapearDTO(rs);
                    quadrinhos.add(dto);
                }

                List<Categoria> categorias = dto.getQuadrinho().getCategorias();
                categorias.add(mapearCategoria(rs));
            }

            return quadrinhos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public QuadrinhoDTO consultarByID(int id) throws Exception {
        Connection connection = ConexaoFactory.getConexao();

        PreparedStatement pst = connection.prepareStatement(
            """
            SELECT quadrinhos.*, est_quantidade_total, grupos_precificacao.*, categorias.* FROM quadrinhos
                JOIN categorias_quadrinho ON qua_id = cqu_qua_id
                JOIN categorias ON cat_id = cqu_cat_id
                LEFT JOIN estoque ON est_qua_id = qua_id
                JOIN grupos_precificacao ON qua_gpr_id = gpr_id
            WHERE qua_id = ?;
            """
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Quadrinho não encontrado!");
            }

            QuadrinhoDTO dto = null;

            while (rs.next()) {
                if (dto == null){
                    dto = mapearDTO(rs);
                }

                dto.getQuadrinho().getCategorias().add(mapearCategoria(rs));
            }

            return dto;
        } catch (Exception e) {
            throw e;
        } finally {
            connection.close();
            pst.close();
        }
    }

    private Quadrinho mapearEntidade(ResultSet rs) throws SQLException {
        Quadrinho q = new Quadrinho();
        q.setId(rs.getInt("qua_id"));

        q.setAno(rs.getDate("qua_ano").toLocalDate());
        q.setTitulo(rs.getString("qua_titulo"));
        q.setEditora(rs.getString("qua_editora"));
        q.setEdicao(rs.getInt("qua_edicao"));
        q.setIsbn(rs.getString("qua_isbn"));
        q.setNumeroPaginas(rs.getInt("qua_numero_paginas"));
        q.setSinopse(rs.getString("qua_sinopse"));
        q.setPreco(rs.getDouble("qua_preco"));
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

    private QuadrinhoDTO mapearDTO(ResultSet rs) throws SQLException {
        QuadrinhoDTO dto = new QuadrinhoDTO();

        dto.setQuadrinho(mapearEntidade(rs));

        int quantidadeEstoque = rs.getInt("est_quantidade_total");
        boolean isForaDeEstoque = quantidadeEstoque == 0;

        dto.setQuantidadeEstoque(quantidadeEstoque);
        dto.setForaDeEstoque(isForaDeEstoque);

        return dto;
    }

    private Categoria mapearCategoria(ResultSet rs) throws Exception {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("cat_id"));
        categoria.setNome(rs.getString("cat_nome"));

        return categoria;
    }

}
