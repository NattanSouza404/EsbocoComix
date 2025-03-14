package com.esboco_comix.webapp.webdriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverTeste extends EdgeDriver {

    private static EdgeOptions criarEdgeOptions(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "edge_profile_" + System.currentTimeMillis());
        return options;
    }

    public DriverTeste(){
        super(criarEdgeOptions());
    }
}
