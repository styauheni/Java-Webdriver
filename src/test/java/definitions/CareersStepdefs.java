package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import pages.*;
import support.TestContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class CareersStepdefs {
    Map<String, String> position = getPosition("automation");
    Map<String, String> candidate = getData("candidate");


    @Given("I open {string} page")
    public void iOpenPage(String page) {
        switch (page) {
            case "quote":
                new QuoteForm().open();
                break;
            case "careers":
                new CareersLanding().open();
                break;
        }
    }

    @And("I login as {string}")
    public void iLoginAs(String role) {
        new CareersLanding().clickLogin();
        new CareersLogin().login(getData(role));
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        String actualName = new CareersLanding().getLoggedUserName();
        String expectedName = getData(role).get("name");
        assertThat(actualName).isEqualTo(expectedName);
    }

    @When("I remove {string} position")
    public void iRemovePosition(String title) throws InterruptedException {
        new CareersLanding().clickRecruit();
        new CareersRecruit().remvePosition(title);
        new CareersRecruit().waitForPositionDisappear(title);
    }

    @And("I verify {string} position is removed")
    public void iVerifyPositionIsRemoved(String title) {
        assertThat(new CareersRecruit().isPositionVisible(title)).isFalse();
    }

    @When("I create new position")
    public void iCreateNewPosition() throws InterruptedException {
        new CareersLanding().clickRecruit();
        new CareersRecruit().openPosition();
        new CareersPosition().createPosition(position);
    }

    @Then("I verify new position is created")
    public void iVerifyNewPositionIsCreated() {
       assertThat(new CareersRecruit().isPositionVisible(position.get("title")));
    }

    @When("I remove new position")
    public void iRemoveNewPosition() {
        new CareersRecruit().remvePosition(position.get("title"));
        new CareersRecruit().waitForPositionDisappear(position.get("title"));
    }

    @And("I verify new position is removed")
    public void iVerifyNewPositionIsRemoved() {
        assertThat(new CareersRecruit().isPositionVisible(position.get("title"))).isFalse();
    }

    @And("I submit application to a new position")
    public void iSubmitApplicationToANewPosition() {
        new CareersLanding().applySae();
        new CareersApplication().fillOutCandidate(candidate);
    }

    @Then("I verify new candidate is created")
    public void iVerifyNewCandidateIsCreated() {
        String actualName = new CareersLanding().getLoggedUserName();
        String expectedName = candidate.get("firstName") + " " + candidate.get("middleName")+ " " + candidate.get("lastName");
        assertThat(expectedName).isEqualTo(actualName);

    }

    @When("I delete new candidate profile")
    public void iDeleteNewCandidateProfile() {
        new CareersLanding().deletePtofile();
    }

    @Then("I verify new candidate is removed")
    public void iVerifyNewCandidateIsRemoved() {
        new CareersLanding().clickLogin();
        new CareersLogin().login(getData("candidate"));
        assertThat(new CareersLogin().isErrorDisplayed()).isTrue();
    }

}