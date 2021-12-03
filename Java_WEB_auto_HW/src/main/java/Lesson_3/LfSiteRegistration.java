package Lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LfSiteRegistration {
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
        driver.findElement(By.cssSelector(".responsiveAccountHeader_accountRegister")).click();
        driver.findElement(By.xpath("//input[@label='Полное имя']")).sendKeys("TesterTester");

        String email = "fewoj49886@nefacility.com";
        String password = "<ercbhjdrf01";
        driver.findElement(By.name("Адрес электронной почты")).sendKeys(email);
        driver.findElement(By.xpath("//input[@label='Подтвердить Адрес электронной почты']")).sendKeys(email);
        driver.findElement(By.name("Пароль")).sendKeys(password);
        driver.findElement(By.xpath("//input[@label='Подтвердить Пароль']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@aria-label='Продолжить']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[@id='responsiveAccountHeader_openAccountButtonMobile_rightSection']")).click();
        driver.quit();

    }
}
