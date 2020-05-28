@ups
  Feature: UPS project

    @ups1
    Scenario: Validate Create Shipment functionality.
      Given I go to "ups" page
      When I open the "Create a Shipment" page
      And I fill out required all fields
      And I submit the Shipping form
      Then I verify submitted data saved successfully
      When I cancel the form
      Then I verify form reset successfully.


    @ups2
    Scenario: UPS end to end
      Given I go to "ups" page
      When I open Shipping menu
      And I go to Create a Shipment
      And I fill out origin shipments fields
      And I submit the Shipping form
      Then I verify origin shipment fields submitted
      When I fill out destination shipment fields
#      Then I verify total charges appear
#      When I submit the shipment form
      And I set packaging type
      Then I verify total charges changed
      When I submit the shipment form
      And I select chipest delivery option
      And I submit the shipment form
      And I set Saturday delivery type
      Then I verify total charges changed
      When I submit the shipment form
      And I select Paypal payment type
      And I submit the shipment form
      Then  I review all recorded details on the review page
      And I cancel the shipment form
      Then I verify shipment form is reset

