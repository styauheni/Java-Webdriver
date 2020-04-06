package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.TestContext;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getActions;
import static support.TestContext.getDriver;

public class upsStepdefs {
    @When("I open {string} page")
    public void iOpenPage(String page) {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks1']")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'"+ page +"')]")).click();

    }

    @And("I fill out required all fields")
    public void iFillOutRequiredAllFields() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Yauheni");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("8880 Rio San Diego Dr");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("92108");
        getDriver().findElement(By.xpath("//input[@id='origincity']")).sendKeys("San Diego");
        WebElement selectState = getDriver().findElement(By.xpath("//select[@id='originstate']"));
        new Select(selectState).selectByVisibleText("California");
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("yauheni@test.com");
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys("9164567890" + Keys.TAB);

    }

    @And("I submit the Shipping form")
    public void iSubmitTheShippingForm() {
        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
    }

    @Then("I verify submitted data saved successfully")
    public void iVerifySubmittedDataSavedSuccessfully() throws InterruptedException {
        Thread.sleep(5000);
        String result = getDriver().findElement(By.xpath("//div[@class='ups-section']")).getText();
//        System.out.println(result);
        assertThat(result).contains("Yauheni");
        assertThat(result).contains("8880 Rio San Diego Dr");
        assertThat(result).contains("92108");
        assertThat(result).contains("San Diego");
        assertThat(result).contains("CA");
        assertThat(result).contains("yauheni@test.com");
        assertThat(result).contains("9164567890");
    }

    @When("I cancell the form")
    public void iCancellTheForm() throws InterruptedException {
        WebElement cancelElement = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        getActions().moveToElement(cancelElement).perform();
        cancelElement.click();
        getDriver().switchTo().alert().accept();
        Thread.sleep(2000);
    }

    @Then("I verify form reset successfully.")
    public void iVerifyFormResetSuccessfully() {
    }
}
