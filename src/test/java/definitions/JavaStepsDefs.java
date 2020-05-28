package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.Cat;
import pages.Dog;
import pages.Parrot;
import pages.Pet;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("Hi, my name is " + firstName + " " + lastName + ", my favorit color is " + favoriteColor);
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
            System.out.println("Number " + num1 + " is positive");
        } else if (0 > Integer.parseInt(num1)) {
            System.out.println("Number " + num1 + " is negative");
        } else {
            System.out.println(num1 + " is 0");
        }
    }

    @Given("I work with arrays")
    public void iWorkWithArrays() {
        String[] fruits = {"apple", "plum", "kiwi"};
        int[] nums = {5, 2, 3, 2, 5};

        System.out.println(fruits[0]);
        System.out.println(fruits[1]);
        System.out.println(fruits[2]);

        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        for (int num : nums) {
            System.out.println("num: " + num);
        }

        List<String> myFruits = Arrays.asList("apple", "plum", "kiwi", "orange");
        for (String fruit : myFruits) {
            System.out.println(fruit);
        }

        List<Integer> myNums = Arrays.asList(5, 2, 3, 2, 5);
        for (int num : myNums) {
            System.out.println("num: " + num);
        }
    }

    @Given("I work with maps")
    public void iWorkWithMaps() {
        Map<String, String> user = Map.of(
                "username", "jdoe",
                "password", "welcome",
                "email", "john@doe.com"
        );
        Map<String, String> admin = Map.of(
                "username", "admin",
                "password", "pass1",
                "email", "admin@doe.com"
        );
        System.out.println(user);
        System.out.println(admin);
        System.out.println(user.get("username"));
        System.out.println(user.get("password"));
        System.out.println(user.get("email"));

        //Homework day 6
        Map<String, String> info = Map.of(
                "firstName", "John",
                "middleName", "George"
        );
//        Map<String, String> info = new LinkedHashMap<>();
//        Map<String, String> info1 = Map.of(
//                "temp", "firstName" = info.get("firstName")",
//                "firstName", "info.get("middleName")",
//                "middleName", "info.get("temp")"
//        )


//        ,
//        "middleName" = info.get("firstName")
//        );

    }

    @Given("I solve coding challenges")
    public void isolveCodingChallenges() {
//        swap(3, 5);
//        isDivisibleBy3and4(8);
//        isDivisibleBy3and4(9);
//        isDivisibleBy3and4(12);
//        isDivisibleBy3and4(17);
//        alfa();
//        main();
//        System.out.println(reverse3rd("WebDriver"));

    }

    String reverse3rd(String str) {
        String reversed = " ";
        int j = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (j % 3 == 0) {

                reversed += str.charAt(i);
            }
            j++;
        }
        return reversed;
    }


    void isDivisibleBy3and4(int num) {
        System.out.println("Is divisible func. Num: " + num);
        if (num % 3 == 0 && num % 4 == 0) {
            System.out.println("Div by 3 and 4");
        } else if (num % 3 == 0) {
            System.out.println("Div by 3");
        } else if (num % 4 == 0) {
            System.out.println("Div by 4");
        } else {
            System.out.println("Not div by 3 and 4");

        }
    }

    void swap(int a, int b) {
        System.out.println("Swap func");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a: " + a);
        System.out.println("b " + b);
    }

    public void alfa() {
        char symb = 'a';
        for (; ; ) {
            System.out.print(symb);
            if (symb == 'z') {
                break;
            }
            symb++;
        }

    }


    public void main() {
        String ipOld = "10.1.1.1";
        String ipNew = ipOld.replace("1", "2");
        System.out.println(ipNew);

        String java = "something.";
        String mmm = java.replaceAll("\\.", "");
    }


    boolean isDivisible() {
        int num = 15;
        if ((num % 10 == 0) || (num % 2 != 0 && num % 3 == 0)) {
            System.out.println("done!");
            return true;
        } else {
            System.out.println("try again");
            return false;
        }

    }

    // Print line up to n element
    void printUpToElement(int myArgument) {
        for (int i = 1; i <= myArgument; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print(" FizzBuzz ");
            } else if (i % 3 == 0) {
                System.out.print(" Fizz ");
            } else if (i % 5 == 0) {
                System.out.print(" Buzz ");
            } else {
                System.out.print(i + " ");
            }
        }
    }


    @Given("I run classes")
    public void iRunClasses() {
        Pet tom = new Cat("Tom");
//        tom.setName("Tom");
        System.out.println("Cat's name: " + tom.getName());
        tom.walk();
        tom.eat("fish");
        tom.speak();
        tom.setName("Jerry");
        tom.walk();
        tom.eat("fish");
        tom.speak();

        Pet puff = new Dog();
        puff.eat("meat");
        puff.walk();
        puff.speak();
        puff.setName("Puff");
        System.out.println("Now it is " + puff.getName());
        puff.eat("meat");
        puff.walk();
        puff.speak();

        Pet flint = new Parrot();
        flint.eat("nuts");
        flint.walk();
        flint.speak();
        flint.setName("Flint");
        System.out.println("I named it " + flint.getName());
        flint.eat("nuts and seeds");
        flint.walk();
        flint.speak();
    }
}

