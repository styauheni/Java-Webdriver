package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.awt.event.WindowFocusListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CodingStepdefs {
    @Given("I replace characters in word {string}")
    public void iReplaseCharactersInWord(String word) {
        System.out.println("it was " + word);
        String newWord = word.replace("h","m");
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
            System.out.print(numr +" ");
        }
        System.out.println(" ");
        System.out.println("I swap the values: ");
        int temp = numrs[2];
        numrs[2] = numrs [4];
        numrs [4] = temp;
        for (int numr : numrs) {
            System.out.print(numr + " ");
        }
    }

    @Given("I play with map")
    public void iPlayWithMap() {
        Map<String,String> info = new LinkedHashMap<>();
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

        if (num % div1 == 0 && num % div2 ==0) {
            System.out.println(num + " is divisible by " + div1 + " and " + div2);
        }else if (num % div1 == 0) {
            System.out.println(num + " is divisible by " + div1);
        }else if (num % div2 == 0){
            System.out.println(num + " is divisible by " + div2);
        } else {
            System.out.println(num + " is not divisible by " + div1 + " or " + div2);
        }
    }
//        info.put("firstName", "John");
//        info.put("middleName", "George");
//        use: info.get("key"); info.put("key", "value")
}
