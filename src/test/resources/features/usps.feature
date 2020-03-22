@usps
  Feature: USPS test suite

    @usps1
    Scenario: Validate ZIP code for Portnov Computer School
      Given I go to "usps" page
      When I go to Lookup ZIP page by address
      And I fill out "4440 Twain Avenue" street, "San Diego" city, "CA" state
      Then I validate "92120" zip code exists in the result

    @usps4
    Scenario: Calculate price
      Given I go to "usps" page
      When I go to Calculate Price Page
      And I select "United Kingdom (United Kingdom of Great Britain and Northern Ireland)" with "Postcard" shape
      And I define "325" quantity
      Then I calculate the price and validate cost is "$390.00"

