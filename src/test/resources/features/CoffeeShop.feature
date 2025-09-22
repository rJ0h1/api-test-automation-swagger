Feature: Tricentis Coffee Shop API tests

Background: Set the test context
  Given user is calling the "coffee-shop"

  Scenario: Update first employee in the list of employees with a new name
  Given user wants to update "employees"
  And user requests list of existing employees
  And user receives a non-empty list of employees
  When user requests to modify the name of the first employee
  Then request is successful
  And the list of existing employees is updated