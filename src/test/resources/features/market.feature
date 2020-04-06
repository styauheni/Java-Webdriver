@market
  Feature: Marketing app test suit

    @market1
    Scenario: Market basic test
      Given I go to "quote" page
      And I print page details in the console
#      And I go to "google" page
#      And I go back and forward, then refresh the page
      And I change resolution to "phone"
      And I change resolution to "desctop"
      When I verify email field behavior
      When I fill out required fields
      And I submit the form
      And I verify required fields


    @market2
    Scenario: Market optional fields
      Given I go to "quote" page
      When I fill out required fields
      And I fill out optional fields
      And I submit the form
      And I verify optional fields


    @market3
    Scenario: Printing logs
      Given I go to "yahoo" page
      And I print logs to the console

    @market4
    Scenario: Multi-select
      Given I go to "quote" page
      And I fill multi-select


    @unitconverters
    Scenario Outline: : Validation
      Given I go to "converter" page
      When I click on "<unit>"
      And I set "<convertFrom>" to "<convertTo>"
      Then I enter into Form field "<valueFrom>" and ferify "<valueTo>" result
      Examples:
      |unit        | convertFrom |convertTo    | valueFrom | valueTo |
      |Temperature | Fahrenheit  | Celsius     | 54        |12.2     |
      |Weight      |Pound        |Kilogram     |170        |77       |
      |Length      |Mile         |Kilometer    |50         |80.4     |


    @calculator
    Scenario: Verify valculator result
      Given I go to "calculator" page
      And I clear all calculator fields
      And I calculate
      Then I verify "Please provide a positive auto price." calculator error
      Then I verify "Please provide a positive interest value." calculator error
      And I enter "25000" price, "60" month, "4.5" interest, "5000" downpayment, "0" trade-in, "California" state, "7" percent tax, "300" fees
      And I calculate
      Then I verify monthly pay is "$372.86"


    @market5
    Scenario:I play with alert
      Given I go to "quote" page
      When I click "3rd perty agreement"
      And I decline agreement
      Then I verify that agreement declined
      When I click "3rd perty agreement"
      And I accept agreement
      Then I verify that agreement accepted.

    @market6
    Scenario: fill out additional information
      Given I go to "quote" page
      When  I fill out additional info
      And I verify that "Document 2" present in related documents
      And I submit using JavaScript





