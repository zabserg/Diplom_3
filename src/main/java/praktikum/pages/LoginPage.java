package praktikum.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import praktikum.model.UserModel;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти']"));
    private final SelenideElement userLoginText = $(By.xpath("//h2[text()='Вход']"));
    private final SelenideElement restorePasswordLink = $(By.xpath("//a[text()='Восстановить пароль']"));
    private final SelenideElement registerLink = $(By.xpath("//a[text()='Зарегистрироваться']"));
    private final SelenideElement emailInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']/parent::div/input"));
    private final SelenideElement passwordInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']/parent::div/input"));

    @Step("Нажать кнопку Регистрация")
    public RegisterPage clickRegisterLink() {
        registerLink.shouldBe(visible).click();
        return Selenide.page(RegisterPage.class);
    }

    @Step("Нажать кнопку сбросить пароль")
    public ForgotPasswordPage clickRestorePasswordLink() {
        restorePasswordLink.click();
        return Selenide.page(ForgotPasswordPage.class);
    }

    @Step("Нажать кнопку Войти")
    public MainPage clickLoginButton() {
        loginButton.click();
        return Selenide.page(MainPage.class);
    }

    @Step("Заполнить поле Email")
    public LoginPage inputEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    @Step("Заполнить поле пароль")
    public LoginPage inputPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    @Step("Заполнить поля Email и пароль, нажать кнопку Войти")
    public MainPage userLogin(UserModel user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickLoginButton();
        return Selenide.page(MainPage.class);
    }

    public boolean isUserLoginTextDisplayed() {
        return userLoginText.shouldBe(visible).isDisplayed();
    }
}
