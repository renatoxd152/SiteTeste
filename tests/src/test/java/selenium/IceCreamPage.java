package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IceCreamPage extends BasePage {

    public IceCreamPage(WebDriver driver) {
        super(driver);
    }

    String nomeSorvete = faker.food().ingredient();
    int quantidade = faker.number().numberBetween(1, 100);
    double preco = faker.number().randomDouble(2, 1, 50);

    By sorvetesMenuDropdown = By.xpath("//a[@id='sorvetesDropdown']");
    By cadatrarSorvete = By.xpath("//div[@id='root']//a[text()='Cadastrar Sorvetes']");
    By nomeField = By.xpath("//input[@class='form-control' and @type='text']");
    By quantidadeField = By.xpath("//input[@class='form-control' and @type='number'][1]");
    By precoField = By.xpath("//input[@class='form-control' and @type='number'][2]");
    By cadastrarButton = By.xpath("//div[@id='root']//button[text()='Cadastrar Sorvete']");
    By successMessage = By.xpath("//div[@id='root']//div[contains(text(), 'Sorvete cadastrado com sucesso!')]");
    By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");

    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void menu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sorvetesMenuDropdown));
        find(sorvetesMenuDropdown).click();
    }

    public void cadastraMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cadatrarSorvete));
        find(cadatrarSorvete).click();
    }

    public void addNome() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nomeField));
        find(nomeField).sendKeys(nomeSorvete);
    }
    public String getNome(){
        return find(cadatrarSorvete).getText();
    }
    public void addQtd() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantidadeField));
        find(quantidadeField).clear();
        find(quantidadeField).sendKeys(String.valueOf(quantidade));
        wait.until(ExpectedConditions.textToBePresentInElementValue(quantidadeField, String.valueOf(quantidade)));
    }
    public String getQuantidade() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantidadeField));
        return find(quantidadeField).getAttribute("value");
    }

    public void addPreco() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(precoField));
        find(precoField).clear();
        find(precoField).sendKeys(String.valueOf(preco));
        wait.until(ExpectedConditions.textToBePresentInElementValue(precoField, String.valueOf(preco)));
    }

    public void cadastra() {
        wait.until(ExpectedConditions.elementToBeClickable(cadastrarButton));
        find(cadastrarButton).click();
    }
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return find(successMessage).getText();
    }
    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return find(errorMessage).getText();
    }
}
