package com.esboco_comix.webapp.testes;

import java.time.LocalDate;

import org.junit.Test;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FiltrarAnalisePorData;

public class AnaliseTest extends BaseTest {

    @Test
    public void filtrarAnalisePorData(){
        try {
            FiltrarAnalisePorData fluxo = new FiltrarAnalisePorData(driver, wait);

            LocalDate dataInicial = LocalDate.of(2025, 6, 1);
            LocalDate dataFinal = LocalDate.of(2025, 6, 16);

            fluxo.mostrarPaginaAnalise();
            fluxo.filtrarPorData(dataInicial, dataFinal);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
