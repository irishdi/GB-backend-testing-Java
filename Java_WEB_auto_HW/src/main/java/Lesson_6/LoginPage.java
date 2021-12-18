package Lesson_6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "Адрес электронной почты")
    private WebElement emailInput;

    public LoginPage fillEmailInput(String email){
        emailInput.sendKeys(email);
        return this;
    }

    @FindBy(name = "Пароль")
    private WebElement passwordInput;

    public LoginPage fillPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }


    @FindBy(css = ".jjIGWo")
    private WebElement submitLoginButton;

    public LoginPage submitLoginData(){
        submitLoginButton.click();
        return this;
    }

}
