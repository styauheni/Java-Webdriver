package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class JavaStepsDefs {
    @Given("I say Hello World.")
    public void iSayHelloWorld() {
        System.out.println("Hello World!");

        String var1 = "Hello World!";
        var1 = "Say Hello";
        System.out.println(var1);


    }

    @And("I say {string}")
    public void iSay(String message) {
        System.out.println(message);

        String firstName = "John";
        String lastName = "Doe";
        String favoriteColor = "Green";
        System.out.println("Hi, my name is "+firstName + " " + lastName+ ", my favorit color is " + favoriteColor);
    }


    @Given("I perform actions with {string} and {string}")
    public void iOerformActionsWithAnd(String str1, String str2) {
        System.out.println(str1);
        System.out.println(str2);

        System.out.println(str1.toUpperCase());
        System.out.println(str2.toUpperCase());

        System.out.println(str1.length());
        System.out.println(str2.length());

        System.out.println(str1.equals(str2));

        System.out.println(str1.equalsIgnoreCase(str2));
    }

    @And("I type url for {string}")
    public void iTypeUrlFor(String site) {
        if (site.equalsIgnoreCase("google")) {
            System.out.println("https://www.google.com/");
        } else if (site.equalsIgnoreCase("yahoo")) {
            System.out.println("https://www.yahoo.com/");
        } else if (site.equalsIgnoreCase("quote")) {
            System.out.println("https://skryabin.com/market/quote.html");
        } else if (site.equalsIgnoreCase("website")) {
            System.out.println(site + " not a site name");
        } else {
            System.out.println("Not supported site! Actual: " + site.toUpperCase());
        }
    }


    @And("I print {string} day of week")
    public void iPrintDayOfWeek(String day) {
        switch (day) {
            case "1":
                System.out.println("Sunday");
                break;
            case "2":
                System.out.println("Monday");
                break;
            case "3":
                System.out.println("Tuesday");
                break;
            case "4":
                System.out.println("Wednesday");
                break;
            case "5":
                System.out.println("Thursday");
                break;
            case "6":
                System.out.println("Friday");
                break;
            case "7":
                System.out.println("Saturday");
                break;
            default:
                System.out.println("Invalid Input");

//        if (day.equals("1")){
//            System.out.println("Sunday");
//        }else if (day.equals("2")){
//            System.out.println("Monday");
//        }else if (day.equals("3")){
//            System.out.println("Tuesday");
//        }else if (day.equals("4")){
//            System.out.println("Wednesday");
//        }else if (day.equals("5")){
//            System.out.println("Thursday");
//        }else if (day.equals("6")){
//            System.out.println("Friday");
//        }else if (day.equals("7")){
//            System.out.println("Saturday");
//        }else {
//            System.out.println("Invalid Input");
        }
    }

    @And("I print if {string} is positive")
    public void iPrintIfIsPositive(String num1) {
        if (0 < Integer.parseInt(num1)) {
            System.out.println("Number "+num1 +" is positive");
        } else if (0 > Integer.parseInt(num1)) {
            System.out.println("Number "+num1 +" is negative");
        } else {
            System.out.println(num1 +" is 0");
        }
    }

    @Given("I work with arrays")
    public void iWorkWithArrays() {
        String[] fruits = {"apple","plum","kiwi"};
        int[] nums = {5, 2, 3, 2, 5};

        System.out.println(fruits[0]);
        System.out.println(fruits[1]);
        System.out.println(fruits[2]);

        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        for (int num : nums){
            System.out.println("num: " +num);
        }

        List<String> myFruits = Arrays.asList("apple", "plum", "kiwi", "orange");
        for (String fruit : myFruits){
            System.out.println(fruit);
        }

        List<Integer> myNums = Arrays.asList(5, 2, 3, 2, 5);
            for (int num : myNums){
                System.out.println("num: " + num);
        }
    }
}
