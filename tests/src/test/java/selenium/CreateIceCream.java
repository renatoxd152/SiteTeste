package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class CreateIceCream {

    protected static WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://site-teste-renatoxd152s-projects.vercel.app");
    }

    @Test
    @DisplayName("Clica em criar novo sorvete")
    public void ClicarEmCriarNovoSorvete() throws InterruptedException {
        driver.findElement(By.xpath("//a[@id='sorvetesDropdown']")).click();
        //Thread.sleep(5000);
    }


    @AfterAll
    public static void tearDown(){
        if (driver != null)
            driver.quit();
    }
}
