package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.sql.SQLOutput;

public class JavaStepsDefs {
    @Given("I say Hello World.")
    public void iSayHelloWorld() {
        System.out.println("Hello World!");

        String var1="Hello World!";
        var1="Say Hello";
        System.out.println(var1);


    }

    @And("I say {string}")
    public void iSay(String message) {
        System.out.println(message);
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
}
