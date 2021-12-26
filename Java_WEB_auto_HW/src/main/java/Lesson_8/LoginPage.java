package Lesson_8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement emailInput = $(By.name("Адрес электронной почты"));

    @Step("Заполнение поля email")
    public LoginPage fillEmailInput(String email){
        emailInput.sendKeys(email);
        return this;
    }

    private SelenideElement passwordInput = $(By.name("Пароль"));

    @Step("Заполнение поля пароль")
    public LoginPage fillPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    private SelenideElement submitLoginButton = $(By.cssSelector(".jjIGWo"));

    @Step("Нажатие кнопки логин")
    public LoginPage submitLoginData(){
        submitLoginButton.click();
        return this;
    }

}

