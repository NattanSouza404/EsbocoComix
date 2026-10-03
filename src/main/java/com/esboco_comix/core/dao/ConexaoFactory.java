package com.esboco_comix.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.esboco_comix.core.config.BancoConfig;

public class ConexaoFactory {

    public static Connection getConexao() {
        try {
            Class.forName(BancoConfig.DRIVER);

            return DriverManager.getConnection(
                BancoConfig.URL,
                BancoConfig.USER,
                BancoConfig.PASSWORD
            );
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver não encontrado!", e);
        } catch (SQLException e) {
            throw new IllegalStateException("Erro ao conectar com o banco de dados!", e);
        }        
    }

}
