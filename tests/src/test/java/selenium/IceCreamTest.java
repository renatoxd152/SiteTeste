package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IceCreamTest {
    private WebDriver driver;
    private IceCreamPage iceCreamPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://site-teste-renatoxd152s-projects.vercel.app");
        iceCreamPage = new IceCreamPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Clica em criar novo sorvete e insere um sorvete válido")
    public void ClicarEmCriarNovoSorvete() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();
        iceCreamPage.addNome();
        iceCreamPage.addQtd();
        iceCreamPage.addPreco();
        iceCreamPage.cadastra();
    }
}
