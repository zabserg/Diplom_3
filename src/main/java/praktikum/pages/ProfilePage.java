package praktikum.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement profileText = $(By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']"));
    private final SelenideElement createBurger = $(By.xpath("//p[text()='Конструктор']"));
    private final SelenideElement burgerLogo = $(By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a"));
    private final SelenideElement logoutButton = $(By.xpath("//button[text()='Выход']"));


    public boolean isProfileTextDisplayed() {
        return profileText.shouldBe(visible).isDisplayed();
    }


    @Step("Нажать Конструктор")
    public MainPage clickCreateBurger() {
        createBurger.click();
        return Selenide.page(MainPage.class);
    }


    @Step("Нажать на элемент логотип")
    public MainPage clickBurgerLogo() {
        burgerLogo.click();
        return Selenide.page(MainPage.class);
    }


    @Step("Нажать Выход")
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return Selenide.page(LoginPage.class);
    }
}
