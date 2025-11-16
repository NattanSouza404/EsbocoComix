package com.esboco_comix.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BancoConfig {
    public static final String DRIVER;
    public static final String URL;
    public static final String USER;  
    public static final String PASSWORD;

    private static Properties properties = new Properties();

    static {
        try (InputStream input = BancoConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input != null){
                properties.load(input);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        DRIVER = get("DB_DRIVER", "database.driver");
        URL = get("DB_URL", "database.url");
        USER = get("POSTGRES_USER", "database.user");
        PASSWORD = get("POSTGRES_PASSWORD", "database.password");
        
        System.out.println("Carregando propriedades do banco de dados");
        System.out.println("database.url = "+URL);
        System.out.println("database.driver = "+DRIVER);
        System.out.println("database.user = "+USER);
        System.out.println("database.password = []");
    }

    private static String get(String envName, String propertiesName){
        String env = System.getenv(envName);

        return
            env != null
            ? env
            : properties.getProperty(propertiesName);
    }
}