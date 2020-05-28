package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuoteResult extends Page{

    @FindBy(xpath = "//div[@id='quotePageResult']")
    private WebElement result;
    @FindBy(name = "agreedToPrivacyPolicy")
    private WebElement agreed;
    @FindBy(name = "password")
    private WebElement password;

    public String getResult(){
        return result.getText();
    }
    public String getAgreedPrivacy(){
        return agreed.getText();
    }
    public String getPassword(){
        return password.getText();
    }

}
