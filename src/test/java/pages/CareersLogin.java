package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class CareersLogin extends Page   {

    @FindBy(xpath = "//label[@for='loginUsername']/../input")
    private WebElement username;

    @FindBy(xpath = "//input[@type= 'password']")
    private WebElement password;

    @FindBy(id = "loginButton")
    private WebElement submitButton;
    @FindBy(xpath = "//div[contains(@class,'alert')]")
    private WebElement loginError;

    public void fillUserbame(String value){
        username.sendKeys(value);
        }
    public void fillPassword(String value){
        password.sendKeys(value);
    }
    public void submitLogin(){
        submitButton.click();
    }
    public void login(Map<String, String> user){
        username.sendKeys(user.get("email"));
        password.sendKeys(user.get("password"));
        submitButton.click();
    }
    public boolean isErrorDisplayed(){
        return loginError.isDisplayed();
    }

}
