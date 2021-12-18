package Lesson_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LfSiteTest {
    WebDriver driver;
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
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        loginBlock = new LoginBlock(driver);
        loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
    }
    String name = "Tester Test Tester";

    @Test
    @DisplayName("Добавление нового адреса доставки")
    void AddNewAddressTest() {
        new MainPage(driver).closeContainer();
        new MainPage(driver).clickMyAccountButton();
        new LoginBlock(driver).clickLoginButton();
        new LoginPage(driver).
                fillEmailInput("fewoj49881@nefacility.com").
                fillPasswordInput("<ercbhjdrf01").
                submitLoginData();

        new AccountPage(driver).
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
        new MainPage(driver).closeContainer();
        new MainPage(driver).
                clickBurgerMenu().
                clickBrandButton().
                clickBrandLetter().
                clickToBrandByName().
                clickToBrandItem().
                clickAddToBasket();
        new BasketPreviewBlock(driver).
                clickViewBasketButton().
                checkItemAddedToBasket(brand);

    }

    String searchItem = "By Terry";
    @Test
    @DisplayName("Проверка поиска")
    void checkSearchButton(){
        new MainPage(driver).closeContainer();
        new MainPage(driver).
                clickSearchGlass().
                fillSearchInput(searchItem).
                clickSearchButton().
                checkSearchItem(searchItem);

    }

    @Test
    @DisplayName("Переход по ссылке Служба поддержки")
    void checkHelpLink(){
        new MainPage(driver).closeContainer();
        new MainPage(driver).clickHelpLink();

        assertEquals("https://www.lookfantastic.ru/help-centre.list", driver.getCurrentUrl());
        assertTrue(driver.getTitle().contains("Служба поддержки"));
    }


    @AfterEach
    void tearDown(){
        driver.quit();
    }
}
