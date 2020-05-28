package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.util.Map;

import static support.TestContext.getWait;

public class CareersPosition extends Page{
    @FindBy(xpath = "//label[@for='positionTitle']/../input")
    private WebElement positionTitle;
    @FindBy(xpath = "//label[@for='positionDescription']/../textarea")
    private WebElement description;
    @FindBy(xpath = "//label[@for='positionCity']/../input")
    private WebElement positionCity;
    @FindBy(xpath = "//select[@class='form-control']")
    private WebElement positionState;
    @FindBy(id = "positionDateOpen")
    private WebElement positionDate;
    @FindBy(id = "positionSubmit")
    private WebElement submitButton;

    public void createPosition(Map<String, String> position){
        positionTitle.sendKeys(position.get("title"));
        description.sendKeys(position.get("description"));
        positionCity.sendKeys(position.get("city"));
        new Select(positionState).selectByValue(position.get("state"));
        positionDate.sendKeys(position.get("date"));
        submitButton.click();
        new CareersRecruit().waitForPositionAppear(position.get("title"));
    }
}
