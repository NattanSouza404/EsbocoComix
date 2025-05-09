package com.esboco_comix.webapp.paginas.cliente.carrinho;

import com.esboco_comix.webapp.base.AbstractPagina;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaCarrinho extends AbstractPagina {

    public PaginaCarrinho(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public void abrir() {
        driver.get("http://localhost:8080/carrinho");
    }
}
