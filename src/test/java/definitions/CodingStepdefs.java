package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.TestContext;

import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class CodingStepdefs {
    @Given("I replace characters in word {string}")
    public void iReplaseCharactersInWord(String word) {
        System.out.println("it was " + word);
        String newWord = word.replace("h", "m");
        System.out.println("now it is " + newWord);
        String andWord = newWord.replaceAll("[a,e,i,o,u]", " - vowel - ");
        System.out.println("and now " + andWord);
    }

    @Given("I swap values in array")
    public void iSwapValuesInArray() {
        //        Swap elements 3rd and 5th
        int[] numrs = {5, 2, 9, 7, 3};
        System.out.println("I have an array:");
        for (int numr : numrs) {
            System.out.print(numr + " ");
        }
        System.out.println(" ");
        System.out.println("I swap the values: ");
        int temp = numrs[2];
        numrs[2] = numrs[4];
        numrs[4] = temp;
        for (int numr : numrs) {
            System.out.print(numr + " ");
        }
    }

    @Given("I play with map")
    public void iPlayWithMap() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("middleName", "George");
        info.put("firstName", "John");


        System.out.println(info);

        String firstNameNew = info.get("middleName");
        String middleNameNew = info.get("firstName");

        info.put("firstName", firstNameNew);
        info.put("middleName", middleNameNew);

        System.out.println(info);

    }

    @And("I found if {string} divisible by {string} or {string}")
    public void iFoundIfDivisibleByOr(String numS, String divS1, String divS2) {
        int num = Integer.parseInt(numS);
        int div1 = Integer.parseInt(divS1);
        int div2 = Integer.parseInt(divS2);

        if (num % div1 == 0 && num % div2 == 0) {
            System.out.println(num + " is divisible by " + div1 + " and " + div2);
        } else if (num % div1 == 0) {
            System.out.println(num + " is divisible by " + div1);
        } else if (num % div2 == 0) {
            System.out.println(num + " is divisible by " + div2);
        } else {
            System.out.println(num + " is not divisible by " + div1 + " or " + div2);
        }
    }

    @Given("I open Swisscows")
    public void iOpenSwisscows() {
        getDriver().get("https://swisscows.ch/");
    }

    @Then("the search field should be present")
    public void theSearchFieldShouldBePresent() {
        assertThat(getDriver().findElement(By.xpath("//input[@name='query']")).isDisplayed());
    }

    @When("search for {string}")
    public void searchFor(String search) {
        getDriver().findElement(By.xpath("//input[@name='query']")).sendKeys(search);
        getDriver().findElement(By.xpath("//button[@class='search-submit']")).click();
    }


    @Then("result should contain text {string}")
    public void resultShouldContainText(String result) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        WebElement resultElement = getDriver().findElement(By.xpath("//div[@class='web-results']"));
        wait.until(driver -> !resultElement.getText().isEmpty());
        String searchResult = resultElement.getText();
        assertThat(searchResult).contains(result);
    }


//        info.put("firstName", "John");
//        info.put("middleName", "George");
//        use: info.get("key"); info.put("key", "value")


    @Given("I do practics")


    public void iDoPractics() {
//        printNum(5);
//        int[] arr = {1,3,5,3,2};
        Object[] arr = {1,3,5,3,2};
//        System.out.println(isArrayEmpty(arr));
//        System.out.println(isArrayNotEmpty(arr));
        System.out.println(isArrayContains(arr, 2));


//        int[] arr = {2, -4, 5, 6, 7, 1};
//        for (int i = 0; i <= arr.length - 1; i++) {
//            if (arr[i] % 2 != 0)
//                System.out.print(arr[i]);
//        }

    }
//    void printNum(int n){
//        for (int i = 0; i <= n; i++) {
//           System.out.print(i);
//        }
//    }

//   boolean isArrayEmpty (int[] arr){
//            if (arr == null || arr.length == 0) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//     boolean isArrayNotEmpty(int[] arr){
//        if (arr == null || arr.length == 0){
//            return false;
//        }else {
//            return true;
//        }
//     }
//    boolean isArrayContains(int[] arr, int a) {
//        for (int element : arr) {
//            if (element == a) {
//                return true;
//            }
//        }
//        return false;
//    }
    Boolean isArrayContains(Object[] arr, Object a){
        for (int i=0; i<arr.length; i++){
            if (arr[i].equals(a)){
                return true;
            }
        }
        return false;
    }

    }
