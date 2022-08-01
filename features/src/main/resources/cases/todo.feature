@api
Feature: API endpoints validations

  @apipost
  @critical
  Scenario: Call POST request create valid ToDo
#    Given I generate POST request body with id 1
    Given I generate POST request body with generated id
    Given I expect 201 status code
    Given I send POST registration request

  @apipost
  @critical
  @issue=391767
  Scenario: Unable call POST request with existing valid ToDo
    Given I generate POST request body with id 1
    Given I send POST registration request
    Given I expect 401 status code
    Given I send POST registration request
#    Given I send GET todo by Id request
#    When  I send POST registration request
#    Then  I validate POST registration request

  Scenario: Validate unable GET user by Id request without authorization  (401 status code)
    Given I expect 401 status code
    When  I send GET todo by Id request
    Then  Error message contains "please send bearer token in header"
