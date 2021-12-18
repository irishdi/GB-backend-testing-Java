package Lesson_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginBlock extends BaseView{

    public LoginBlock(WebDriver driver) {
        super(driver);
    }

    private final static String loginButtonInputByXpath = "//a[contains(text(),'Войти')]";

    @FindBy(xpath = loginButtonInputByXpath)
    private WebElement loginButton;

    public LoginBlock clickLoginButton(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(loginButtonInputByXpath)));
        loginButton.click();
        return this;
    }
}
