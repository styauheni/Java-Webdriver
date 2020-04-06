@ups
  Feature: UPS project

    @ups1
    Scenario: Validate Create Shipment functionality.
      Given I go to "ups" page
      When I open "Create a Shipment" page
      And I fill out required all fields
      And I submit the Shipping form
      Then I verify submitted data saved successfully
      When I cancell the form
      Then I verify form reset successfully.
