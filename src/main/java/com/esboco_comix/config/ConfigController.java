package com.esboco_comix.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigController {
    public static final String CHATBOT_URL;

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

        CHATBOT_URL = get("CHATBOT_URL", "chatbot.url");
        
        System.out.println("Carregando propriedades:");
        System.out.println("chatbot.url = "+CHATBOT_URL);
    }

    private static String get(String envName, String propertiesName){
        String env = System.getenv(envName);

        return
            env != null
            ? env
            : properties.getProperty(propertiesName);
    }
}
