package io.test.automation.cucumberframework.sampleshopapp.steps;

import com.github.javafaker.Faker;
import io.test.automation.cucumberframework.sampleshopapp.models.LoginRequestDto;
import io.test.automation.cucumberframework.sampleshopapp.models.RegistrationAndLoginResponseDto;
import io.test.automation.cucumberframework.sampleshopapp.requests.ApiServiceRequests;
import io.test.automation.cucumberframework.general.component.RequestsFactory;
import io.test.automation.cucumberframework.general.component.ResponseInfo;
import io.test.automation.cucumberframework.sampleshopapp.models.RegistrationRequestDto;
import io.test.automation.cucumberframework.sampleshopapp.models.UserDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ApiServiceSteps {

    private RegistrationRequestDto registrationRequestBody;
    private LoginRequestDto loginRequestBody;
    private RegistrationAndLoginResponseDto registrationResponse;
    private RegistrationAndLoginResponseDto loginResponse;
    private UserDto getUserByIdResponse;

    @Autowired
    private ResponseInfo responseInfo;

    @Autowired
    private RequestsFactory request;

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

    @Given("I generate POST registration request body")
    public void generatePostRegistrationRequestBody() {
        registrationRequestBody = new RegistrationRequestDto()
                .setName(Faker.instance().name().firstName())
                .setEmail(Faker.instance().internet().emailAddress())
                .setPassword("default");
    }

    @Given("I set empty email POST registration request body")
    public void setEmptyEmailPostRegistrationRequestBody() {
        assertThat(registrationRequestBody)
                .as("Please generate POST registration request body first").isNotNull();
        registrationRequestBody.setEmail(null);
    }

    @Given("I send POST registration request")
    public void sendPostRegistrationRequest() {
        registrationResponse = request.get(ApiServiceRequests.class).postRegistrationRequest(registrationRequestBody);
    }

    @Given("I validate POST registration request")
    public void validatePostRegistrationRequest() {
        assertThat(registrationResponse).as("Registration Response should not be empty").isNotNull();
        assertThat(registrationResponse.getData().getEmail())
                .as("Registration Response email not as expected")
                .isEqualTo(registrationRequestBody.getEmail());
    }

    @Given("I prepare POST login request body")
    public void preparePostLoginRequestBody() {
        assertThat(registrationResponse).as("Please register first").isNotNull();
        loginRequestBody = new LoginRequestDto()
                .setEmail(registrationResponse.getData().getEmail())
                .setPassword(registrationRequestBody.getPassword());
    }

    @Given("I send POST login request")
    public void sendPostLoginRequest() {
        loginResponse = request.get(ApiServiceRequests.class).postLoginRequest(loginRequestBody);
    }

    @Given("I send GET user by Id request")
    public void sendGetUserByIdRequest() {
        String token = loginResponse != null ? loginResponse.getData().getToken() : "";
        Long userId = loginResponse != null ? loginResponse.getData().getId() : 0L;
        getUserByIdResponse = request.get(ApiServiceRequests.class).getUserByIdRequest(token, userId);
    }

    @Given("I validate GET user by Id response")
    public void validateGetUserByIdResponse() {
        assertThat(getUserByIdResponse).as("Expected not null").isNotNull();
    }
}
