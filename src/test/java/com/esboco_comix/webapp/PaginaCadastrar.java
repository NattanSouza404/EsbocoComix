package webapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.esboco_comix.model.entidades.Cliente;

public class PaginaCadastrar {

    private static String URL = "http://localhost:8080/cadastrar.html";  

    public void preencherCliente(Cliente c) throws InterruptedException{
        WebDriver driver = new EdgeDriver();
        driver.get(URL);

        WebElement form = getFormularioDadosPessoais(driver);
        WebElement input = form.findElement(By.name("nome"));
        input.sendKeys(c.getNome());

        driver.close();
    }

    public WebElement getFormularioDadosPessoais(WebDriver driver){
        return driver.findElement(By.id("form-cadastrar-dados-pessoais"));
    }
}
