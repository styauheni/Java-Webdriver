package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.bytebuddy.implementation.bytecode.Throw;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import support.TestContext;

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

    }

    @And("I go back and forward, then refresh the page")
    public void iGoBackAndForwardThenRefreshThePage() {
        getDriver().navigate().back();
        getDriver().navigate().forward();
        getDriver().navigate().refresh();
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

    @And("I ferify required fields")
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
}
