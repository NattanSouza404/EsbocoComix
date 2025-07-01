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

    public void mostrarPaginaAnalise() throws InterruptedException{
        paginaAnalise.abrir();
        paginaAnalise.scrollarGraficos();
    }

    public void filtrarPorData(LocalDate dataInicial, LocalDate dataFinal) throws InterruptedException {
        paginaAnalise.abrir();

        paginaAnalise.colocarDataInicio(dataInicial);
        paginaAnalise.colocarDataFinal(dataFinal);

        paginaAnalise.filtrarPorData();
        paginaAnalise.scrollarGraficos();
    }
}
