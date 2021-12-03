package Lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LfSiteAddAddress {
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

        driver.findElement(By.cssSelector(".responsiveSettingsCard_wrapper:nth-child(4) > .responsiveSettingsCard")).click();
        driver.findElement(By.cssSelector(".addressBook_addAddress_button_empty")).click();

        String fullName = "Tester Test Tester";
        driver.findElement(By.id("fullName")).sendKeys(fullName);
        driver.findElement(By.id("postCode")).sendKeys("190000");
        driver.findElement(By.id("houseNumber")).sendKeys("Korablestroiteley 16");
        driver.findElement(By.id("streetName")).sendKeys("31");
        driver.findElement(By.id("addressLine3")).sendKeys("St. Petersburg");
        driver.findElement(By.id("phoneNumber")).sendKeys("+7921558899");
        driver.findElement(By.cssSelector(".editAddress_card_submitButton")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath(String.format("//p[text()='%s']", fullName))).click();
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        Thread.sleep(3000);
        driver.quit();
    }
}
