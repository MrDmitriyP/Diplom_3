package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//h1[normalize-space(text())='Соберите бургер']")
    private WebElement constructorTitle;

    @FindBy(xpath = "//span[normalize-space(text())='Булки']")
    private WebElement tabBun;

    @FindBy(xpath = "//span[normalize-space(text())='Соусы']")
    private WebElement tabSauce;

    @FindBy(xpath = "//span[normalize-space(text())='Начинки']")
    private WebElement tabFilling;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current__2BEPc')]")
    private WebElement tabIngredients;

    @FindBy(xpath = "//button[normalize-space(text())='Войти в аккаунт']")
    private WebElement buttonLogin;

    public ConstructorPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Получаем текст заголовка 'Соберите бургер'")
    public String getConstructorTitle() {
        return constructorTitle.getText();
    }

    @Step("Ожидание видимости заголовка 'Соберите бургер'")
    public void waitForConstructorTitle() {
        wait.until(ExpectedConditions.visibilityOf(constructorTitle));
    }

    @Step("Получаем текст активного таба")
    public String getTabIngredients() {
        return wait.until(ExpectedConditions.visibilityOf(tabIngredients)).getText();
    }

    @Step("Ожидание, что вкладка '{tabName}' активна")
    private void waitForTabToBeActive(String tabName) {
        wait.until(d -> getTabIngredients().equals(tabName));
    }

    @Step("Кликаем по табу 'Булки'")
    public void clickTabBun(){
        tabBun.click();
        waitForTabToBeActive("Булки");
    }

    @Step("Кликаем по табу 'Соусы'")
    public void clickTabSauce(){
        tabSauce.click();
        waitForTabToBeActive("Соусы");

    }

    @Step("Кликаем по табу 'Начинки'")
    public void clickTabFilling(){
        tabFilling.click();
        waitForTabToBeActive("Начинки");
    }

    @Step("Кликаем по кнопке 'Войти в аккаунт'")
    public void clickButtonLogin() {
        buttonLogin.click();
    }
}
