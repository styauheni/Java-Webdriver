package definitions;

import cucumber.api.java.en.Given;

public class CodingStepdefs {
    @Given("I replace characters in word {string}")
    public void iReplaseCharactersInWord(String word) {
        System.out.println("it was " + word);
        String newWord = word.replace("h","m");
        System.out.println("now it is " + newWord);
        String andWord = newWord.replaceAll("[a,e,i,o,u]", " - vowel - ");
        System.out.println("and now " + andWord);
    }

}
