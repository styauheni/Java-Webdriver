package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import support.TestContext;


import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class MarketStepsDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "google":
                getDriver().get("https://google.com");
                break;
            default:
//                System.out.println("Not recognized page " + page);
                throw new RuntimeException("Not recognized page "+page);


        }

    }

    @And("I print page details in the console")
    public void iPrintPageDetailesInTheConsole() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getPageSource());

    }

    @And("I go back and forward, then refresh the page")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
    }

    @When("I fill out required fields")
    public void iFillOutRequiredFields() {
        getDriver().findElement(By.xpath("//input[@name='username']")).click();
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("YS");
        getDriver().findElement(By.xpath("//input[@name='email']")).click();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ys@test.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).click();
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).click();
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='name']")).click();
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("Yauheni");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Statsenka");
        getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")).click();
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();

    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }

    @And("I change resolution to {string}")
    public void iChangeResolutionTo(String mode) {
        if (mode.equals("phone")){
            getDriver().manage().window().setSize(new Dimension(400,768));
        }else if (mode.equals("desctop")){
            getDriver().manage().window().setSize(new Dimension(1024,768));
        }else{
            throw new RuntimeException("Not recognized size "+ mode);
        }
    }

    @When("I verify email field behavior")
    public void iVerifyEmailFieldBehavior() {
        getDriver().findElement(By.xpath("//input[@name='email']")).click();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ystest");
        getDriver().findElement(By.xpath("//input[@name='username']")).click();
        assertThat(getDriver().findElement(By.xpath("//label[@id='email-error']")).isDisplayed()).isTrue();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.BACK_SPACE);
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ys@test.com");
        assertThat(getDriver().findElement(By.xpath("//label[@id='email-error']")).isDisplayed()).isFalse();
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();

    }

    @And("I verify that submitted fields saved correctly")
    public void iVerifySubmittedFields() {
        assertThat(getDriver().findElement(By.xpath("//b[@name='username']")).getText().equalsIgnoreCase("ys")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='lastName']")).getText().equalsIgnoreCase("statsenka")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='firstName']")).getText().equalsIgnoreCase("yauheni")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='name']")).getText().equalsIgnoreCase("yauheni statsenka")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='email']")).getText().equalsIgnoreCase("ys@test.com")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='password']")).getText().equalsIgnoreCase("[entered]")).isTrue();
        assertThat(getDriver().findElement(By.xpath("//b[@name='agreedToPrivacyPolicy']")).getText().equalsIgnoreCase("true")).isTrue();


    }

    @And("I fill out optional fields")
    public void iFillOutOptionalFields() {
        getDriver().findElement(By.xpath("//input[@name='phone']")).click();
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@id='dateOfBirth']")).click();
        getDriver().findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();
        getDriver().findElement(By.xpath("//select[@class='ui-datepicker-month']/option[2]")).click();
        getDriver().findElement(By.xpath("//select[@class='ui-datepicker-year']/*[@value='1904']")).click();
        getDriver().findElement(By.xpath("//*[@data-year='1904']/a[text()='8']")).click();
        getDriver().findElement(By.xpath("//input[@value='male']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("My New Address");
        getDriver().findElement(By.xpath("//*[@name='carMake']/*[@value='Ford']")).click();
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().accept();


    }
}
