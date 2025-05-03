package com.esboco_comix.dao.impl.quadrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esboco_comix.model.entidades.Categoria;
import com.esboco_comix.model.entidades.CategoriaQuadrinho;
import com.esboco_comix.utils.ConexaoFactory;

public class CategoriaDAO {

    public Map<Integer, List<Categoria>> consultarTodos() throws Exception{
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            """ 
            SELECT * FROM categorias;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de categoria.");
            }
            rs.beforeFirst();

            List<Categoria> todasCategorias = new ArrayList<>();

            while (rs.next()){
                todasCategorias.add(mapearEntidade(rs));
            }

            Map<Integer, List<Categoria>> map = new HashMap<>();

            for (CategoriaQuadrinho cq : consultarTodosCategorias()) {
                int idQuadrinho = cq.getIdQuadrinho();

                List<Categoria> lista = map.get(idQuadrinho) == null ?
                    new ArrayList<>() : map.get(idQuadrinho);
                    
                for (Categoria c : todasCategorias) {
                    if (c.getId() == cq.getIdCategoria()){
                        lista.add(c);
                    }
                }

                if (map.get(idQuadrinho) == null){
                    map.put(idQuadrinho, lista);
                }
            }

            return map;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<CategoriaQuadrinho> consultarTodosCategorias() throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            """ 
            SELECT * FROM categorias_quadrinho;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            ResultSet rs = pst.executeQuery();
            
            if (!rs.next()) {
                throw new Exception("Nenhum registro encontrado de categoria.");
            }
            rs.beforeFirst();

            List<CategoriaQuadrinho> categoriaQuadrinhos = new ArrayList<>();

            while (rs.next()){
                CategoriaQuadrinho c = new CategoriaQuadrinho();
                c.setIdCategoria(rs.getInt("cqu_cat_id"));
                c.setIdQuadrinho(rs.getInt("cqu_qua_id"));
                categoriaQuadrinhos.add(c);
            }

            return categoriaQuadrinhos;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    public List<Categoria> consultarByIDQuadrinho(int id) throws Exception {
        Connection conn = ConexaoFactory.getConexao();

        PreparedStatement pst = conn.prepareStatement(
            """
            SELECT categorias.* FROM categorias
                JOIN categorias_quadrinho ON cat_id = cqu_cat_id
                JOIN quadrinhos ON cqu_qua_id = qua_id
            WHERE qua_id = ?;
            """,
            ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_READ_ONLY
        );

        try {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return new ArrayList<>();
            }
            rs.beforeFirst();

            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()){
                categorias.add(mapearEntidade(rs));
            }

            return categorias;
        }
        catch (Exception e){
            throw e;
        } finally {
            pst.close();
            conn.close();
        }
    }

    private Categoria mapearEntidade(ResultSet rs) throws Exception {
        Categoria categoria = new Categoria();

        categoria.setId(rs.getInt("cat_id"));
        categoria.setNome(rs.getString("cat_nome"));

        return categoria;
    }

}
