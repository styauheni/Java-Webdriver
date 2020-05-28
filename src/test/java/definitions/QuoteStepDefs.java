package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import pages.QuoteForm;
import pages.QuoteResult;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class QuoteStepDefs {
    QuoteForm form = new QuoteForm();
    QuoteResult result = new QuoteResult();


    @Given("I go to {string} page oop")
    public void iGoToPageOop(String page) {
        form.open();
           }

    @When("I fill out required fields as {string} oop")
    public void iFillOutRequiredFieldsOop(String role)  {

        Map<String, String> user = getData(role);
        form.fillUsername(user.get("username"));
        form.fillEmail(user.get("email"));
        form.fillBothPasswordDields(user.get("password"));
//        form.fillPassword(user.get("password"));
//        form.fillPasswordConfirm(user.get("password"));
//        form.fillName();
//        form.fillFirstName("Yauheni");
//        form.fillLastName("Statsenka");
//        form.saveName();
        form.fillName(user.get("firstName"), user.get("lastName"));
        form.acceptPp();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        form.clickSubmit();
    }

    @And("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsOop(String role) {
        Map<String, String> user = getData(role);
        assertThat(result.getResult()).contains(user.get("username"));
        assertThat(result.getResult()).contains(user.get("email"));
        assertThat(result.getResult()).contains(user.get("firstName"));
        assertThat(result.getResult()).contains(user.get("lastName"));
        assertThat(result.getPassword()).doesNotContain(user.get("password"));
        assertThat(result.getAgreedPrivacy()).isEqualTo("true");
    }

    @When("I clear {string} field")
    public void iClearField(String fieldName) {
        form.clearField(fieldName);
    }

    @And("I uncheck {string} field")
    public void iUncheckField(String fieldName) {

    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String field, String expectedMessage) {
//        if (field.equals("confirmPassword")){
//            if (!form.isConfirmActive()){
//                System.out.println(field + " field is disabled");
//            }
//        }
        String actualMessage = form.getErrorText(field);
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String field) {

        assertThat(form.isErrorDisplayed(field)).isFalse();
    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String field, String value) {
        form.clearField(field);
        switch (field){
            case "username":
                form.fillUsername(value);
                break;
            case "email":
                form.fillEmail(value);
                break;
            case "password":
                form.fillPassword(value);
                break;
            case "confirmPassword":
                form.fillPasswordConfirm(value);
                break;

        }
    }

    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String firstName, String lastName) {
        form.fillName(firstName, lastName);
        }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String field, String name) {
        assertThat(form.getName()).isEqualTo(name);
    }

    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String firstName, String middleName, String lastName) {
        form.fillName(firstName, middleName, lastName);
    }
}
