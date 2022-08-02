@todoapi
Feature: TodoApp API endpoints validations

  @critical
  Scenario: Call POST request create valid todo
    Given I generate POST todo request body with generated id
    When  I expect 201 status code
    Then  I send POST todo app request

  Scenario: Call POST unable request with existing id
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I expect 400 status code
    Then  I send POST todo app request

  Scenario: Call POST request with existing text
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I update POST todo request body with generated id
    When  I expect 201 status code
    Given I send POST todo app request

  Scenario: Call POST unable request create missing id
    Given I generate POST todo request body with generated id
    Given I update POST todo request body id with null
    When  I expect 400 status code
    Then  I send POST todo app request
    Then  Error message contains "deserialize error: missing field `id`"

  Scenario: Call POST unable request create missing text
    Given I generate POST todo request body with generated id
    Given I update POST todo request body text with null
    When  I expect 400 status code
    Then  I send POST todo app request
    Then  Error message contains "deserialize error: missing field `text`"

  Scenario: Call POST unable request create missing completed
    Given I generate POST todo request body with generated id
    Given I update POST todo request body completed with null
    When  I expect 400 status code
    Then  I send POST todo app request
    Then  Error message contains "deserialize error: missing field `completed`"

  @critical
  Scenario: Call GET request receive todos without limit and offset
    Given I generate POST todo request body with generated id
    When  I send POST todo app request
    And   I send GET todo request
    Then  I validate GET todo request contains expected todo

  Scenario: Call GET request receive todos with limit
    Given I expect 201 status code
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I set limit 2 for GET todo request
    And   I expect 200 status code
    And   I send GET todo request
    Then  I validate GET todo request contains expected records limit

#    case should not be started in parallel as could be side effects from other cases
  Scenario: Call GET request receive todos with offset
    Given I expect 201 status code
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I set offset 1 for GET todo request
    And   I expect 200 status code
    And   I send GET todo request
    Then  I validate GET todo request contains expected records offset

  @critical
  Scenario: Call PUT update request with new text and completed
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate PUT todo request body from POST with new text and completed
    And   I set PUT request path id from generated PUT body
    Given I send PUT todo app request

#    discuss as suppose it must be an issue as we should not be able to update 'unique' id
  Scenario: Call PUT update request with new existing id in body
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate PUT todo request body from POST with new text and completed
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    And   I set PUT request path id from generated POST body
    Given I send PUT todo app request

#    discuss if we are good to update instance with id not presented in system
  Scenario: Call PUT update request with new non existing id in body
    Given I generate POST todo request body with generated id
    Given I generate PUT todo request body from POST with new text and completed
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I set PUT request path id from generated POST body
    Then  I send PUT todo app request

  @issue
#    seems to be an issue, with missing field expected response code 400 but actual 401
  Scenario: Call PUT unable update request with missing id
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    Given I generate PUT todo request body from POST with new text and completed
    And   I set PUT request path id from generated PUT body
    And   I update PUT todo request body id with null
    When  I expect 400 status code
    Then  I send PUT todo app request
    Then  Error message contains "deserialize error: missing field `id`"

  @critical
  Scenario: Call DELETE request with existing id
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I set DELETE request path id from generated POST body
    And   I generate valid basic token
    Given I send DELETE todo app request

  Scenario: Call DELETE unable request without auth
    Given I generate POST todo request body with generated id
    Given I send POST todo app request
    When  I set DELETE request path id from generated POST body
    And   I expect 401 status code
    Then  I send DELETE todo app request

  Scenario: Call DELETE unable request with not existing id
    Given I generate POST todo request body with generated id
    When  I set DELETE request path id from generated POST body
    And   I generate valid basic token
    And   I expect 404 status code
    Given I send DELETE todo app request
