package praktikum;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.api.UserData;
import praktikum.api.UserRegisterRequest;
import praktikum.api.UserSteps;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    public String accessToken;
    public String token;
    public String currentUrl;
    public WebDriver driver;
    public RegisterPage registerPage;
    public Header header;
    public LoginPage loginPage;
    public ConstructorPage constructorPage;
    public ForgotPasswordPage forgotPasswordPage;
    public PersonalAccountPage personalAccountPage;
    protected boolean needRegister = true;
    private Browser browser = new Browser();
    private UserSteps steps = new UserSteps();

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        if (needRegister) {
            register();
        }

        String browserName = System.getProperty("browser", "chrome");
        driver = browser.getWebDriver(browserName);
        driver.get(Config.BASE_URL);
        initPages();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }

        if (accessToken != null) {
            steps.deleteUser(accessToken).checkDeleteUser();
        }
    }

    @Step("Инициализация страниц")
    private void initPages() {
        registerPage = new RegisterPage(driver);
        header = new Header(driver);
        loginPage = new LoginPage(driver);
        constructorPage = new ConstructorPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
    }

    @Step("Авторизация пользователя")
    public void login() throws InterruptedException {
        loginPage.userLoginData(UserData.EMAIL, UserData.PASSWORD);
        constructorPage.waitForConstructorTitle();
        assertEquals("Соберите бургер", constructorPage.getConstructorTitle(), "Заголовок должен быть 'Соберите бургер'");
    }

    @Step("Регистрация пользователя")
    private void register() throws IOException {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        accessToken = steps.registerUser(user).checkRegisterUser();
    }

    @Step("Получаем accessToken из localStorage")
    public String getAccessTokenFromLocalStorage(Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return wait.until(d -> {
            String token = (String) js.executeScript("return localStorage.getItem('accessToken');");
            return token != null ? token : null;
        });
    }

}