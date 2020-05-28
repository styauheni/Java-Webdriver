package pages;

import org.openqa.selenium.support.PageFactory;

import static support.TestContext.getDriver;

public class Page {

    protected String url;

    // constructor:
    public Page(){
        PageFactory.initElements(getDriver(), this);
    }

    public void open(){
        getDriver().get(url);
    }
}
