package Lesson_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketPreviewBlock extends BaseView{
    public BasketPreviewBlock(WebDriver driver) {
        super(driver);
    }


    private final static String VIEW_BASKET_BUTTON_LOCATOR_BY_XPATH = "//a[contains(text(), 'Посмотреть корзину')]";
    @FindBy(xpath = VIEW_BASKET_BUTTON_LOCATOR_BY_XPATH)
    private WebElement viewBasketButton;

    public BasketPage clickViewBasketButton() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(VIEW_BASKET_BUTTON_LOCATOR_BY_XPATH)));
        viewBasketButton.click();
        return new BasketPage(driver);
    }

}
