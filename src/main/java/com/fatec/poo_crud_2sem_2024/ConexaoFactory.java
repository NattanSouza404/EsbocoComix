package com.fatec.poo_crud_2sem_2024;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fatec.poo_crud_2sem_2024.config.BancoConfig;

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
