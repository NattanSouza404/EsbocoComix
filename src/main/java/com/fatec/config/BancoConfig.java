package com.fatec.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BancoConfig {
    public static final String DRIVER;
    public static final String URL;
    public static final String USER;  
    public static final String PASSWORD ;

    static {

        Properties properties = new Properties();
        
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DRIVER  = properties.getProperty("database.driver");
        URL     = properties.getProperty("database.url");
        USER    = properties.getProperty("database.user");
        PASSWORD= properties.getProperty("database.password");

        System.out.println("Carregando propriedades do banco de dados");
        System.out.println("database.url = "+URL);
        System.out.println("database.driver = "+DRIVER);
        System.out.println("database.user = "+USER);
        System.out.println("database.password = "+PASSWORD);
    }
}