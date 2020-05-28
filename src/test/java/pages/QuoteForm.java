package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import support.TestContext;

import static support.TestContext.*;

public class QuoteForm extends Page {
    public QuoteForm(){
        url = "https://skryabin.com/market/quote.html";
    }


    @FindBy(xpath = "//input[@name='username']")
    private WebElement username;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "firstName")
    private WebElement firstName;
    @FindBy(id = "middleName")
    private WebElement middleName;
    @FindBy(id = "lastName")
    private WebElement lastName;
    @FindBy(xpath = "//span[contains(text(),'Save')]")
    private WebElement saveButton;
    @FindBy(name = "agreedToPrivacyPolicy")
    private WebElement privacyPolicy;
    @FindBy(xpath = "//button[@id='formSubmit']")
    private WebElement submitButton;

    private WebElement errorElement(String fieldName){
        return getDriver().findElement(By.id(fieldName + "-error"));
    }
    public boolean isConfirmActive(){
        return confirmPassword.isEnabled();
    }
    public String getErrorText(String fieldName){
        return errorElement(fieldName).getText();
    }
    public boolean isErrorDisplayed(String fieldName){
        return errorElement(fieldName).isDisplayed();
    }
    public void fillUsername(String value){
        username.sendKeys(value);
    }
    public void fillEmail(String value){
        email.sendKeys(value);
    }
    public void fillPassword(String value){
        password.sendKeys(value);
    }
    public void fillPasswordConfirm(String value){
        confirmPassword.sendKeys(value);
    }
    public void fillBothPasswordDields(String value){
        password.sendKeys("value");
        confirmPassword.sendKeys("value");
    }
    public void fillName(){
        name.click();
    }
    public void fillName(String first, String last){
        name.click();
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        saveButton.click();
    }
    public void fillName(String first, String middle, String last){
        name.click();
        firstName.sendKeys(first);
        middleName.sendKeys(middle);
        lastName.sendKeys(last);
        saveButton.click();
    }
    public void fillFirstName(String value){
        firstName.sendKeys(value);
    }
    public void fillLastName(String value){
        lastName.sendKeys(value);
    }
    public void saveName(){
        saveButton.click();
    }
    public void acceptPp(){
        privacyPolicy.click();
    }
    public void clickSubmit(){
        submitButton.click();
    }

    public void clearField(String fieldName){
        switch (fieldName){
            case "username":
                username.clear();
                break;
            case "email":
                email.clear();
                break;
            case "password":
                password.clear();
                break;
            case "confirmPassword":
                if (confirmPassword.isEnabled()) {
                    confirmPassword.clear();
            }
                break;
            case "name":
                name.clear();
                break;
            default:
                throw new RuntimeException("Unknownfield: " + fieldName);
        }
    }
    public String getName(){
        return name.getAttribute("value");
    }
}
