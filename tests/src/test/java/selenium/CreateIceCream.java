package selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateIceCream extends BaseTest{

    //Opções de By
    By sorvetesMenuDropdown = By.xpath("//a[@id='sorvetesDropdown']");
    By cadatrarSorvete = By.xpath("//div[@id='root']//a[text()='Cadastrar Sorvetes']");
    By nomeField = By.xpath("//input[@class='form-control' and @type='text']");
    By quantidadeField = By.xpath("//input[@class='form-control' and @type='number'][1]");
    By precoField = By.xpath("//input[@class='form-control' and @type='number'][2]");
    By cadastrarButton = By.xpath("//div[@id='root']//button[text()='Cadastrar Sorvete']");
    @Test
    @DisplayName("Clica em criar novo sorvete")
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

        find(precoField).clear();
        find(precoField).sendKeys(String.valueOf(preco));
        //Thread.sleep(4000);
        click(cadastrarButton);
    }

    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    private void click(By locator) {
        find(locator).click();
    }

}
