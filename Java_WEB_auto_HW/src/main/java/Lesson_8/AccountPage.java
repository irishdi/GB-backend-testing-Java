package Lesson_8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.url;


public class AccountPage {

    @Step("Проверка нахождения на странице Мой аккаунт")
    public AccountPage checkAccountPage(){
    Selenide.webdriver().shouldHave(url("https://www.lookfantastic.ru/accountHome.account"));
    return this;
    }

    private SelenideElement addressButton = $(By.cssSelector(".responsiveSettingsCard_wrapper:nth-child(4) > .responsiveSettingsCard"));

    @Step("Нажать кнопку адреса")
    public AccountPage clickAddressButton(){
        addressButton.click();
        return this;
    }

    private SelenideElement newAddressButton = $(By.cssSelector(".addressBook_addAddress_button_empty"));

    @Step("Добавление нового адреса")
    public AccountPage clickNewAddressButton(){
        newAddressButton.click();
        return this;
    }

    private SelenideElement fullNameInput = $(By.id("fullName"));

    @Step("Заполнение поля с именем")
    public AccountPage fillFullNameInput(String fullName){
        fullNameInput.sendKeys(fullName);
        return this;
    }

    private SelenideElement postCodeInput = $(By.id("postCode"));

    @Step("Заполнение поля индекс")
    public AccountPage fillPostCodeInput(String postcode){
        postCodeInput.sendKeys(postcode);
        return this;
    }

    private SelenideElement houseNumberInput = $(By.id("houseNumber"));

    @Step("Заполнение поля номер дома")
    public AccountPage fillHouseNumberInput(String house){
        houseNumberInput.sendKeys(house);
        return this;

    }

    private SelenideElement streetNameInput = $(By.id("streetName"));

    @Step("Заполнение поля улица")
    public AccountPage fillStreetNameInput(String street){
        streetNameInput.sendKeys(street);
        return this;
    }

    private SelenideElement addressLineInput = $(By.id("addressLine3"));

    @Step("Заполнение поля город")
    public AccountPage fillCityInput(String city){
        addressLineInput.sendKeys(city);
        return this;
    }

    private SelenideElement phoneInput = $(By.id("phoneNumber"));

    @Step("Заполнение поля номер телефона")
    public AccountPage fillPhoneInput(String phone){
        phoneInput.sendKeys(phone);
        return this;
    }

    private SelenideElement submitAddressButton = $(By.cssSelector(".editAddress_card_submitButton"));

    @Step("Отправка заполненной формы с адресом")
    public AccountPage clickSubmitAddressButton(){
        submitAddressButton.click();
        return this;
    }

    private SelenideElement addedAddressBlock = $(By.xpath("//p[@class='addressBook_card_fullName']"));

    @Step("Проверка добавленного адреса")
    public AccountPage checkAddedAddress(String name){
        addedAddressBlock.shouldHave(Condition.text(name));
        return this;
    }

    private SelenideElement deleteButton = $(By.xpath("//input[@type='submit']"));
    @Step("Удаление адреса")
    public void deleteRecord(){
        deleteButton.click();
    }
}
