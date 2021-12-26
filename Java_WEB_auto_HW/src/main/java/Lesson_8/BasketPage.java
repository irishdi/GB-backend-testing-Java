package Lesson_8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;


public class BasketPage {

    private ElementsCollection basketItems = $$(By.xpath("//p[@class='responsiveBasket_itemName']"));

    @Step("Проверка товара, добавленного в корзину")
    public BasketPage checkItemAddedToBasket(String brandName){
        //assertTrue(String.valueOf(basketItems.stream().filter(f -> f.getText().contains(brandName))), true);
        //assertTrue(String.valueOf(basketItems.filterBy(Condition.text(brandName))), true);
        basketItems.findBy(Condition.text(brandName));
        return this;
    }
}
