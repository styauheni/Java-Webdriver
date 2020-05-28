package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.util.Map;

import static support.TestContext.*;

public class CareersApplication extends Page {
    @FindBy(xpath = "//label[@for='candidateFirstName']/../input")
    private WebElement firstName;
    @FindBy(xpath = "//label[@for='candidateMiddleName']/../input")
    private WebElement middleName;
    @FindBy(xpath = "//label[@for='candidateLastName']/../input")
    private WebElement lastName;
    @FindBy(xpath = "//label[@for='candidateEmail']/../input")
    private WebElement email;
    @FindBy(xpath = "//label[@for='candidatePassword']/../input")
    private WebElement password;
    @FindBy(xpath = "//label[@for='candidateConfirmPassword']/../input")
    private WebElement confirmPassord;
    @FindBy(xpath = "//label[@for='candidateSummary']/../textarea")
    private WebElement summary;
    @FindBy(xpath = "//label[text()='Address']/../input")
    private WebElement address;
    @FindBy(xpath = "//label[@for='candidateCity']/../input")
    private WebElement city;
    @FindBy(xpath = "//label[@for='candidateState']/../select")
    private WebElement candidateState;
    @FindBy(xpath = "//label[@for='candidateZip']/../input")
    private WebElement zip;
    @FindBy(id = "candidateSubmit")
    private WebElement submitButton;

    public void selectState(String state){
        new Select(candidateState).selectByValue(state);
    }
    public void fillOutCandidate(Map<String, String> candidate){
        getWait().until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys(candidate.get("firstName"));
        middleName.sendKeys(candidate.get("middleName"));
        lastName.sendKeys(candidate.get("lastName"));
        email.sendKeys(candidate.get("email"));
        password.sendKeys(candidate.get("password"));
        confirmPassord.sendKeys(candidate.get("password"));
        summary.sendKeys(candidate.get("summary"));
        address.sendKeys(candidate.get("address"));
        city.sendKeys(candidate.get("city"));
        selectState(candidate.get("state"));
        zip.sendKeys(candidate.get("zip"));
        submitButton.click();
    }

}
