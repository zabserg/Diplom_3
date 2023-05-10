import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.UserCredentialsModel;
import praktikum.model.UserModel;
import praktikum.pages.LoginPage;
import praktikum.steps.UserClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static praktikum.config.Init.setSettings;

public class UserProfileTest {

    private UserClient userClient;
    private UserModel userModel;
    private String bearerToken;

    @Before
    public void setUp() {
        setSettings();
        Selenide.open("\\login");
        userClient = new UserClient();
        userModel = UserModel.getRandom();
        UserCredentialsModel creds = UserCredentialsModel.from(userModel);
        userClient.registerNewUser(userModel);
        bearerToken = userClient.login(creds)
                .then().log().all()
                .assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");
    }

    @After
    public void teardown() {
        userClient.delete(userModel.getEmail(), bearerToken);
        WebDriverRunner.closeWebDriver();
    }


    @Test
    @DisplayName("Проверь переход по клику на «Личный кабинет» - Успешно")
    public void successfullyDisplayUserProfile() {
        final boolean profileTextDisplayed = new LoginPage()
                .userLogin(userModel)
                .clickProfileLinkUserLogged()
                .isProfileTextDisplayed();
        assertTrue(profileTextDisplayed);
    }

    @Test
    @DisplayName("Проверь переход по клику на «Конструктор» - Успешно")
    public void successfullyDisplayCreateBurgerTextByClickingTheCreateBurgerLink() {
        final boolean createBurgerTextDisplayed = new LoginPage()
                .userLogin(userModel)
                .clickProfileLinkUserLogged()
                .clickCreateBurger()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }

    @Test
    @DisplayName("Проверь переход по клику на логотип Stellar Burgers - Успешно")
    public void successfullyDisplayCreateBurgerTextByClickingTheBurgerLogo() {
        final boolean createBurgerTextDisplayed = new LoginPage()
                .userLogin(userModel)
                .clickProfileLinkUserLogged()
                .clickBurgerLogo()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }


    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете - Успешно")
    public void successfullyLogoutUser() {
        final boolean userLoginTextDisplayed = new LoginPage()
                .userLogin(userModel)
                .clickProfileLinkUserLogged()
                .clickLogoutButton()
                .isUserLoginTextDisplayed();
        assertTrue(userLoginTextDisplayed);
    }
}
