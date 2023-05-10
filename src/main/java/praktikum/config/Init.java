package praktikum.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;

public class Init {

    public static final String PATH_DRIVER_YANDEX = "src/main/resources/driver/yandexdriver.exe";
    public static final String PATH_DRIVER_CHROME = "src/main/resources/driver/chromedriver.exe";

    /**
     * Выбрать активный браузер
     **/

    public static void setSettings() {
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site";
        Configuration.holdBrowserOpen = false;

        System.setProperty("webdriver.chrome.driver", PATH_DRIVER_CHROME);  //для запуск браузера Chrome раскомментировать 20 и 21 строку
        WebDriverRunner.setWebDriver(new ChromeDriver());

//        System.setProperty("webdriver.chrome.driver", PATH_DRIVER_YANDEX);  //для запуск браузера Yandex раскомментировать 23 и 24 строку
//        WebDriverRunner.setWebDriver(new ChromeDriver());

    }
}
