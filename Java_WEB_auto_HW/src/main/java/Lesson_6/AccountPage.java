package Lesson_6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertTrue;

public class AccountPage extends BaseView{

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private final static String ADDRESS_BUTTON_LOCATOR_BY_CSS = ".responsiveSettingsCard_wrapper:nth-child(4) > .responsiveSettingsCard";
    @FindBy(css = ADDRESS_BUTTON_LOCATOR_BY_CSS)
    private WebElement addressButton;

    public AccountPage clickAddressButton(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(ADDRESS_BUTTON_LOCATOR_BY_CSS)));
        addressButton.click();
        return this;
    }

    @FindBy(css = ".addressBook_addAddress_button_empty")
    private WebElement newAddressButton;

    public AccountPage clickNewAddressButton(){
        newAddressButton.click();
        return this;
    }


    private final static String FULL_NAME_INPUT_LOCATOR_BY_ID = "fullName";

    @FindBy(id = FULL_NAME_INPUT_LOCATOR_BY_ID)
    private WebElement fullNameInput;

    public AccountPage fillFullNameInput(String fullName){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(FULL_NAME_INPUT_LOCATOR_BY_ID)));
        fullNameInput.sendKeys(fullName);
        return this;

    }

    @FindBy(id = "postCode")
    private WebElement postCodeInput;

    public AccountPage fillPostCodeInput(String postcode){
        postCodeInput.sendKeys(postcode);
        return this;
    }

    @FindBy(id = "houseNumber")
    private WebElement houseNumberInput;

    public AccountPage fillHouseNumberInput(String house){
        houseNumberInput.sendKeys(house);
        return this;

    }

    @FindBy(id = "streetName")
    private WebElement streetNameInput;

    public AccountPage fillStreetNameInput(String street){
        streetNameInput.sendKeys(street);
        return this;
    }

    @FindBy(id = "addressLine3")
    private WebElement addressLineInput;

    public AccountPage fillCityInput(String city){
        addressLineInput.sendKeys(city);
        return this;
    }

    @FindBy(id = "phoneNumber")
    private WebElement phoneInput;

    public AccountPage fillPhoneInput(String phone){
        phoneInput.sendKeys(phone);
        return this;

    }

    @FindBy(css = ".editAddress_card_submitButton")
    private WebElement submitAddressButton;

    public AccountPage clickSubmitAddressButton(){
        submitAddressButton.click();
        return this;
    }


    @FindBy(xpath = "//p[@class='addressBook_card_fullName']")
    private WebElement addedAddressBlock;

    public AccountPage checkAddedAddress(String name){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(String.format("//p[text()='%s']", name))));
        assertTrue(addedAddressBlock.getText().contains(name));
        return this;
    }

    void deleteRecord(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='addressBook_card']")));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

}
