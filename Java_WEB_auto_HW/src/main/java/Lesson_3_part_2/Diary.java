package Lesson_3_part_2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Diary {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://diary.ru/user/login");

        Cookie cookie = new Cookie("_identity_", "f37de224d62e8809601bd4cd601a88e13e60f9b78c6d85d5441a4ddc9c0ed332a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3560327%2C%22meVVQKmDgzC8x2Yn75eM3GjxSXXmsKMh%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[text()='Новая запись']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='postTitle']")).sendKeys("TesterHead");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='message_ifr']")));
        String text = "TextText";
        driver.findElement(By.xpath("//body[@id='tinymce']")).sendKeys(text);
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//input[@id='rewrite']")).click();

        driver.findElement(By.xpath("//a[@class='comments']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@class='grey'][@style='cursor:pointer']")).click();
        driver.findElement(By.xpath("//option[@value='0']")).click();
        driver.findElement(By.xpath("//*[@id='text']")).sendKeys(text);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
