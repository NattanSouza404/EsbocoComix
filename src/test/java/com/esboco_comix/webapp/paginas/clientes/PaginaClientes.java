package com.esboco_comix.webapp.paginas.clientes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.esboco_comix.webapp.webdriver.DriverTeste;

public class PaginaClientes {

    private static String URL = "http://localhost:8080/admin/clientes.html"; 

    private WebDriver driver;
    

    public PaginaClientes(){
        this.driver = new DriverTeste();
        driver.get(URL);
        
    }

    public void fechar() throws InterruptedException{
        Thread.sleep(3000);
        driver.close();
    }

    public void consultarPorFiltro() throws InterruptedException {

        WebElement selectGenero = driver.findElement(By.name("genero"));
        
        Thread.sleep(1000);
        selectGenero.sendKeys("Feminino");
        Thread.sleep(1000);
        
        driver.findElement(By.cssSelector("#filtro-clientes > button")).click();
        Thread.sleep(1000);

        new Select(selectGenero).selectByIndex(0);
        Thread.sleep(1000);
        
        driver.findElement(By.name("nome")).sendKeys("Jorge");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#filtro-clientes > button")).click();
        Thread.sleep(1000);
    }

}
