package Lesson_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LfSiteTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String BASE_URL = "https://www.lookfantastic.ru/";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(8));
        actions = new Actions(driver);
        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Добавление нового адреса доставки")
    void AddNewAddress() throws InterruptedException {
        login();

        driver.findElement(
                By.cssSelector(".responsiveSettingsCard_wrapper:nth-child(4) > .responsiveSettingsCard")).click();
        driver.findElement(By.cssSelector(".addressBook_addAddress_button_empty")).click();

        String fullName = "Tester Test Tester";
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("fullName")));
        driver.findElement(By.id("fullName")).sendKeys(fullName);
        driver.findElement(By.id("postCode")).sendKeys("190000");
        driver.findElement(By.id("houseNumber")).sendKeys("Korablestroiteley 16");
        driver.findElement(By.id("streetName")).sendKeys("31");
        driver.findElement(By.id("addressLine3")).sendKeys("St. Petersburg");
        driver.findElement(By.id("phoneNumber")).sendKeys("+7921558899");
        driver.findElement(By.cssSelector(".editAddress_card_submitButton")).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//p[text()='%s']", fullName))));
        assertTrue(driver.findElement(By.xpath("//p[@class='addressBook_card_fullName']")).getText().contains(fullName));
        deleteRecord();
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addItemToBasket(){
        driver.findElement(By.cssSelector(".responsiveFlyoutMenu_burgerMenu")).click();
        closeContainer();
        driver.findElement(By.linkText("Бренды")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#panel-letter-B']")));
        driver.findElement(By.linkText("B")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Babyliss')])[2]")));
        driver.findElement(By.linkText("Babyliss")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li/div/div[2]/div/button")));
        driver.findElement(By.cssSelector(".productListProducts_product:nth-child(1) .productQuickbuySimple")).click();
        String item = driver.findElement(By.xpath("//a[@class='addedToBasketModal_itemName']")).getText();
        driver.findElement(By.cssSelector(".addedToBasketModal_viewBasketButton")).click();
        assertTrue(item.contains(driver.findElement(By.xpath("//p[@class='responsiveBasket_itemName']")).getText()));
    }

    @Test
    @DisplayName("Переход по ссылке Служба поддержки")
    void clickHelpLink(){
        closeContainer();
        driver.findElement(By.linkText("Служба Поддержки")).click();
        assertEquals("https://www.lookfantastic.ru/help-centre.list", driver.getCurrentUrl());
        assertTrue(driver.getTitle().contains("Служба поддержки"));
    }

    @Test
    @DisplayName("Проверка поиска")
    void checkSearchButton(){
        closeContainer();
        String searchItem = "By Terry";
        driver.findElement(By.cssSelector(".headerSearch_toggleForm > .headerSearch_spyglass")).click();
        driver.findElement(By.id("header-search-input")).sendKeys(searchItem);
        driver.findElement(By.cssSelector(".headerSearch_button > .headerSearch_spyglass")).click();

        List<WebElement> items = driver.findElements(By.xpath("//div//span[@class='js-enhanced-ecommerce-data hidden']"));
        assertTrue(items.stream().allMatch(s -> s.getAttribute("data-product-brand").contains(searchItem)));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    void login() throws InterruptedException {
        closeContainer();
        driver.findElement(By.id("responsiveAccountHeader_openAccountButtonMobile_rightSection")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Войти')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.name("Адрес электронной почты")).sendKeys("fewoj49881@nefacility.com");
        driver.findElement(By.name("Пароль")).sendKeys("<ercbhjdrf01");
        driver.findElement(By.cssSelector(".jjIGWo")).click();
        Thread.sleep(5000);
    }

    void closeContainer(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='emailReengagement_container']")));
        driver.findElement(By.cssSelector(".close-button")).click();
    }
    void deleteRecord(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='addressBook_card']")));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
}
