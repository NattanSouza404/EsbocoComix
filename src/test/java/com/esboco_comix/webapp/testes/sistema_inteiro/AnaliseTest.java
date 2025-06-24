package com.esboco_comix.webapp.testes.sistema_inteiro;

import java.time.LocalDate;

import org.junit.Test;

import com.esboco_comix.webapp.base.BaseTest;
import com.esboco_comix.webapp.casos_de_uso.FiltrarAnalisePorData;

public class AnaliseTest extends BaseTest {

    @Test
    public void filtrarAnalisePorData(){
        try {
            FiltrarAnalisePorData fluxo = new FiltrarAnalisePorData(driver, wait);

            fluxo.abrirPaginaAnalise();

            fluxo.colocarDataInicio(LocalDate.of(2025, 6, 1));
            fluxo.colocarDataFinal(LocalDate.of(2025, 6, 16));

            fluxo.filtrarPorData();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
