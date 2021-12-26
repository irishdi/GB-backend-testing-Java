Feature: add new delivery address
  Background:
    Given User is authorized on a website
    And is present on AccountPage
    Scenario:
      When I fill in form details
      And I submit data
      Then added Address is visible