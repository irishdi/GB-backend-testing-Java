package Lesson_8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private SelenideElement containerButton = $(By.cssSelector(".close-button"));

    @Step("Закрытие рекламного контейнера")
    public void closeContainer() throws InterruptedException {
        containerButton.click();
        Thread.sleep(5000);
    }


    private SelenideElement myAccountButton = $(By.id("responsiveAccountHeader_openAccountButtonMobile_rightSection"));

    @Step("Нажать на кнопку Мой аккаунт")
    public MainPage clickMyAccountButton() {
        myAccountButton.hover();
        return this;
    }

    private SelenideElement burgerMenu = $(By.cssSelector(".responsiveFlyoutMenu_burgerMenu"));

    @Step("Развернуть меню")
    public MainPage clickBurgerMenu(){
        burgerMenu.click();
        return this;
    }

    private SelenideElement brandButton = $(By.xpath(
            "//a[contains (@class, 'responsiveFlyoutMenu_levelOneLink') and contains (@data-context, 'Бренды')]"));

    @Step("Нажать на Бренды")
    public MainPage clickBrandButton(){
        brandButton.click();
        return this;
    }

    private SelenideElement letterBButton = $(By.xpath("//a[@href='#panel-letter-B']"));

    @Step("Выбрать букву бренда")
    public MainPage clickBrandLetter(){
        letterBButton.click();
        return this;
    }

    private SelenideElement brandName = $(By.linkText("Babyliss"));

    @Step("Выбрать бренд по имени")
    public MainPage clickToBrandByName() {
        brandName.click();
        return page(MainPage.class);
    }

    private ElementsCollection brandItemsList = $$(By.xpath("//div[@class='productBlock ']"));

    @Step("Выбрать товар по наименованию")
    public MainPage clickToBrandItem(){
        //brandItemsList.stream().findFirst().get().click();
        brandItemsList.first().click();
        return page(MainPage.class);
    }

    private SelenideElement addToBasketButton = $(By.xpath("//button[@data-component='productAddToBasket']"));

    @Step("Добавить в корзину")
    public MainPage clickAddToBasket(){
        addToBasketButton.click();
        return this;
    }

    private SelenideElement searchGlass = $(By.cssSelector(".headerSearch_toggleForm > .headerSearch_spyglass"));

    @Step("Нажать поиск")
    public MainPage clickSearchGlass(){
        searchGlass.click();
        return this;
    }

    private SelenideElement searchInput = $(By.id("header-search-input"));

    @Step("Ввести даныне для поиска")
    public MainPage fillSearchInput(String searchItem){
        searchInput.sendKeys(searchItem);
        return this;
    }

    private SelenideElement searchButton = $(By.cssSelector(".headerSearch_button > .headerSearch_spyglass"));

    @Step("Нажать кнопку поискового запроса")
    public MainPage clickSearchButton(){
        searchButton.click();
        return page(MainPage.class);
    }


    private ElementsCollection searchItems = $$(By.xpath(
            "//div//span[@class='js-enhanced-ecommerce-data hidden']"));

    @Step("Проверка наименования товара для поиска")
    public MainPage checkSearchItem(String searchItem){
        //assertThat(String.valueOf(searchItems.stream().allMatch(s ->
          //      s.getAttribute("data-product-brand").contains(searchItem))), true);
        //assertThat(searchItems.findBy(Condition.attribute("data-product-brand", searchItem)));
        searchItems.findBy(Condition.attribute("data-product-brand", searchItem));
        return this;
    }

    private SelenideElement helpLink = $(By.linkText("Служба Поддержки"));

    @Step("Перейти по ссылке Служба поддержки")
    public MainPage clickHelpLink(){
        helpLink.click();
        return page(MainPage.class);
    }

}
