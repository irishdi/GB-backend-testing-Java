package Lesson_8;

import Lesson_6.BasketPage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasketPreviewBlock {

    private SelenideElement viewBasketButton = $(By.xpath("//a[contains(text(), 'Посмотреть корзину')]"));

    @Step("Просмотр корзины")
    public BasketPage clickViewBasketButton() {
        viewBasketButton.click();
        return page(BasketPage.class);
    }
}
