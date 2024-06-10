package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CreateIceCream {

    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("site-teste-renatoxd152s-projects.vercel.app");
    }

    @AfterAll
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }
}
