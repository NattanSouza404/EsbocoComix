package com.esboco_comix.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BancoConfig {
    public static final String DRIVER;
    public static final String URL;
    public static final String USER;  
    public static final String PASSWORD ;

    static {
        Properties properties = new Properties();

        try (InputStream input = BancoConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null){
                throw new RuntimeException("config.properties não encontrado!");
            }
            
            properties.load(input);
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        DRIVER  = properties.getProperty("database.driver");

        String dbUrl = System.getenv("DB_URL") != null 
            ? System.getenv("DB_URL")
            : properties.getProperty("database.url");

        URL     = dbUrl;
        USER    = properties.getProperty("database.user");
        PASSWORD= properties.getProperty("database.password");

        System.out.println("Carregando propriedades do banco de dados");
        System.out.println("database.url = "+URL);
        System.out.println("database.driver = "+DRIVER);
        System.out.println("database.user = "+USER);
        System.out.println("database.password = "+PASSWORD);
    }
}