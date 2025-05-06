package com.esboco_comix.webapp.utils.web_driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.esboco_comix.webapp.config.TestConfig;

public class DriverFactory {

    public static WebDriver criaDriver() {
        String browser = TestConfig.get("browser");

        WebDriver driver;

        switch (browser) {
            default:
            case "firefox":
                driver = new FirefoxDriver(criarFirefoxOptions());
                break;

            case "edge":
                driver = new EdgeDriver(criarEdgeOptions());
                break;
        }

        driver.manage().window().maximize();

        return driver;
    }

    private static EdgeOptions criarEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "edge_profile_"
                + System.currentTimeMillis());
        return options;
    }

    private static FirefoxOptions criarFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        String tempProfileDir = System.getProperty("java.io.tmpdir") + "firefox_profile_" + System.currentTimeMillis();

        FirefoxProfile profile = new FirefoxProfile(new java.io.File(tempProfileDir));
        options.setProfile(profile);

        return options;
    }
}
