package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class upsStepdefs {
    @When("I open the {string} page")
    public void iOpenThePage(String page) {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks1']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'"+ page +"')]")).click();

    }

    @And("I fill out required all fields")
    public void iFillOutRequiredAllFields() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Administrator");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("4970 El Camino Real");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("94022");
//        getDriver().findElement(By.xpath("//input[@id='origincity']")).sendKeys("Los Altos");
//        WebElement selectState = getDriver().findElement(By.xpath("//select[@id='originstate']"));
//        new Select(selectState).selectByVisibleText("California");
        getWait().until(ExpectedConditions.attributeContains(By.xpath("//input[@id='origincity']"), "class", "ng-valid"));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("mail@example.com");
        WebElement phone = getDriver().findElement(By.xpath("//input[@id='originphone']"));
        phone.sendKeys("9164567890");
        if (getDriver().findElement(By.xpath("//div[@role= \"alert\"]")).isDisplayed()){
            getDriver().findElement(By.xpath("//div[@role= \"alert\"]/button")).click();
        }
    }

    @And("I submit the Shipping form")
    public void iSubmitTheShippingForm() {
        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
    }

    @Then("I verify submitted data saved successfully")
    public void iVerifySubmittedDataSavedSuccessfully() throws InterruptedException {

        WebElement resultElement = getDriver().findElement(By.xpath("//div[@class='ups-section']"));
        String result = resultElement.getText();

        System.out.println(result);
        assertThat(result).contains("Administrator");
        assertThat(result).contains("4970 El Camino Real");
        assertThat(result).contains("94022");
        assertThat(result).contains("Los Altos");
        assertThat(result).contains("CA");
        assertThat(result).contains("mail@example.com");
        assertThat(result).contains("9164567890");
    }

    @When("I cancel the form")
    public void iCancellTheForm() throws InterruptedException {
        WebElement cancelElement = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        getActions().moveToElement(cancelElement).perform();
        cancelElement.click();
//        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();

    }

    @Then("I verify form reset successfully.")
    public void iVerifyFormResetSuccessfully() {
        List<WebElement> fields = getDriver().findElements(By.xpath("//div[contains(@class,'required')]//input"));
        for (WebElement field : fields){
            assertThat(field.getText().isEmpty());
        }
    }

    @When("I open Shipping menu")
    public void iOpenShippingMenu() {
        getDriver().findElement(By.id("ups-menuLinks1")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();
    }

    @And("I fill out origin shipments fields")
    public void iFillOutOriginShipmentsFields() {
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Administrator");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("4970 El Camino Real");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("94022");
        getWait().until(ExpectedConditions.attributeContains(By.xpath("//input[@id='origincity']"), "class", "ng-valid"));
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("mail@example.com");
        WebElement phone = getDriver().findElement(By.xpath("//input[@id='originphone']"));
        phone.sendKeys("1234567890");
        if (getDriver().findElement(By.xpath("//div[@role= \"alert\"]")).isDisplayed()){
            getDriver().findElement(By.xpath("//div[@role= \"alert\"]/button")).click();
        }
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@class='ups-section']"));
        String result = resultElement.getText();

        System.out.println(result);
        assertThat(result).contains("Administrator");
        assertThat(result).contains("4970 El Camino Real");
        assertThat(result).contains("94022");
        assertThat(result).contains("mail@example.com");
        assertThat(result).contains("1234567890");

    }

    @When("I fill out destination shipment fields")
    public void iFillOutDestinationShipmentFields() {
        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys("John Doe");
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys("870 7th Ave");
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys("10019");
        WebElement cityElement = getDriver().findElement(By.xpath("//input[@name='city']"));
        getWait().until(driver -> cityElement.getAttribute("class").contains("ng-valid"));
        String city = cityElement.getAttribute("value");
        assertThat(city).isEqualTo("NEW YORK");
        assertThat(getDriver().findElement(By.xpath("//option[contains(text(),'New York')]")).isDisplayed()).isTrue();
        getDriver().findElement(By.xpath("//button[text()='Continue']")).click();

    }

    @Then("I verify total charges appear")
    public void iVerifyTotalChargesAppear() {

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nbsBalanceBarContainer']")));
        assertThat(getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText().isEmpty()).isFalse();

    }

    @When("I submit the shipment form")
    public void iSubmitTheShipmentForm() {
        WebElement button = getDriver().findElement(By.xpath("//b[text()='See All']"));
        getActions().moveToElement(button).perform();
        getWait().until(driver ->button.isEnabled());
        getDriver().findElement(By.xpath("//button[text() ='Continue']")).click();
    }

    @And("I set packaging type")
    public void iSetPackagingType() {
        WebElement typeElement = getDriver().findElement(By.xpath("//select[@name='nbsPackagePackagingTypeDropdown0']"));
        new Select(typeElement).selectByVisibleText("UPS Express Box - Small");
        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("1");
    }

    @Then("I verify total charges changed")
    public void iVerifyTotalChargesChanged() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='nbsBalanceBarContainer']")));
        assertThat(getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText().isEmpty()).isFalse();
    }

    @And("I select chipest delivery option")
    public void iSelectChipestDeliveryOption() {
        List<WebElement> deliveryOptions = getDriver().findElements(By.xpath("//div[contains(@class,'upsell-tiles')]//p[@class = 'ng-star-inserted']/strong"));

        double myPrice = Double.MAX_VALUE;

        for (WebElement deliveryOption : deliveryOptions) {
            String priceText = deliveryOption.getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            if (myPrice > price) {
                myPrice = price;
            }
            }
        WebElement button = getDriver().findElement(By.xpath("//a[@class='ups-footer_social-facebook']"));
        getActions().moveToElement(button).perform();
        String myPriceText = Double.toString(myPrice);
        getDriver().findElement(By.xpath("//strong[contains(text(),'" + myPriceText + "')]")).click();
    }

    @And("I set Saturday delivery type")
    public void iSetSaturdayDeliveryType() throws InterruptedException {
            getDriver().findElement(By.xpath("//input[@id='nbsShipmentDescription']")).sendKeys("No");
            getDriver().findElement(By.xpath("//strong[contains(text(),'Saturday Delivery')]")).click();
            getDriver().findElement(By.xpath("//button[text()='Continue']")).click();
            Thread.sleep(5000);

        }


    @And("I select Paypal payment type")
    public void iSelectPaypalPaymentType() {
    }

    @Then("I review all recorded details on the review page")
    public void iReviewAllRecordedDetailsOnTheReviewPage() {
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {
    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {
    }
}
