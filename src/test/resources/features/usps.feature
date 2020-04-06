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
      And I define "2" quantity
      Then I calculate the price and validate cost is "$2.40"

    @usps5
    Scenario: find a location
      Given I go to "usps" page
      When I go to Find Location page
      And I select location type "Collection Boxes"
      And I select service "Priority Mail Express" within "10 Miles"
      And I enter zip code "92120"
      And I search for the location address
      Then  I should see city "San Diego" in the search result.

    @usps6
    Scenario: Phone number of the nearest Accountable Mail Pickup Service Post Office for Portnov Computer School
      Given I go to "usps" page
      When I go to Find a Location page
      And I filter by "Post Offices™" Location Types, "Pickup Services" Services, "Accountable Mail" Available Services
      And I fill in "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
      Then I print the phone number and validate is "880-275-8777"

    @usps7
    Scenario: Quadcopters delivery
      Given I go to "usps" page
      When I go to "Help" tab
      And I perform "Quadcopter delivery" help search
      Then I verify that no results of "Quadcopter delivery" available in the search

    @usps8
    Scenario: Every door direct mail
      Given I go to "usps" page
      When I  go to "Every Door Direct Mail" under "Business"
      And I search for "4970 El Camino Real, Los Altos, CA 94022"
      And I click"Show Table" on the map
      And I click "Select All" on the table
      And I close modal window
      Then I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary

    @usps9
    Scenario: Verify location
      Given I go to "usps" page
      When I perform "Free Boxes" search
      And I set "Mail & Ship" in filters
      Then I verify that "7" result found
      When I select "Priority Mail | USPS" in results
      And I click "Ship Now" button
      Then I validate that Sign In is required




