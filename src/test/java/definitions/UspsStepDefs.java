package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class UspsStepDefs {
    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {

        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'ZipLookupAction!')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@class,'zip-code-address')]")).click();
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        // My way, do not use:
//        getDriver().findElement(By.xpath("//select[@id='tState']")).click();
//        if (state.equalsIgnoreCase("CA")) {
//            getDriver().findElement(By.xpath("//select[@id='tState']/option[@value='CA']")).click();
//
//        } else {
//            throw new RuntimeException("State must be CA");
//        }
//          end of long stupid attempt

        // Dynamic XPAth, good way:
//        getDriver().findElement(By.xpath("//select[@id='tState']/option[@value='" + state + "']")).click();

        // The Best way:
      WebElement stateElement = getDriver().findElement(By.xpath("//select[@id='tState']"));
      new Select(stateElement).selectByValue(state);


//        Thread.sleep(5000);
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zip) {
//        String result = getDriver().findElement(By.xpath("//ul[@class='list-group industry-detail']")).getText();

        // Create Explicit wait:
//        WebDriverWait wait = new WebDriverWait(getDriver(),5);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='zipByAddressDiv']//p")));
//        String result = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText();

        // Another Explicit wait:
//        WebDriverWait wait = new WebDriverWait(getDriver(),5);
//        wait.until(driver -> driver.findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText().length()>0);
//        String result = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText();

        // Best way:
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        wait.until(driver -> !resultElement.getText().isEmpty());
        String result = resultElement.getText();

        assertThat(result).contains(zip);


    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() {
        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'calculate')]")).click();

    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String country, String shape) throws InterruptedException {
        WebElement countryElement = getDriver().findElement(By.xpath("//select[@id='CountryID']"));
        new Select(countryElement).selectByVisibleText(country);
        getDriver().findElement(By.xpath("//input[@value='Postcard']")).click();

    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String quantity) {
        getDriver().findElement(By.xpath("//input[@id='quantity-0']")).sendKeys(quantity);
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePrceAndValidateCostIs(String cost) {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
//        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement resultElemrnt = getDriver().findElement(By.xpath("//div[@id='cost-0']"));
//        wait.until(driver -> !resultElemrnt.getText().equals("$0.00"));
        String result = resultElemrnt.getText();
        assertThat(result).contains(cost);

    }
}