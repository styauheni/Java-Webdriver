package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.TestContext;

import static support.TestContext.getWait;


public class CareersLanding extends Page {

    public CareersLanding(){
        url = "https://skryabin-careers.herokuapp.com/";
    }

    @FindBy(xpath = "//a[@href = '/login']/button")
    private WebElement loginButton;
    @FindBy(xpath = "//span[@class='logout-box']/a")
    private WebElement loggedUserName;
    @FindBy(xpath = "//a[@href = '/recruit']/button")
    private WebElement recruitButton;
    @FindBy(xpath = "//h4[contains(text(),'Senior Automation Engineer')]")
    private WebElement saePosition;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement applyButton;
    @FindBy(xpath = "//button[contains(@class,'btn-primary')]")
    private WebElement deleteAccountButton;
    @FindBy(xpath = "//ul")
    private WebElement positionList;


    public void applySae(){
        getWait().until(ExpectedConditions.visibilityOf(saePosition));
        saePosition.click();
        getWait().until(ExpectedConditions.elementToBeClickable(applyButton));
        applyButton.click();
    }
    public void clickLogin(){
        getWait().until(driver -> !isListPresent());
        loginButton.click();
    }
    public String getLoggedUserName(){
        return loggedUserName.getText();
    }
    public void clickRecruit(){
        recruitButton.click();
    }
    public void deletePtofile(){
        loggedUserName.click();
        getWait().until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
        deleteAccountButton.click();
    }
    public boolean isListPresent(){
        return positionList.getText().isEmpty();
    }


}
