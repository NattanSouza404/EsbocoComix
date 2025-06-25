package com.esboco_comix.webapp.casos_de_uso;

import java.time.LocalDate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.esboco_comix.webapp.paginas.admin.analise.PaginaAnalise;

public class FiltrarAnalisePorData {
    private PaginaAnalise paginaAnalise;
    
    public FiltrarAnalisePorData(WebDriver driver, WebDriverWait wait){
        paginaAnalise = new PaginaAnalise(driver, wait);
    }

    public void abrirPaginaAnalise() throws InterruptedException{
        paginaAnalise.abrir();
    }

    public void colocarDataInicio(LocalDate data) throws InterruptedException {
        paginaAnalise.colocarDataInicio(data);
    }

    public void colocarDataFinal(LocalDate data) throws InterruptedException {
        paginaAnalise.colocarDataFinal(data);
    }

    public void filtrarPorData() throws InterruptedException {
        paginaAnalise.filtrarPorData();
    }
}
