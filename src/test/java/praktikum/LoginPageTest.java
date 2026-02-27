package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Вход в аккаунт")
public class LoginPageTest extends BaseTest {

    @Test
    @DisplayName("Вход через «Войти в аккаунт» на главной странице")
    @Description("Пользователь нажимает кнопку 'Войти в аккаунт' на главной — переходит на /login, вводит данные, авторизуется и попадает в конструктор")
    public void loginClickOnTheLoginButton() throws InterruptedException {
        constructorPage.clickButtonLogin();
        loginPage.waitForLoginTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        login();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Вход через «Личный кабинет» в хедере")
    @Description("Пользователь нажимает по «Личному кабинету» — переходит на /login, вводит данные, авторизуется и попадает в конструктор")
    public void loginViaPersonalAccountButton() throws InterruptedException {
        header.clickPersonalAccountLink();
        loginPage.waitForLoginTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        login();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Вход по ссылке «Войти»")
    @Description("Пользователь переходит на страницу регистрации, нажимает по ссылке 'Войти' — переходит на /login, вводит данные, авторизуется и попадает в конструктор")
    public void loginFromRegistrationForm() throws InterruptedException {
        header.clickPersonalAccountLink();
        loginPage.clickRegisterLink();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.REGISTER_URL, currentUrl, "URL не соответствует ожидаемому");
        registerPage.clickLoginLink();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        login();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Вход через восстановление пароля по ссылке «Войти»")
    @Description("Пользователь переходит на страницу восстановления пароля, нажимает по ссылке 'Войти' — переходит на /login, вводит данные, авторизуется и попадает в конструктор")
    public void loginFromPasswordForgotForm() throws InterruptedException {
        header.clickPersonalAccountLink();
        loginPage.clickForgotPasswordLink();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.FORGOT_URL, currentUrl, "URL не соответствует ожидаемому");
        forgotPasswordPage.clickLoginLink();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        login();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
    }
}