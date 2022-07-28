@ui
Feature: UI tests for automationpractice site

  Background:
    Given I open main page of automationpractice

  Scenario: Search flow validation
    When  I fill search field "Printed"
    Then  I click search button
    Then "Showing 1 - 5 of 5 items" message is displayed

  Scenario: Contact us flow with file upload
    When I click on a text "Contact us"
    And  "Customer service - Contact us" message is displayed
    And  I select subject heading
    And  I set email address
    And  I upload file
    And  I set message field
    And  I click Send button
    Then "Your message has been successfully sent to our team." message is displayed

  Scenario: Sign in flow with email
    When I click on a text "Sign in"
    And  I fill valid email to input field to create account
    And  I click create account button
    Then "Your personal information" message is displayed

  @issue=391730
  Scenario: Sign in flow with invalid email
    When I click on a text "Sign in"
    And  I fill invalid email to input field to create account
    And  I click create account button
    Then "Invalid email address." message is displayed
