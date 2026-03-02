package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Работа с профилем и выход из аккаунта")
public class PersonalAccountPageTest extends BaseTest {

    @Test
    @DisplayName("Пользователь переходит в личный кабинет")
    @Description("После авторизации пользователь нажимает на «Личный кабинет» — открывается страница профиля с кнопками: Профиль, История заказов, Выход, и текстом о редактировании данных")
    public void logInPersonalAccount() throws InterruptedException {
        header.clickPersonalAccountLink();
        login();
        header.clickPersonalAccountLink();
        personalAccountPage.waitForTitleProfile();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.PROFILE_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Профиль", personalAccountPage.getButtonProfile(), "На странице кнопка 'Профиль'");
        assertEquals("История заказов", personalAccountPage.getLinkHistoryOrders(), "На странице ссылка 'История заказов'");
        assertEquals("Выход", personalAccountPage.getButtonExit(), "На странице кнопка 'Выход");
        assertEquals("В этом разделе вы можете изменить свои персональные данные", personalAccountPage.getProfileInfoText(), "На странице текст 'В этом разделе вы можете изменить свои персональные данные'");
    }

    @Test
    @DisplayName("Пользователь выходит из аккаунта")
    @Description("В личном кабинете пользователь нажимает «Выход» — происходит выход, перенаправление на страницу входа с заголовком 'Вход'")
    public void logOutOfPersonalAccount() throws InterruptedException {
        header.clickPersonalAccountLink();
        login();
        header.clickPersonalAccountLink();
        personalAccountPage.waitForButtonExit();
        personalAccountPage.clickButtonExit();
        loginPage.waitForLoginTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Вход", loginPage.getLoginTitle(), "Заголовок должен быть 'Вход'");
    }
}