package Lesson_6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BasketPage extends BaseView{
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[@class='responsiveBasket_itemName']")
    private List<WebElement> basketItems;

    @Step("Проверка товара, добавленного в корзину")
    public BasketPage checkItemAddedToBasket(String brandName){
        assertTrue(String.valueOf(basketItems.stream().filter(f -> f.getText().contains(brandName))), true);
        return this;
    }
}
