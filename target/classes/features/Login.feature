@Login @Smoke @Regression
Feature: Login Functionality

  Scenario: Verify login using Excel data
    Given user is on the Login page
    When user logs in using data from excel "Login.xlsx" sheet "Sheet1"
    Then user clicks on MyCompany link and verifies navigation
