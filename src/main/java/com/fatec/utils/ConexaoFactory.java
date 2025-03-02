package com.fatec.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fatec.config.BancoConfig;

public class ConexaoFactory {

    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        Class.forName(BancoConfig.DRIVER);

        return DriverManager.getConnection(
            BancoConfig.URL,
            BancoConfig.USER,
            BancoConfig.PASSWORD
        );
    }

}
