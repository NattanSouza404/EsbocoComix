package com.esboco_comix.webapp.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {

    private static final Logger logger = Logger.getLogger(DriverManager.class.getName());
    private static Path edgeProfileDir;
    private static Path firefoxProfileDir;

    public static WebDriver criaDriver() throws Exception {
        String browser = TestConfig.get("browser");

        WebDriver driver;

        switch (browser) {
            case "edge":
                driver = new EdgeDriver(criarEdgeOptions());
                break;

            default:
                driver = new FirefoxDriver(criarFirefoxOptions());
                break;
        }

        driver.manage().window().maximize();

        return driver;
    }

    public static EdgeOptions criarEdgeOptions() throws IOException {
        edgeProfileDir = Paths.get(System.getProperty("java.io.tmpdir"), "edge_profile_" + System.currentTimeMillis());

        try {
            Files.createDirectories(edgeProfileDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Edge temp profile directory: " + edgeProfileDir, e);
        }

        EdgeOptions options = new EdgeOptions();
        if (Boolean.parseBoolean(TestConfig.get("headless"))) {
            options.addArguments("--headless");
        }
        options.addArguments("--user-data-dir=" + edgeProfileDir.toString());

        return options;
    }

    public static FirefoxOptions criarFirefoxOptions() {
        firefoxProfileDir = Paths.get(System.getProperty("java.io.tmpdir"), "firefox_profile_" + System.currentTimeMillis());

        try {
            Files.createDirectories(firefoxProfileDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Firefox temp profile directory: " + firefoxProfileDir, e);
        }

        FirefoxProfile profile = new FirefoxProfile(firefoxProfileDir.toFile());
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(TestConfig.get("headless"))) {
            options.addArguments("--headless");
        }
        options.setProfile(profile);

        return options;
    }

    public static void cleanup() {
        deleteDirectory(edgeProfileDir);
        deleteDirectory(firefoxProfileDir);
    }

    private static void deleteDirectory(Path dir) {
        if (dir == null) {
            return;
        }

        try {
            Files.walk(dir)
                .sorted((a, b) -> b.compareTo(a))  // Delete files before the directory itself
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        logger.log(Level.WARNING, "Falha em deletar: " + path.toString(), e);
                    }
                });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro durante deleção de diretório temporário: " + dir.toString(), e);
        }
    }
}
