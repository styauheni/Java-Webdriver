package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import support.TestContext;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;




public class MarketStepsDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "usps":
                getDriver().get("https://www.usps.com/");
                break;
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

    }

    @And("I go back and forward, then refresh the page")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
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


    @When("I fill out required fields")
    public void iFillOutRequiredFields() {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("YS");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ys@test.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("Yauheni Statsenka");
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();


    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }

    @And("I verify required fields")
    public void iFerifyRequiredFields() {
        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
//        System.out.println(result);

        assertThat(result).contains("YS");
        assertThat(result).contains("ys@test.com");
        assertThat(result).containsIgnoringCase("Yauheni Statsenka");
        assertThat(result).doesNotContain("12345");

        String privacyPolicy = getDriver().findElement(By.xpath("//b[@name = 'agreedToPrivacyPolicy']")).getText();
        assertThat(privacyPolicy).isEqualTo("true");
    }

    @And("I fill out optional fields")
    public void iFillOutOptionalFields() {
//        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']")).click();
//        WebElement countySelect = getDriver().findElement(By.xpath("//option[@value='China']"));

        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        // date of birth element
        getDriver().findElement(By.xpath("//input[@id='dateOfBirth']")).click();
        getDriver().findElement(By.xpath("//select[@data-handler='selectMonth']/option[11]")).click();
        getDriver().findElement(By.xpath("//select[@data-handler='selectYear']/option[@value='2000']")).click();
        getDriver().findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='7']")).click();
        getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']/option[@value='Austria']")).click();
        getDriver().findElement(By.xpath("//input[@value='male']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("My New Address");
        getDriver().findElement(By.xpath("//select[@name='carMake']/*[@value='Ford']")).click();
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().accept();

    }

    @And("I verify optional fields")
    public void iVerifyOptionalFields() {
        String actPhNum = getDriver().findElement(By.xpath("//b[@name='phone']")).getText();
        assertThat(actPhNum).isEqualTo("123456789");
        String actDOB = getDriver().findElement(By.xpath("//b[@name='dateOfBirth']")).getText();
        assertThat(actDOB).isEqualTo("11/07/2000");
        String actCOA = getDriver().findElement(By.xpath("//b[@name='countryOfOrigin']")).getText();
        assertThat(actCOA).isEqualTo("Austria");
        String actGender = getDriver().findElement(By.xpath("//b[@name='gender']")).getText();
        assertThat(actGender).isEqualTo("male");
        String allowedToContact = getDriver().findElement(By.xpath("//b[@name='allowedToContact']")).getText();
        assertThat(allowedToContact).isEqualTo("true");
        String thirdParty = getDriver().findElement(By.xpath("//b[@name='thirdPartyAgreement']")).getText();
        assertThat(thirdParty).isEqualTo("accepted");
        String result = getDriver().findElement(By.xpath("//div[@id='quotePageResult']")).getText();
        assertThat(result).containsIgnoringCase("my new address");
        assertThat(result).containsIgnoringCase("ford");
    }

}
