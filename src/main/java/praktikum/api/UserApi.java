package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.BaseHttpClient;

public class UserApi extends BaseHttpClient {
    private final String postUserRegister = "/api/auth/register";
    private final String getUser = "/api/auth/user";
    private final String deleteUser = "/api/auth/user";
    private final String postLoginUser = "/api/auth/login";

    @Step("Создание пользователя")
    public Response registerUser(UserRegisterRequest user) {
        return doPostRequest(postUserRegister, user);
    }

    @Step("Получение данных пользователя")
    public Response getUser(String token) {
        return doGetRequest(getUser, token);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String token) {
        return doDeleteRequest(deleteUser, token);
    }

}