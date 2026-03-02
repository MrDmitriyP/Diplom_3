package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSteps {

    private UserApi userApi = new UserApi();
    private ValidatableResponse response;
    private String accessToken;

    @Step("Регистрация пользователя")
    public UserSteps registerUser(UserRegisterRequest user) {
        response = userApi.registerUser(user).then();
        return this;
    }

    @Step("Проверка регистрации (возвращает accessToken)")
    public String checkRegisterUser() {
        response.assertThat().statusCode(SC_OK);
        UserRegisterResponse userRegisterResponseFromApi = response.extract().body().as(UserRegisterResponse.class);
        assertTrue(userRegisterResponseFromApi.isSuccess(), "Ответ сервера содержит success = true");
        assertNotNull(userRegisterResponseFromApi.getUser(), "Ответ сервера содержит объект user");
        assertEquals(UserData.EMAIL.toLowerCase(), userRegisterResponseFromApi.getUser().getEmail().toLowerCase(), "Поле email в объекте user должно совпадать с: " + UserData.EMAIL);
        assertEquals(UserData.NAME, userRegisterResponseFromApi.getUser().getName(), "Поле name в объекте user должно совпадать с: " + UserData.NAME);
        assertNotNull(userRegisterResponseFromApi.getAccessToken(), "Ответ сервера содержит accessToken");
        assertTrue(userRegisterResponseFromApi.getAccessToken().startsWith("Bearer "), "Поле accessToken начинается со слова 'Bearer'");
        assertNotNull(userRegisterResponseFromApi.getRefreshToken(), "Ответ сервера содержит refreshToken");
        return userRegisterResponseFromApi.getAccessToken();
    }

    @Step("Удаление пользователя")
    public UserSteps deleteUser(String token) {
        response = userApi.deleteUser(token).then();
        return this;
    }

    @Step("Успешное удаления пользователя")
    public UserSteps checkDeleteUser() {
        response.assertThat().statusCode(SC_ACCEPTED);
        return this;
    }

}