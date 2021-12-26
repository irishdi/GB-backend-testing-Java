import Lesson_8.AccountPage;
import Lesson_8.LoginBlock;
import Lesson_8.LoginPage;
import Lesson_8.MainPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {

    @Given("User is authorized on a website")
    public void userIsAuthorizedOnAWebsite() throws InterruptedException {
        open("https://www.lookfantastic.ru/");
        new MainPage().closeContainer();
        new MainPage().clickMyAccountButton();
        new LoginBlock().clickLoginButton();
        new LoginPage().
                fillEmailInput("fewoj49881@nefacility.com").
                fillPasswordInput("<ercbhjdrf01").
                submitLoginData();

    }

    @And("is present on AccountPage")
    public void isPresentOnAccountPage() {
        new AccountPage().checkAccountPage();
    }

    String name = "Tester Test Tester";

    @When("I fill in form details")
    public void iFillInFormDetails() {

        new AccountPage().clickAddressButton().
                clickNewAddressButton().
                fillFullNameInput(name).
                fillPostCodeInput("190000").
                fillHouseNumberInput("31").
                fillStreetNameInput("Korablestroiteley 16").
                fillCityInput("St. Petersburg").
                fillPhoneInput("+7921558899");
    }

    @And("I submit data")
    public void iSubmitData(){
        new AccountPage().clickSubmitAddressButton();
    }

    @Then("added Address is visible")
    public void addedAddressIsVisible() {
        new AccountPage().checkAddedAddress(name).deleteRecord();
    }
}
