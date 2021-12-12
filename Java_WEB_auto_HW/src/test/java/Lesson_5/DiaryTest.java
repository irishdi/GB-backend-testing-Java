package Lesson_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiaryTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String BASE_URL2 = "https://diary.ru/user/login";

    @BeforeAll
    static void registerDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(7));
        actions = new Actions(driver);
        driver.get(BASE_URL2);
    }

    @Test
    @DisplayName("Проверка создания новой записи в дневнике")
    void addNewDiaryRecord() {
        login();
        driver.findElement(By.xpath("//span[text()='Новая запись']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='postTitle']")));
        driver.findElement(By.xpath("//input[@id='postTitle']")).sendKeys("TesterHead");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='message_ifr']")));
        String text = "TextText";
        driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys(text);
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//input[@id='rewrite']")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='post-header']")));
        assertTrue(driver.findElement(By.xpath("//div[@class='post-header']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='post-inner']")).getText().contains(text));

    }

    @Test
    @DisplayName("Проверка создания комментария к текущей записи")
    void addCommentToExistingRecord() throws InterruptedException {
        String text2 = "12345";

        addNewDiaryRecord();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Комментировать")));
        driver.findElement(By.xpath("//a[@class='comments']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='msg_form']/p/a/span")));
        driver.findElement(By.xpath("//span[contains(.,'Расширенная форма')]")).click();
        driver.findElement(By.xpath("//option[@value='0']")).click();
        driver.findElement(By.xpath("//*[@id='text']")).sendKeys(text2);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item discussion']//div[@class='post-inner']")));
        assertTrue(driver.findElement(By.xpath("//div[@class='item discussion']//div[@class='post-inner']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='item discussion']//div[@class='post-inner']")).getText().contains(text2));
        deleteRecord();
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    void login() {
        Cookie cookie = new Cookie("_identity_", "f37de224d62e8809601bd4cd601a88e13e60f9b78c6d85d5441a4ddc9c0ed332a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3560327%2C%22meVVQKmDgzC8x2Yn75eM3GjxSXXmsKMh%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

    }
    void deleteRecord(){
        driver.findElement(By.cssSelector(".i-cross")).click();
    }
}
