package praktikum.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    public static final String URL = MainPage.URL + "forgot-password";

    private final SelenideElement loginLink = $(By.xpath("//a[@class='Auth_link__1fOlj']"));

    @Step("Кликнуть на кнопку Войти на странице Восстановления пароля")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}
