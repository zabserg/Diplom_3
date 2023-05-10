import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.config.Init.setSettings;

public class IngredientsTest {

    @Before
    public void setUp() {
        setSettings();
        Selenide.open("/");
    }

    @After
    public void teardown() {
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Проверь, что работают переходы к разделам: «Булки» - Успешно")
    public void checkBunsTabGetsActivatedSuccessfully() {
        MainPage mainPage = new MainPage();
        mainPage.displayAvailableFillings();
        mainPage.displayAvailableBuns();
        String actual = mainPage.getBunsTabClassValue();
        assertEquals("Булки", actual);
    }


    @Test
    @DisplayName("Проверь, что работают переходы к разделам: «Соусы» - Успешно")
    public void checkSaucesTabGetsActivatedSuccessfully() {
        MainPage mainPage = new MainPage();
        mainPage.displayAvailableFillings();
        mainPage.displayAvailableSauces();
        String actual = mainPage.getSaucesTabClassValue();
        assertEquals("Соусы", actual);
    }

    @Test
    @DisplayName("Проверь, что работают переходы к разделам: «Начинки» - Успешно")
    public void checkFillingsTabGetsActivatedSuccessfully() {
        MainPage mainPage = new MainPage();
        mainPage.displayAvailableSauces();
        mainPage.displayAvailableFillings();
        String actual = mainPage.getFillingsTabClassValue();
        assertEquals("Начинки", actual);
    }
}
