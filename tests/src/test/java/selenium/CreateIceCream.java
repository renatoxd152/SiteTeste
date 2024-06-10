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
    @Test
    @DisplayName("Clica em criar novo sorvete")
    public void ClicarEmCriarNovoSorvete() throws InterruptedException {
        click(sorvetesMenuDropdown);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cadatrarSorvete));
        click(cadatrarSorvete);
    }

    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    private void click(By locator) {
        find(locator).click();
    }

}
