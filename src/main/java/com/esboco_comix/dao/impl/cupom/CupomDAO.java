package com.esboco_comix.dao.impl.cupom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.esboco_comix.dao.mapper.impl.CupomMapper;
import com.esboco_comix.model.entidades.Cupom;
import com.esboco_comix.utils.ConexaoFactory;

public class CupomDAO {

    private CupomMapper cupomMapper = new CupomMapper();

    public Cupom consultarByID(int id) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM cupons WHERE cup_id = ?;"
            );
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()){
                throw new Exception("Cupom não encontrado!");
            }
            
            return cupomMapper.mapearEntidade(rs);
        }

    }

    public List<Cupom> consultarByIDCliente(int idCliente) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "SELECT * FROM cupons WHERE cup_cli_id = ? AND cup_is_ativo ORDER BY cup_id;",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
        ) {
            pst.setInt(1, idCliente);

            ResultSet rs = pst.executeQuery();
    
            if (!rs.next()) {
                throw new Exception("Cliente não possui nenhum cupom.");
            }
            rs.beforeFirst();
    
            List<Cupom> cupons = new ArrayList<>();
            while(rs.next()){
                cupons.add(cupomMapper.mapearEntidade(rs));
            }

            return cupons;
        }
    }

    public Cupom inserir(Cupom c) throws Exception {
        try (
            Connection connection = ConexaoFactory.getConexao();

            PreparedStatement pst = connection.prepareStatement(
                "INSERT INTO cupons("+
                    "cup_cli_id, cup_is_ativo, cup_is_promocional, cup_is_troca, cup_valor) "+
                    "VALUES (?, ?, ?, ?, ?) ",
                    Statement.RETURN_GENERATED_KEYS
            );
        ) {
            pst.setInt(1, c.getIdCliente());
            pst.setBoolean(2, true);
            pst.setBoolean(3, c.isPromocional());
            pst.setBoolean(4, c.isTroca());
            pst.setDouble(5, c.getValor());
    
            if (pst.executeUpdate() == 0){
                throw new Exception("Inserção de cupom não executada!");
            }
            ResultSet rs = pst.getGeneratedKeys();
            Cupom cupomInserido = null;
            if (rs.next()){
                cupomInserido = consultarByID(rs.getInt(1));
            }

            return cupomInserido;
        }
    }

    public Cupom inativar(int id) throws Exception {
        try (
            Connection conn = ConexaoFactory.getConexao(); 
    
            PreparedStatement pst = conn.prepareStatement(
                "UPDATE cupons SET cup_is_ativo = false WHERE cup_id = ?;"
            );
        ){
            pst.setInt(1, id);

            if (pst.executeUpdate() == 0) {
                throw new Exception("Atualização não foi sucedida!");
            }

            return consultarByID(id);
        }
    }

}
