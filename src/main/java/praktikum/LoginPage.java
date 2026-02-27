package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    private WebDriverWait wait;

    @FindBy(xpath = "//label[text()='Email']/following::input[1]")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='Пароль' and @type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[normalize-space(text())='Войти']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//a[normalize-space(text())='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[normalize-space(text())='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    // Локатор для заголовка "Вход"
    @FindBy(xpath = "//h2[normalize-space(text())='Вход']")
    private WebElement loginTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Заполняем форму входа в личный кабинет")
    public void userLoginData(String email, String password) throws InterruptedException {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }

    @Step("Кликаем на кнопку 'Войти'")
    public void clickButtonLogin() {
        buttonLogin.click();
    }

    @Step("Кликаем на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        registerLink.click();
    }

    @Step("Кликаем на ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    @Step("Получаем текст заголовка 'Вход'")
    public String getLoginTitle() {
        return loginTitle.getText();
    }
    @Step("Ожидание видимости заголовка 'Вход'")
    public void waitForLoginTitle() {
        wait.until(ExpectedConditions.visibilityOf(loginTitle));
    }

}
