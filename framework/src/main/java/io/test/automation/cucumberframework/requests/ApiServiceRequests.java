package io.test.automation.cucumberframework.requests;

import io.test.automation.cucumberframework.models.LoginRequestDto;
import io.test.automation.cucumberframework.models.RegistrationAndLoginResponseDto;
import io.test.automation.cucumberframework.models.RegistrationRequestDto;
import io.test.automation.cucumberframework.models.UserDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.is;

// https://www.appsloveworld.com/sample-rest-api-url-for-testing-with-authentication/#hgetuser
public class ApiServiceRequests extends AbstractRequests {

    private static final String SERVICE_URI = "http://restapi.adequateshop.com";
    private static final String POST_REGISTRATION_URL = "/api/authaccount/registration";
    private static final String POST_LOGIN_URL = "/api/authaccount/login";
    private static final String GET_USER_BY_ID_URL = "/api/users/{user_id}";

    private static final String USER_ID = "user_id";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "bearer ";

    public RegistrationAndLoginResponseDto postRegistrationRequest(RegistrationRequestDto registrationBody) {
        ExtractableResponse<Response> response = abstractRequest(SERVICE_URI,
                POST_REGISTRATION_URL)
                .body(registrationBody)
                .when().post()
                .then().statusCode(is(responseInfo.checkExpectedCodeOrDefault(HttpStatus.SC_OK)))
                .extract();
        return getExtractor(response).extract(HttpStatus.SC_OK, RegistrationAndLoginResponseDto.class);
    }

    public RegistrationAndLoginResponseDto postLoginRequest(LoginRequestDto loginBody) {
        ExtractableResponse<Response> response = abstractRequest(SERVICE_URI,
                POST_LOGIN_URL)
                .body(loginBody)
                .when().post()
                .then().statusCode(is(responseInfo.checkExpectedCodeOrDefault(HttpStatus.SC_OK)))
                .extract();
        return getExtractor(response).extract(HttpStatus.SC_OK, RegistrationAndLoginResponseDto.class);
    }

    public UserDto getUserByIdRequest(String token, Long userId) {
        ExtractableResponse<Response> response = abstractRequest(SERVICE_URI,
                GET_USER_BY_ID_URL)
                .header(AUTHORIZATION, BEARER_PREFIX + token)
                .pathParam(USER_ID, userId)
                .when().get()
                .then().statusCode(is(responseInfo.checkExpectedCodeOrDefault(HttpStatus.SC_OK)))
                .extract();
        return getExtractor(response).extract(HttpStatus.SC_OK, UserDto.class);
    }
}
