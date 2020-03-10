@market
  Feature: Marketing app test suit

    @market1
    Scenario: Market basic test
      Given I go to "quote" page
      And I print page details in the console
#      And I go to "google" page
#      And I go back and forward, then refresh the page
      When I fill out required fields
      And I submit the form
      And I ferify required fields
      And I wait for 3 sec