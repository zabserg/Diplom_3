package praktikum.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";


    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private final SelenideElement orderButton = $(By.xpath("//button[text()='Оформить заказ']"));
    private final SelenideElement profileLink = $(By.xpath("//p[text()='Личный Кабинет']"));
    private final SelenideElement createBurgerText = $(By.xpath("//h1[text()='Соберите бургер']"));
    private final SelenideElement bunsTab = $(By.xpath("//span[text()='Булки']//parent::div"));
    private final SelenideElement saucesTab = $(By.xpath("//span[text()='Соусы']//parent::div"));
    private final SelenideElement fillingsTab = $(By.xpath("//span[text()='Начинки']//parent::div"));


    @Step("Нажать кнопку Войти")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }


    public boolean isOrderButtonDisplayed() {
        return orderButton.shouldBe(visible).isDisplayed();
    }

    @Step("Нажать кнопку Личный кабинет")
    public LoginPage clickProfileLink() {
        profileLink.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Нажать кнопку Личный кабинет после логина")
    public ProfilePage clickProfileLinkUserLogged() {
        profileLink.click();
        return Selenide.page(ProfilePage.class);
    }

    public boolean isCreateBurgerTextDisplayed() {
        return createBurgerText.shouldBe(visible).isDisplayed();
    }


    public void displayAvailableBuns() {
        bunsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Краторная булка N-200i']")));
    }

    public void displayAvailableSauces() {
        saucesTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Соус Spicy-X']")));
    }

    public void displayAvailableFillings() {
        fillingsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Мини-салат Экзо-Плантаго']")));
    }


    public String getFillingsTabClassValue() {
        return fillingsTab.getText();
    }

    public String getBunsTabClassValue() {
        return bunsTab.getText();
    }

    public String getSaucesTabClassValue() {
        return saucesTab.getText();
    }
}
