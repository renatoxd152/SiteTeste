package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    double precoNegativo = faker.number().randomDouble(2, -50, -1);
    int quantidadeNegativa = faker.number().numberBetween(-100, -1);

    By sorvetesMenuDropdown = By.xpath("//a[@id='sorvetesDropdown']");
    By cadatrarSorvete = By.xpath("//div[@id='root']//a[text()='Cadastrar Sorvetes']");
    By nomeField = By.xpath("//input[@class='form-control' and @type='text']");
    By quantidadeField = By.xpath("//input[@class='form-control' and @type='number'][1]");
    By precoField = By.xpath("//input[@class='form-control' and @type='number'][2]");
    By cadastrarButton = By.xpath("//div[@id='root']//button[text()='Cadastrar Sorvete']");
    By successMessage = By.xpath("//div[@id='root']//div[contains(text(), 'Sorvete cadastrado com sucesso!')]");
    By errorMessage = By.xpath("//div[contains(@class, 'alert-danger')]");
    By primeiroAtualizarButton = By.xpath("//table[@class='table']//tbody//tr[1]//a[@class='btn btn-primary']");
    By primeiroExcluirButton = By.xpath("//table[@class='table']//tbody//tr[1]//button[@class='btn btn-danger']");
    By excluidoSuccess = By.xpath("//div[contains(@class, 'alert-success')]");

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
    public String getPreco() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(precoField));
        return find(precoField).getAttribute("value");
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
    public void setNome(String nome) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nomeField));
        find(nomeField).clear();
        find(nomeField).sendKeys(nome);
    }

    public void setQuantidade(String quantidade) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantidadeField));
        find(quantidadeField).clear();
        find(quantidadeField).sendKeys(quantidade);
    }

    public void setPreco(String preco) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(precoField));
        find(precoField).clear();
        find(precoField).sendKeys(preco);
    }

    public void salvarSorveteNoLocalStorage(String nome, String quantidade, String preco) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = String.format(
                "var sorvetes = JSON.parse(localStorage.getItem('sorvetes')) || [];" +
                        "sorvetes.push({ nome: '%s', quantidade: '%s', preco: '%s' });" +
                        "localStorage.setItem('sorvetes', JSON.stringify(sorvetes));",
                nome, quantidade, preco
        );
        js.executeScript(script);
    }

    public void clicarPrimeiroAtualizar() {
        wait.until(ExpectedConditions.elementToBeClickable(primeiroAtualizarButton));
        find(primeiroAtualizarButton).click();
    }

    public void clicarPrimeiroExcluir() {
        wait.until(ExpectedConditions.elementToBeClickable(primeiroExcluirButton));
        find(primeiroExcluirButton).click();
    }

    public String getExcluirMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(excluidoSuccess));
        return find(excluidoSuccess).getText();
    }

    public void addPrecoNeg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(precoField));
        find(precoField).clear();
        find(precoField).sendKeys(String.valueOf(precoNegativo));
        wait.until(ExpectedConditions.textToBePresentInElementValue(precoField, String.valueOf(precoNegativo)));
    }

    public void addQtdNeg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantidadeField));
        find(quantidadeField).clear();
        find(quantidadeField).sendKeys(String.valueOf(quantidadeNegativa));
        wait.until(ExpectedConditions.textToBePresentInElementValue(quantidadeField, String.valueOf(quantidadeNegativa)));
    }
}
