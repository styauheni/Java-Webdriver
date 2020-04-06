package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.assertj.core.internal.Lists;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class UspsStepDefs {
    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {

        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a")).click();
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
    public void iValidateZipCodeExistsInTheResult(String zip) throws InterruptedException {
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
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));

        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
//        wait.until(driver -> !resultElement.getText().isEmpty());
        wait.until(ExpectedConditions.textToBePresentInElement(resultElement, zip));

        List<WebElement> list = getDriver().findElements(By.xpath("//div[@class='zipcode-result-address']"));
        for (WebElement item : list) {
            String itemText = item.getText();
            System.out.println(itemText);
            assertThat(itemText).contains(zip);

//        String result = resultElement.getText();
//        assertThat(result).contains(zip);
        }

    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[@id='mail-ship-width']")).click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(driver -> getDriver().findElement(By.xpath("//a[contains(@href, 'calculate')]")).isDisplayed());
        getDriver().findElement(By.xpath("//a[contains(@href, 'calculate')]")).click();

    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String country, String shape) throws InterruptedException {
        WebElement countyElement = getDriver().findElement(By.xpath("//select[@id='CountryID']"));
        new Select(countyElement).selectByVisibleText(country);
        getDriver().findElement(By.xpath("//input[@value='" + shape + "']")).click();
//        Thread.sleep(5000);
    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String qtt) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(driver -> getDriver().findElement(By.xpath("//input[@id='quantity-0']")).isEnabled());
        getDriver().findElement(By.xpath("//input[@id='quantity-0']")).sendKeys(qtt);

    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String cost) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        wait.until(driver -> getDriver().findElement(By.xpath("//input[@value='Calculate']")).isEnabled());
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
        WebDriverWait waitRes = new WebDriverWait(getDriver(), 5);
        waitRes.until(driver -> !getDriver().findElement(By.xpath("//div[@id='total']")).getText().contains("$0.00"));
        String result = getDriver().findElement(By.xpath("//div[@id='total']")).getText();
        assertThat(result).isEqualTo(cost);
    }

    @When("I go to Find Location page")
    public void iGoToFindLocationPage() {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-find']//a")).click();

    }

    @And("I enter zip code {string}")
    public void iEnterZipCode(String zip) {
        getDriver().findElement(By.xpath("//input[@id='city-state-input']")).sendKeys(zip);
    }

    @And("I select location type {string}")
    public void iSelectLocationType(String locType) throws InterruptedException {
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'"+ locType + "')]")).click();

    }

    @And("I select service {string} within {string}")
    public void iSelectServiceWithin(String service, String distance) {
        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//li[contains(@class,'post-list')]//a[contains(text(),'"+service+"')]")).click();
        getDriver().findElement(By.xpath("//button[@id='within-select']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'" + distance +"')]")).click();

    }
    @And("I search for the location address")
    public void iSearchForTheLocationAddress() {
        getDriver().findElement(By.xpath("//a[@id='searchLocations']")).click();
    }

    @Then("I should see city {string} in the search result.")
    public void iShouldSeeCityInTheSearchResult(String city) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@id='resultBox']"));
        wait.until(driver -> !resultElement.getText().isEmpty());
        String result = resultElement.getText();
        assertThat(result).containsIgnoringCase(city);

    }



    @When("I go to Find a Location page")
    public void iGoToFindALocationPage() {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        new Actions(getDriver()).moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-find']//a")).click();
    }

    @And("I filter by {string} Location Types, {string} Services, {string} Available Services")
    public void iFilterByLocationTypesServicesAvailableServices(String locationType, String service, String availableServise) throws InterruptedException {
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("//a[string()='"+ locationType +"']")).click();
        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//li[@id='pickupPo']//a[contains(text(),'"+service+"')]")).click();
        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        getDriver().findElement(By.xpath("//a[text()= '" + availableServise + "']")).click();
//        Thread.sleep(5000);

    }

    @And("I fill in {string} street, {string} city, {string} state")
    public void iFillInStreetCityState(String street, String city, String state) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();
        WebElement address = getDriver().findElement(By.xpath("//input[@id='addressLineOne']"));
//        WebDriverWait wait = new WebDriverWait(getDriver(),5);
//        wait.until(ExpectedConditions.visibilityOf(address));

        address.sendKeys(street);
        for (int i = 0; !address.getAttribute("value").contains(street) && i<5; i++){
            address.clear();
            address.sendKeys(street);
        }

        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(city);
        WebElement stateElement = getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']"));
        new Select(stateElement).selectByValue(state);
        getDriver().findElement(By.xpath("//a[text()='Go to Results']")).click();

    }

    @Then("I print the phone number and validate is {string}")
    public void iPrintThePhoneNumberAndValidateIs(String phone) {
        getDriver().findElement(By.xpath("(//div[@id='resultBox']//span)[1]")).click();
        String phoneResult = getDriver().findElement(By.xpath("//p[@class='ask-usps']")).getText();
        assertThat(phoneResult.contains(phone));

    }

    @When("I go to {string} tab")
    public void iGoToTab(String tabName) {
        getDriver().findElement(By.xpath("//a[@class='menuitem'][text()='Help']")).click();

    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String search) throws InterruptedException {
        WebElement searchField = getDriver().findElement(By.xpath("//div[contains(@class,'search-field-wrapper')]/input"));
        searchField.sendKeys(search + Keys.ENTER);
//        getDriver().findElement(By.xpath("(//span[@class='search-input-group']/button)[1]")).click();

    }

    @Then("I verify that no results of {string} available in the search")
    public void iVerifyThatNoResultsOfAvailableInTheSearch(String mySearch) {
        WebElement resultElement = getDriver().findElement(By.xpath("//ul[@class='slds-has-dividers--bottom']"));
        new WebDriverWait(getDriver(),5).until(driver -> resultElement.isDisplayed());
        String result = resultElement.getText();
//        System.out.println(result);
        assertThat(result).doesNotContain(mySearch);
    }

    @When("I  go to {string} under {string}")
    public void iGoToUnder(String menu, String header) throws InterruptedException {
        WebElement business = getDriver().findElement(By.xpath("//a[@class='menuitem'][text()='" + header + "']"));
        getActions().moveToElement(business).perform();
        getDriver().findElement(By.xpath("//a[contains(text(),'"+ menu +"')]")).click();
    }

    @And("I search for {string}")
    public void iSearchFor(String search) {
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(search);
        getDriver().findElement(By.xpath("//form[@id='formSearch']//button[@type='submit']")).click();
    }

    @And("I click{string} on the map")
    public void iClickOnTheMap(String show) throws InterruptedException {
        WebElement button = getDriver().findElement(By.xpath("//a[contains(text(),'"+ show +"')]"));
        WebElement loader = getDriver().findElement(By.xpath("//div[@id='eddm_overlay-progress']"));
        getWait().until(ExpectedConditions.visibilityOf(loader));
        getWait(10).until(ExpectedConditions.invisibilityOf(loader));
        button.click();
    }

    @And("I click {string} on the table")
    public void iClickOnTheTable(String selection) throws InterruptedException {
        WebElement selectElemrnt = getDriver().findElement(By.xpath("//div[@id='grid']/a[contains(text(),'"+ selection +"')]"));
        getWait(10).until(driver -> selectElemrnt.isEnabled());
        selectElemrnt.click();

    }

    @And("I close modal window")
    public void iCloseModalWindow() {
        getDriver().findElement(By.xpath("//button[@id='dropOffDone']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() {
        //get the number of total expected elements:
        String expectedString = getDriver().findElement(By.xpath("//a[contains(@class,'totalsArea')]")).getText();
        String expectedCountString = expectedString.replaceAll("\\D*", "");
        int expectedCount = Integer.parseInt(expectedCountString);

        //I have not a full list visible
        //I need to scroll to the last element so more of them loaded (infinite scroll)
        // 1. get list of elements:
        By listSelector = By.xpath("//div[@class='dojoxGridContent']//td[@idx='7']");
        List<WebElement> list = getDriver().findElements(listSelector);
        // 2. get last element and move mouse over it:
        WebElement lastElement =  list.get(list.size()-1);
        getActions().moveToElement(lastElement).perform();
        // 3. wait until the last element loaded:
        getWait().until(ExpectedConditions.numberOfElementsToBe(listSelector, expectedCount));
        //calculate the number of elements again:
        list = getDriver().findElements(listSelector);

        double cost = 0;
        for (WebElement item : list){
            String itemText = item.getAttribute("textContent").replace("$", "");
            double itemCost = Double.parseDouble(itemText);
            cost += itemCost;
        }
        System.out.println("result is " + cost);

        String expectedTotalString = getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText();
        double expectedTotal = Double.parseDouble(expectedTotalString);

        assertThat(cost).isCloseTo(expectedTotal, Percentage.withPercentage(1));

         }

    @When("I perform {string} search")
    public void iPerformSearch(String mySearch) {
//        getDriver().findElement(By.xpath("//input[@id='home-input']")).sendKeys(mySearch + Keys.ENTER);
        WebElement searchElement = getDriver().findElement(By.xpath("//li[contains(@class,'nav-search')]/a[@class='menuitem']"));
        WebElement searchField = getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']"));
        getActions().moveToElement(searchElement).
                    sendKeys(searchField, mySearch, Keys.ENTER).
                    perform();
    }

    @And("I set {string} in filters")
    public void iSetInFilters(String filter) throws InterruptedException {
        WebElement filterElement = getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][contains(text(),'"+ filter +"')]"));
        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
        getWait().until(driver -> !spinner.isDisplayed());
        filterElement.click();
//        getExecutor().executeScript("arguments[0].click", filterElement);
    }

    @Then("I verify that {string} result found")
    public void iVerifyThatResultFound(String results) throws InterruptedException {
        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
        getWait().until(driver -> !spinner.isDisplayed());

//        String result = getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText();
//        System.out.println(result);
//        assertThat(result).contains(results);
        int resultInt = Integer.parseInt(results);
        List<WebElement> count = getDriver().findElements(By.xpath("//div[@id='main_res']//li"));
        int resultCount = count.size();
        assertThat(resultCount).isEqualTo(resultInt);

    }

    @When("I select {string} in results")
    public void iSelectInResults(String myString) {
        getDriver().findElement(By.xpath("//span[contains(text(),'"+ myString +"')]")).click();
    }

    @And("I click {string} button")
    public void iClickButton(String button) throws InterruptedException {
                getDriver().findElement(By.xpath("//a[contains(text(),'"+ button +"')]")).click();

        }



    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() {
        String startTab = getDriver().getWindowHandle();
        for (String nextWindow : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(nextWindow);
        }
        getWait().until(ExpectedConditions.titleContains("Sign In"));
        WebElement username = getDriver().findElement(By.xpath("//input[@id='username']"));
        assertThat(username.isDisplayed()).isTrue();

    }
}