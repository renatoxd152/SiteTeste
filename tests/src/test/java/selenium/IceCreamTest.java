package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
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
        String successMessage = iceCreamPage.getSuccessMessage();
        assertEquals("Sorvete cadastrado com sucesso!", successMessage);
    }
    @Test
    @DisplayName("Tenta cadastrar sorvete com campos vazios")
    public void CadastrarSorveteComCamposVazios() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();
        iceCreamPage.cadastra();

        String errorMessage = iceCreamPage.getErrorMessage();
        assertEquals("Por favor, preencha todos os campos.", errorMessage);
    }
    @Test
    @DisplayName("Tenta cadastrar sorvete com campo 'nome' vazio")
    public void CadastrarSorveteComCampoNomeVazio() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();
        iceCreamPage.addQtd();
        iceCreamPage.addPreco();
        iceCreamPage.cadastra();

        assertTrue(iceCreamPage.getNome().isEmpty());
    }
    @Test
    @DisplayName("Tenta cadastrar sorvete com campo 'quantidade' vazio")
    public void CadastrarSorveteComCampoQuantidadeVazio() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();
        iceCreamPage.addNome();
        iceCreamPage.addPreco();
        iceCreamPage.cadastra();

        assertTrue(iceCreamPage.getQuantidade().isEmpty());
    }
    @Test
    @DisplayName("Tenta cadastrar sorvete com campo 'preço' vazio")
    public void CadastrarSorveteComCampoPrecoVazio() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();
        iceCreamPage.addNome();
        iceCreamPage.addQtd();
        iceCreamPage.cadastra();

        assertTrue(iceCreamPage.getPreco().isEmpty());
    }
    @Test
    @DisplayName("Tentar criar sorvete com nome duplicado")
    public void criarSorveteComNomeDuplicado() {
        iceCreamPage.menu();
        iceCreamPage.cadastraMenu();

        iceCreamPage.setNome("Sorvete Teste");
        iceCreamPage.setQuantidade("10");
        iceCreamPage.setPreco("5.00");
        iceCreamPage.cadastra();

        iceCreamPage.setNome("Sorvete Teste");
        iceCreamPage.setQuantidade("15");
        iceCreamPage.setPreco("7.50");
        iceCreamPage.cadastra();


        String errorMessage = iceCreamPage.getErrorMessage();
        assertEquals("Já existe um sorvete Sorvete Teste", errorMessage);
    }

    @Test
    @DisplayName("Clica no botão de atualizar do primeiro sorvete da lista e verifica se a página de edição foi carregada")
    public void clicarPrimeiroAtualizar() {
        iceCreamPage.salvarSorveteNoLocalStorage("Nome" ,"5", "48");
        driver.navigate().refresh();
        iceCreamPage.clicarPrimeiroAtualizar();

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/sorvete/0"), "A URL atual deve conter /sorvete/0");
    }


}
