package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static support.TestContext.*;

public class CareersRecruit extends Page {
    @FindBy(xpath = "//h4[text() = 'New Position']")
    private WebElement newPosition;


    // dynamic element:
    private WebElement positionCard(String title){
        return getDriver().findElement(By.xpath("//h4[text()='" + title + "']/../../.."));
    }
    private WebElement closeForPosition(String title){
        return getDriver().findElement(By.xpath("//h4[text()='" + title + "']/../../..//button"));
    }
    public boolean isPositionVisible(String title){
        try {
            return positionCard(title).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    public void remvePosition(String title){
        WebElement card = positionCard(title);
        WebElement close = closeForPosition(title);
        getActions().moveToElement(card).perform();
        close.click();
    }
    public void waitForPositionDisappear(String title){
        getWait().until(ExpectedConditions.invisibilityOf(positionCard(title)));
    }
    public void waitForPositionAppear(String title){
        getWait().until(ExpectedConditions.visibilityOf(positionCard(title)));
    }
    public void openPosition(){
        newPosition.click();
    }
}
