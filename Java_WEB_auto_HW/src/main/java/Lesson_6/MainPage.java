package Lesson_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class MainPage extends BaseView{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "responsiveAccountHeader_openAccountButtonMobile_rightSection")
    private WebElement myAccountButton;

    public MainPage clickMyAccountButton(){
        myAccountButton.click();
        return this;
    }

    @FindBy(css = ".responsiveFlyoutMenu_burgerMenu")
    private WebElement burgerMenu;

    public MainPage clickBurgerMenu(){
        burgerMenu.click();
        return this;
    }

    private final static String BRAND_BUTTON_LOCATOR_BY_XPATH =
            "//a[contains (@class, 'responsiveFlyoutMenu_levelOneLink') and contains (@data-context, 'Бренды')]";
        @FindBy(xpath = BRAND_BUTTON_LOCATOR_BY_XPATH)
    private WebElement brandButton;

    public MainPage clickBrandButton(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(BRAND_BUTTON_LOCATOR_BY_XPATH)));
        brandButton.click();
        return this;
    }

    private final static String LETTER_B_LOCATOR_BY_XPATH = "//a[@href='#panel-letter-B']";
    @FindBy(xpath = LETTER_B_LOCATOR_BY_XPATH)
    private WebElement letterBButton;

    public MainPage clickBrandLetter(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LETTER_B_LOCATOR_BY_XPATH)));
        letterBButton.click();
        return this;
    }

    private final static String BRAND_LIST_LOCATOR_BY_LINKED_TEXT = "Babyliss";
    @FindBy(linkText = BRAND_LIST_LOCATOR_BY_LINKED_TEXT)
    private WebElement brandName;

    public MainPage clickToBrandByName() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(BRAND_LIST_LOCATOR_BY_LINKED_TEXT)));
        brandName.click();
        return new MainPage(driver);
    }

    private final static String BRAND_ITEMS_LIST_LOCATOR_BY_XPATH = "//div[@class='productBlock ']";
    @FindBy(xpath = BRAND_ITEMS_LIST_LOCATOR_BY_XPATH)
    private List<WebElement> brandItemsList;

    public MainPage clickToBrandItem(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BRAND_ITEMS_LIST_LOCATOR_BY_XPATH)));
        brandItemsList.stream().findFirst().get().click();
        return new MainPage(driver);
    }

    private final static String ADD_TO_BASKET_BUTTON_LOCATOR_BY_XPATH = "//button[@data-component='productAddToBasket']";
    @FindBy(xpath = ADD_TO_BASKET_BUTTON_LOCATOR_BY_XPATH)
    private WebElement addToBasketButton;

    public MainPage clickAddToBasket(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(ADD_TO_BASKET_BUTTON_LOCATOR_BY_XPATH)));
        addToBasketButton.click();
        return this;
    }


    @FindBy(css=".headerSearch_toggleForm > .headerSearch_spyglass")
    private WebElement searchGlass;

    public MainPage clickSearchGlass(){
        searchGlass.click();
        return this;
    }

    @FindBy(id="header-search-input")
    private WebElement searchInput;

    public MainPage fillSearchInput(String searchItem){
        searchInput.sendKeys(searchItem);
        return this;
    }

    @FindBy(css = ".headerSearch_button > .headerSearch_spyglass")
    private WebElement searchButton;

    public MainPage clickSearchButton(){
        searchButton.click();
        return new MainPage(driver);
    }

    @FindBy(xpath = "//div//span[@class='js-enhanced-ecommerce-data hidden']")
    private List<WebElement> searchItems;

    public MainPage checkSearchItem(String searchItem){
        assertThat(String.valueOf(searchItems.stream().allMatch(s -> s.getAttribute("data-product-brand").contains(searchItem))), true);
        return this;
    }

    @FindBy(linkText = "Служба Поддержки")
    private WebElement helpLink;

    public MainPage clickHelpLink(){
        helpLink.click();
        return new MainPage(driver);
    }

}
