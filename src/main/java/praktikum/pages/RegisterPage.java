package praktikum.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import praktikum.model.UserModel;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    final public static String URL = MainPage.URL + "register";


    private final SelenideElement nameInputField = $(By.xpath("//label[text()='Имя']//following-sibling::input"));
    private final SelenideElement emailInputField = $(By.xpath("//label[text()='Email']//following-sibling::input"));
    private final SelenideElement passwordInputField = $(By.xpath("//input[@type='password']"));
    private final SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private final SelenideElement incorrectPasswordWarning = $(By.xpath("//p[text()='Некорректный пароль']"));
    private final SelenideElement loginLink = $(By.xpath("//a[text()='Войти']"));


    @Step("Заполнить поле Имя")
    public RegisterPage inputName(String name) {
        nameInputField.sendKeys(name);
        return this;
    }

    @Step("Заполнить поле Email")
    public RegisterPage inputEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    @Step("Заполнить поле Пароль")
    public RegisterPage inputPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку Регистрация")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Заполнить форму регистрации")
    public LoginPage registerNewUser(UserModel user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegisterButton();
        return Selenide.page(LoginPage.class);
    }

    @Step("Заполнить форму регистрации не валидными данными")
    public RegisterPage registerNewUserWithIncorrectPass(UserModel user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegisterButton();
        return this;
    }

    @Step("Нажать кнопку Войти")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }

    public boolean isIncorrectPassDisplayed() {
        return incorrectPasswordWarning.shouldBe(visible).isDisplayed();
    }
}
