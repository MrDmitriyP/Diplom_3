package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Работа с хедером")
public class HeaderTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет на страницу авторизации")
    @Description("Клик по ссылке «Личный кабинет» в хедере перенаправляет на страницу входа с URL /login и заголовком 'Вход'")
    public void openLoginPage() {
        header.clickPersonalAccountLink();
        loginPage.waitForLoginTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Вход", loginPage.getLoginTitle(), "Заголовок должен быть 'Вход'");
    }

    @Test
    @DisplayName("Из личного кабинета можно перейти в конструктор при нажатии на «Конструктор»")
    @Description("После авторизации пользователь переходит в личный кабинет, затем нажимает на «Конструктор» — должен перейти на главную страницу c заголовком 'Соберите бургер'")
    public void openConstructorFromPersonalAccount() throws InterruptedException {
        header.clickPersonalAccountLink();
        login();
        header.clickPersonalAccountLink();
        header.clickConstructorLink();
        constructorPage.waitForConstructorTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Соберите бургер", constructorPage.getConstructorTitle(), "Заголовок должен быть 'Соберите бургер'");
    }

    @Test
    @DisplayName("Из личного кабинета можно вернуться в конструктор по клику на логотип")
    @Description("После авторизации пользователь в личном кабинете кликает на логотип Stellar Burgers — должен вернуться на главную страницу с заголовком 'Соберите бургер'")
    public void openConstructorByClickOnLogo() throws InterruptedException {
        header.clickPersonalAccountLink();
        login();
        header.clickPersonalAccountLink();
        header.clickLogoStellarBurgers();
        constructorPage.waitForConstructorTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.BASE_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Соберите бургер", constructorPage.getConstructorTitle(), "Заголовок должен быть 'Соберите бургер'");
    }
}