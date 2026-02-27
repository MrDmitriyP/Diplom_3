package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Негативные сценарии регистрации пользователя")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterPageNegativeTest extends BaseTest {

    private static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of("Дмитрий", "test+" + UUID.randomUUID() + "a@test.ru", "Qwert"),
                Arguments.of("Дмитрий", "test+" + UUID.randomUUID() + "b@test.ru", "Qwer"),
                Arguments.of("Дмитрий", "test+" + UUID.randomUUID() + "c@test.ru", "Q")
        );
    }

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        needRegister = false;
        super.setUp();
    }

    @ParameterizedTest(name = "Регистрация с паролем: {2} (слишком короткий)")
    @MethodSource("userData")
    @DisplayName("Нельзя регистрироваться с паролем меньше 6 символов")
    @Description("Система блокирует регистрацию и отображает ошибку 'Некорректный пароль', если длина пароля меньше 6 символов")
    public void registerUser(String name, String email, String password) {
        header.clickPersonalAccountLink();
        loginPage.clickRegisterLink();
        registerPage.userRegisterData(name, email, password);
        assertTrue(registerPage.isPasswordErrorDisplayed(), "Не отображается сообщение об ошибке 'Некорректный пароль'");
        assertEquals("Некорректный пароль", registerPage.getPasswordErrorMassage(), "Сообщение об ошибке должно быть 'Некорректный пароль'");
    }
}