@api
Feature: API endpoints validations

  @apipost
  Scenario: Validate POST registration request
    Given I generate POST registration request body
    When  I send POST registration request
    Then  I validate POST registration request

  @apipost
  Scenario: Validate unable POST registration request (400 status code)
    Given I generate POST registration request body
    And   I set empty email POST registration request body
    And   I expect 400 status code
    When  I send POST registration request
    Then  Error message contains "The request is invalid"

  @apiget
  Scenario: Validate GET user by Id request with token
    Given I generate POST registration request body
    And   I send POST registration request
    And   I prepare POST login request body
    And   I send POST login request
    When  I send GET user by Id request
    Then  I validate GET user by Id response

  @apiget
  @issue=391767
  Scenario: Validate unable GET user by Id request without authorization  (401 status code)
    Given I expect 401 status code
    When  I send GET user by Id request
    Then  Error message contains "please send bearer token in header"
