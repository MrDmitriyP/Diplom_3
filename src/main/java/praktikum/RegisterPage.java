package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

    private WebDriverWait wait;

    @FindBy(xpath = "//label[text()='Имя']/following::input[1]")
    private WebElement inputName;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='Пароль' and @type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[normalize-space(text())='Зарегистрироваться']")
    private WebElement buttonRegister;

    @FindBy(xpath = "//a[normalize-space(text())='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//p[text()='Некорректный пароль']")
    private WebElement passwordErrorMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Заполняем форму регистрации")
    public void userRegisterData(String name, String email, String password) {
        inputName.sendKeys(name);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonRegister.click();
    }

    @Step("Кликаем на ссылку 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Проверяем, отображается ли сообщение об ошибке под полем пароля")
    public boolean isPasswordErrorDisplayed() {
        return passwordErrorMessage.isDisplayed();
    }

    @Step("Получаем текст сообщения об ошибке")
    public String getPasswordErrorMassage() {
        return passwordErrorMessage.getText();
    }
}
