package Lesson_6;

import Lesson_7.CustomLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Тестирование сайта LookFantastic")
@Owner("Irina P")

public class LfSiteTest {
    //WebDriver driver;
    EventFiringWebDriver eventFiringWebDriver;
    MainPage mainPage;
    LoginBlock loginBlock;
    LoginPage loginPage;
    private final static String BASE_URL = "https://www.lookfantastic.ru/";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
        eventFiringWebDriver.register(new CustomLogger());
//        driver = new ChromeDriver();
        mainPage = new MainPage(eventFiringWebDriver);
        loginBlock = new LoginBlock(eventFiringWebDriver);
        loginPage = new LoginPage(eventFiringWebDriver);
        eventFiringWebDriver.get(BASE_URL);
    }
    String name = "Tester Test Tester";

    @Test
    @DisplayName("Добавление нового адреса доставки")
    @Description("Добавление нового адреса доставки")
    void AddNewAddressTest() {
        new MainPage(eventFiringWebDriver).closeContainer();
        new MainPage(eventFiringWebDriver).clickMyAccountButton();
        new LoginBlock(eventFiringWebDriver).clickLoginButton();
        new LoginPage(eventFiringWebDriver).
                fillEmailInput("fewoj49881@nefacility.com").
                fillPasswordInput("<ercbhjdrf01").
                submitLoginData();

        new AccountPage(eventFiringWebDriver).
                clickAddressButton().
                clickNewAddressButton().
                fillFullNameInput(name).
                fillPostCodeInput("190000").
                fillHouseNumberInput("31").
                fillStreetNameInput("Korablestroiteley 16").
                fillCityInput("St. Petersburg").
                fillPhoneInput("+7921558899").
                clickSubmitAddressButton().
                checkAddedAddress(name).
                deleteRecord();

    }


    String brand = "Babyliss";
    @Test
    @DisplayName("Добавление товара в корзину")
    void addItemToBasket() {
        new MainPage(eventFiringWebDriver).closeContainer();
        new MainPage(eventFiringWebDriver).
                clickBurgerMenu().
                clickBrandButton().
                clickBrandLetter().
                clickToBrandByName().
                clickToBrandItem().
                clickAddToBasket();
        new BasketPreviewBlock(eventFiringWebDriver).
                clickViewBasketButton().
                checkItemAddedToBasket(brand);

    }

    String searchItem = "By Terry";
    @Test
    @DisplayName("Проверка поиска")
    void checkSearchButton(){
        new MainPage(eventFiringWebDriver).closeContainer();
        new MainPage(eventFiringWebDriver).
                clickSearchGlass().
                fillSearchInput(searchItem).
                clickSearchButton().
                checkSearchItem(searchItem);

    }

    @Test
    @DisplayName("Переход по ссылке Служба поддержки")
    void checkHelpLink(){
        new MainPage(eventFiringWebDriver).closeContainer();
        new MainPage(eventFiringWebDriver).clickHelpLink();

        assertEquals("https://www.lookfantastic.ru/help-centre.list", eventFiringWebDriver.getCurrentUrl());
        assertTrue(eventFiringWebDriver.getTitle().contains("Служба поддержки"));
    }


    @AfterEach
    void tearDown(){
        LogEntries logs = eventFiringWebDriver.manage().logs().get(LogType.BROWSER);
        Iterator<LogEntry> iterator= logs.iterator();
        while (iterator.hasNext()){
            Allure.addAttachment("Элемент лога браузера ", iterator.next().getMessage());
        }
        eventFiringWebDriver.quit();
    }
}
