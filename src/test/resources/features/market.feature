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
      And I wait for 3 sec

    @market2
    Scenario: Market optional fields
      Given I go to "quote" page
      When I fill out required fields
      And I fill out optional fields
      And I submit the form
      And I verify optional fields




