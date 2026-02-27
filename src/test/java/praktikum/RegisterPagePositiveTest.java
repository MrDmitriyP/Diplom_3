package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.api.UserData;
import praktikum.api.UserSteps;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Регистрация пользователя")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterPagePositiveTest extends BaseTest {

    private final UserSteps userSteps = new UserSteps();

    private static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of("Дмитрий", UserData.EMAIL, "Qwerty"),
                Arguments.of("Дмитрий", UserData.EMAIL, "Qwertyy"),
                Arguments.of("Дмитрий", UserData.EMAIL, "QwertyQwerty123")
        );
    }

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        needRegister = false;
        super.setUp();
    }

    @ParameterizedTest(name = "Регистрация пользователя: {0}, {1}")
    @MethodSource("userData")
    @DisplayName("Пользователь регистрируется с валидными данными")
    @Description("Пользователь с валидными: именем, email, паролем проходит регистрацию после чего перенаправляется на страницу входа (/login)")
    public void registerUser(String name, String email, String password) throws InterruptedException {
        header.clickPersonalAccountLink();
        loginPage.clickRegisterLink();
        registerPage.userRegisterData(name, email, password);
        loginPage.waitForLoginTitle();
        currentUrl = driver.getCurrentUrl();
        assertEquals(Config.LOGIN_URL, currentUrl, "URL не соответствует ожидаемому");
        assertEquals("Вход", loginPage.getLoginTitle(), "Заголовок должен быть 'Вход'");
        loginPage.userLoginData(email, password);
        this.accessToken = getAccessTokenFromLocalStorage(Duration.ofSeconds(10));
        assertNotNull(accessToken, "Токен сохранён в localStorage после авторизации");
        assertTrue(accessToken.startsWith("Bearer ") || accessToken.contains("eyJ"), "Токен в формате JWT");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        super.tearDown();
    }
}