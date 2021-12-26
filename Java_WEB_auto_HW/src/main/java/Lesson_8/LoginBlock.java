package Lesson_8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginBlock {

   private SelenideElement loginButton = $(By.xpath("//a[contains(text(),'Войти')]"));

    @Step("Нажатие кнопки логин")
    public LoginBlock clickLoginButton(){
        loginButton.click();
        return this;
    }
}
