Feature: New Patient Registration

Background:
Given I launched "chrome" browser with url "Akni"
And I navigated to the Akni Home page
Then I verify the Home page
When I click the new patient button
And I navigate to the Basic Details tab

@Smoke @Regression
Scenario: Verify new patient using external excel data
Given I am on the New Patient Basic Details page
When I fill new patient details from excel "src/test/testData/RegistrationData.xlsx" sheet "patients"