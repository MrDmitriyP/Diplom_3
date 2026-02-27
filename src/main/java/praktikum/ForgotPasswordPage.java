package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage extends BasePage{

    private WebDriverWait wait;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    private WebElement inputEmail;

    @FindBy(xpath = "//button[normalize-space(text())='Восстановить']")
    private WebElement buttonRestore;

    @FindBy(xpath = "//a[normalize-space(text())='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Заполняем форму восстановления пароля")
    public void userForgotPasswordData(String password) {
        inputEmail.sendKeys(password);
        buttonRestore.click();
    }

    @Step("Кликаем на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }
}
