package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.security.Key;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;


public class MarketStepsDefs {
    @Given("I go to {string} page")
    public void iGoToPage(String page) {
        switch (page) {
            case "usps":
                getDriver().get("https://www.usps.com/");
                break;
            case "ups":
                getDriver().get("https://www.ups.com/us/en/Home.page?");
                break;
            case "quote":
                getDriver().get("https://skryabin.com/market/quote.html");
                break;
            case "google":
                getDriver().get("https://google.com");
                break;
                case "yahoo":
                getDriver().get("https://www.yahoo.com/");
                break;
            case "converter":
                getDriver().get("https://www.unitconverters.net/");
                break;
            case "calculator":
                getDriver().get("https://www.calculator.net/auto-loan-calculator.html");
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
    public void iFillOutRequiredFields() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("YS");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("ys@test.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("12345");
        getDriver().findElement(By.xpath("//input[@id='name']")).click();
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("Yauheni");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Statsenka");
        getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")).click();
        getDriver().findElement(By.xpath("//input[contains(@name, 'PrivacyPolicy')]")).click();

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
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//input[@id='dateOfBirth']")).click();
        getDriver().findElement(By.xpath("//select[@data-handler='selectMonth']/option[@value='10']")).click();
        getDriver().findElement(By.xpath("//select[@data-handler='selectYear']/*[@value='2000']")).click();
        getDriver().findElement(By.xpath("//td[@data-handler='selectDay']//a[text()='7']")).click();
        WebElement countryElement = getDriver().findElement(By.xpath("//select[@name='countryOfOrigin']"));
        new Select(countryElement).selectByValue("Austria");
        getDriver().findElement(By.xpath("//input[@value='male']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("My New Address");
        getDriver().findElement(By.xpath("//option[@value='Ford']")).click();
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

    @And("I print logs to the console")
    public void iPrintLogsToTheConsole() {
        LogEntries logs = getDriver().manage().logs().get("browser");
        System.out.println(">>>>>browser log begin");
        for (LogEntry log : logs)
        System.out.println(log);
        System.out.println(">>>>>browser log end");
    }

    @And("I fill multi-select")
    public void iFillMultiSelect() throws InterruptedException {
        WebElement ford = getDriver().findElement(By.xpath("//select[@name='carMake']//option[@value='Ford']"));
        WebElement bmw = getDriver().findElement(By.xpath("//select[@name='carMake']//option[@value='BMW']"));

        new Actions(getDriver()).click(ford)
                .keyDown(Keys.COMMAND)
                .click(bmw).perform();
        Thread.sleep(5000);
    }

    @When("I click on {string}")
    public void iClickOn(String unit) {
        getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'" + unit + "')]")).click();

    }

    @And("I set {string} to {string}")
    public void iSetTo(String unitFrom, String unitTo) throws InterruptedException {
        WebElement selectFrom = getDriver().findElement(By.xpath("//select[@id='calFrom']"));
        WebElement selectTo = getDriver().findElement(By.xpath("//select[@id='calTo']"));
//        WebDriverWait wait = new WebDriverWait(getDriver(),5);
//        wait.until(ExpectedConditions.visibilityOf(selectFrom));
        new Select(selectFrom).selectByVisibleText(unitFrom);
        new Select(selectTo).selectByVisibleText(unitTo);
    }

    @Then("I enter into Form field {string} and ferify {string} result")
    public void iEnterIntoFormFieldAndFerifyRsult(String valFrom, String valTo) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(valFrom);
        String result = getDriver().findElement(By.xpath("//input[@name='toVal']")).getAttribute("value");
        assertThat(result).contains(valTo);
    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).clear();
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).clear();
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).clear();
    }

    @And("I calculate")
    public void iCalculate() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
//        Thread.sleep(2000);
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String message) {
        String error = getDriver().findElement(By.xpath("//a[@name='autoloanresult']/..")).getText();
        assertThat(error).containsIgnoringCase(message);
    }

    @And("I enter {string} price, {string} month, {string} interest, {string} downpayment, {string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthInterestDownpaymentTradeInStatePercentTaxFees(String price, String month, String interest, String down, String tradeIn, String state, String tax, String fees) {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(month);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(down);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(tradeIn);
        new Select(getDriver().findElement(By.xpath("//select[@name='cstate']"))).selectByVisibleText(state);
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(tax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);
    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String pay) {
        String result = getDriver().findElement(By.xpath("//a[@name='autoloanresult']/..")).getText();
        assertThat(result).contains(pay);
    }

    @When("I click {string}")
    public void iClick(String arg0) {
        getDriver().findElement(By.xpath("//button[contains(text(),'3rd party agreement')]")).click();
    }

    @And("I decline agreement")
    public void iDeclineAgreement() {
        getDriver().switchTo().alert().dismiss();
    }

    @Then("I verify that agreement declined")
    public void iVerifyThatAgreementDeclined() {
        String message = getDriver().findElement(By.xpath("//span[@id='thirdPartyResponseMessage']")).getText();
        assertThat(message.equals("You did not accept third party agreement."));
    }

    @And("I accept agreement")
    public void iAcceptAgreement() {
        getDriver().switchTo().alert().accept();
    }

    @Then("I verify that agreement accepted.")
    public void iVerifyThatAgreementAccepted() {
        String yesMessage = getDriver().findElement(By.xpath("//span[@id='thirdPartyResponseMessage']")).getText();
        assertThat(yesMessage.equals("You accepted third party agreement."));
    }

    @When("I fill out additional info")
    public void iFillOutAdditionalInfo() throws InterruptedException {
        WebElement iframe = getDriver().findElement(By.xpath("//iframe[@name='additionalInfo']"));
        getDriver().switchTo().frame(iframe);
        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys("My Friend");
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys("112233445");
        getDriver().switchTo().defaultContent();

    }

    @And("I verify that {string} present in related documents")
    public void iVrifyThatPresentInRelatedDocuments(String myDoc) {
        String firstWindow = getDriver().getWindowHandle();
        getDriver().findElement(By.xpath("//input[@id='relatedDocuments']/../button")).click();

        for (String nextWindow : getDriver().getWindowHandles()){
            getDriver().switchTo().window(nextWindow);
        }
        String newPage = getDriver().findElement(By.xpath("//body")).getText();
        assertThat(newPage).contains(myDoc);

        getDriver().switchTo().window(firstWindow);
    }

    @And("I submit using JavaScript")
    public void iSubmitUsingJavaScript() {
        WebElement buttonElement = getDriver().findElement(By.xpath("//button[@id='formSubmit']"));
        getExecutor().executeScript("arguments[0].click()",buttonElement);

    }
}
