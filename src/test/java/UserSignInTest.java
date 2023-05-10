import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.UserCredentialsModel;
import praktikum.model.UserModel;
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegisterPage;
import praktikum.steps.UserClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static praktikum.config.Init.setSettings;

public class UserSignInTest {

    private UserClient userClient;
    private UserModel user;
    private String bearerToken;

    @Before
    public void setUp() {
        setSettings();
        userClient = new UserClient();
        user = UserModel.getRandom();
        UserCredentialsModel creds = UserCredentialsModel.from(user);
        userClient.registerNewUser(user);
        bearerToken = userClient.login(creds)
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");
    }

    @After
    public void teardown() {
        userClient.delete(user.getEmail(), bearerToken);
        WebDriverRunner.closeWebDriver();
    }


    @Test
    @DisplayName("Проверь: вход по кнопке «Войти в аккаунт» на главной - Успешно")
    public void successfullyLoginUserUsingLoginButtonOnTheMainPage() {
        final boolean orderButtonDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickLoginButton()
                .userLogin(user)
                .isOrderButtonDisplayed();
        assertTrue(orderButtonDisplayed);
    }

    @Test
    @DisplayName("Проверь: вход через кнопку «Личный кабинет» - Успешно")
    public void successfullyLoginUserUsingProfileLinkOnTheMainPage() {
        final boolean orderButtonDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickProfileLink()
                .userLogin(user)
                .isOrderButtonDisplayed();
        assertTrue(orderButtonDisplayed);
    }


    @Test
    @DisplayName("Проверь: вход через кнопку в форме регистрации - Успешно")
    public void successfullyLoginUserUsingLoginLinkOnTheRegisterPage() {
        final boolean orderButtonDisplayed = Selenide.open(RegisterPage.URL, RegisterPage.class)
                .clickLoginLink()
                .userLogin(user)
                .isOrderButtonDisplayed();
        assertTrue(orderButtonDisplayed);
    }

    @Test
    @DisplayName("Проверь: вход через кнопку в форме восстановления пароля - Успешно")
    public void successfullyLoginUserUsingLoginLinkOnTheRestorePasswordPage() {
        final boolean orderButtonDisplayed = Selenide.open(ForgotPasswordPage.URL, ForgotPasswordPage.class)
                .clickLoginLink()
                .userLogin(user)
                .isOrderButtonDisplayed();
        assertTrue(orderButtonDisplayed);
    }
}
