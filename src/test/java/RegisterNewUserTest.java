import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.UserCredentialsModel;
import praktikum.model.UserModel;
import praktikum.pages.MainPage;
import praktikum.steps.UserClient;

import static org.junit.Assert.assertTrue;
import static praktikum.config.Init.setSettings;

public class RegisterNewUserTest {

    private UserClient userClient;
    private UserCredentialsModel creds;
    private UserModel userModel;
    private boolean afterToBeLaunched;


    @Before
    public void setUp() {
        setSettings();
        Selenide.open("/");
        userClient = new UserClient();
        userModel = UserModel.getRandom();
        creds = UserCredentialsModel.from(userModel);
    }

    @After
    public void teardown() {
        WebDriverRunner.closeWebDriver();
        if (!afterToBeLaunched) {
            return;
        }
        String bearerToken = userClient.login(creds)
                .then().log().all()
                .extract()
                .path("accessToken");
        userClient.delete(userModel.getEmail(), bearerToken);
    }


    @Test
    @DisplayName("Проверь: Успешную регистрацию. Минимальный пароль — шесть символов - Успешно")
    public void registerNewUserWithCorrectPassSuccessfully() {
        userModel.setPassword("Pa_s#6");
        final boolean correctPasswordWarningDisplayed = new MainPage()
                .clickLoginButton()
                .clickRegisterLink()
                .registerNewUser(userModel)
                .isUserLoginTextDisplayed();
        assertTrue(correctPasswordWarningDisplayed);


    }

    @Test
    @DisplayName("Проверь: Ошибку для некорректного пароля. Минимальный пароль — шесть символов -  НЕ Успешно")
    public void registerNewUserWithIncorrectPassFails() {
        userModel.setPassword("Pas#5");
        final boolean incorrectPasswordWarningDisplayed = new MainPage()
                .clickLoginButton()
                .clickRegisterLink()
                .registerNewUserWithIncorrectPass(userModel)
                .isIncorrectPassDisplayed();
        assertTrue(incorrectPasswordWarningDisplayed);
    }
}
