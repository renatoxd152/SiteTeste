package selenium;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;

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

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        faker = new Faker();
    }

    @AfterEach
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }

    //Opções de By
    By sorvetesMenuDropdown = By.xpath("//a[@id='sorvetesDropdown']");
    By cadatrarSorvete = By.xpath("//div[@id='root']//a[text()='Cadastrar Sorvetes']");
    By nomeField = By.xpath("//input[@class='form-control' and @type='text']");
    By quantidadeField = By.xpath("//input[@class='form-control' and @type='number'][1]");
    By precoField = By.xpath("//input[@class='form-control' and @type='number'][2]");
    By cadastrarButton = By.xpath("//div[@id='root']//button[text()='Cadastrar Sorvete']");
    @Test
    @DisplayName("Clica em criar novo sorvete e insere um sorvete valido")
    public void ClicarEmCriarNovoSorvete() throws InterruptedException {
        click(sorvetesMenuDropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cadatrarSorvete));
        click(cadatrarSorvete);

        String nomeSorvete = faker.food().ingredient();
        int quantidade = faker.number().numberBetween(1, 100);
        double preco = faker.number().randomDouble(2, 1, 50);

        wait.until(ExpectedConditions.visibilityOfElementLocated(nomeField));
        find(nomeField).sendKeys(nomeSorvete);

        find(quantidadeField).clear();
        find(quantidadeField).sendKeys(String.valueOf(quantidade));
        wait.until(ExpectedConditions.textToBePresentInElementValue(quantidadeField, String.valueOf(quantidade)));

        find(precoField).clear();
        find(precoField).sendKeys(String.valueOf(preco));
        wait.until(ExpectedConditions.textToBePresentInElementValue(precoField, String.valueOf(preco)));

        click(cadastrarButton);
    }



    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    private void click(By locator) {
        find(locator).click();
    }

}
