package Lesson_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseView {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    public BaseView(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);

    }

    private final static String CONTAINER_BUTTON_LOCATOR_BY_CSS = ".close-button";

    @FindBy(css = CONTAINER_BUTTON_LOCATOR_BY_CSS)
    private WebElement containerButton;

    public void closeContainer(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(CONTAINER_BUTTON_LOCATOR_BY_CSS)));
        containerButton.click();
    }
}
