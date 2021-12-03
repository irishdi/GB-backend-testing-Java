package Lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LfSiteAuthorization {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.lookfantastic.ru/");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".close-button")).click();
        driver.findElement(By.id("responsiveAccountHeader_openAccountButtonMobile_rightSection")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.name("Адрес электронной почты")).sendKeys("fewoj49881@nefacility.com");
        driver.findElement(By.name("Пароль")).sendKeys("<ercbhjdrf01");
        driver.findElement(By.cssSelector(".jjIGWo")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
