package io.test.automation.cucumberframework.general.component;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
import static java.util.Objects.nonNull;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ResponseInfo {

    private List<Integer> expectedStatusCodes = new ArrayList<>();
    private Integer actualStatusCode;
    private Map<String, String> headers = new HashMap<>();
    @Getter
    private String error;

    public ExtractableResponse<Response> setResponse(ExtractableResponse<Response> response) {
        this.actualStatusCode = response.statusCode();
        if (response.statusCode() >= HttpStatus.SC_BAD_REQUEST) {
            setHeaders(response.headers());
            this.error = response.response().asPrettyString();
        }
        return response;
    }

    private void setHeaders(Headers responseHeaders) {
        if (nonNull(responseHeaders)) {
            for (Header h : responseHeaders) {
                headers.put(h.getName(), h.getValue());
            }
        }
    }

    public void addExpectedStatusCodes(Integer... statusCode) {
        this.expectedStatusCodes.clear();
        this.expectedStatusCodes.addAll(Arrays.asList(statusCode));
    }

    public List<Integer> checkExpectedOrDefaultCodes(Integer... defaultCodes) {
        return expectedStatusCodes.isEmpty() ? Arrays.asList(defaultCodes) : expectedStatusCodes;
    }
}
