package io.test.automation.cucumberframework.general.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.test.automation.cucumberframework.general.component.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralApiSteps {

    @Autowired
    private ResponseInfo responseInfo;

    @And("I expect {int} status code")
    public void expectStatusCode(Integer statusCode) {
        responseInfo.addExpectedStatusCodes(statusCode);
    }

    @Given("Error message contains {string}")
    public void errorMessageContains(String expectedErrorMsg) {
        String actualError = responseInfo.getError();
        boolean msgPresented = actualError.contains(expectedErrorMsg);
        assertThat(msgPresented)
                .as("Expected message '%s' occur in response '%s'", expectedErrorMsg, actualError).isTrue();
    }
}
