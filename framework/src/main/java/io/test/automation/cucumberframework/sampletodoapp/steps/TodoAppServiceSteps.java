package io.test.automation.cucumberframework.sampletodoapp.steps;

import io.test.automation.cucumberframework.sampletodoapp.requests.TodoAppServiceRequests;
import io.test.automation.cucumberframework.general.component.RequestsFactory;
import io.test.automation.cucumberframework.general.component.ResponseInfo;
import io.test.automation.cucumberframework.sampletodoapp.models.TodoDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class TodoAppServiceSteps {

    private TodoDto todoRequestBody;
    private TodoDto getUserByIdResponse;

    @Autowired
    private ResponseInfo responseInfo;

    @Autowired
    private RequestsFactory request;

    private String basicToken;
    private Integer limit;
    private Integer offset;
    private List<TodoDto> responseTodo;

    @Given("I generate basic token for user {string} and password {string}")
    public void generateBasicTokenByUserAndPassword(String user, String pwd) {
        basicToken = Base64.getEncoder().encodeToString((user + ":" + pwd).getBytes());
    }

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

    @And("I set limit {int} and offset {int}")
    public void setLimitAndOffset(Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    @Given("I send GET todo by Id request")
    public void sendGetTodoByIdRequest() {
        responseTodo = request.get(TodoAppServiceRequests.class).getTodoRequest(limit, offset);
    }

    @Given("I validate GET todo by Id response")
    public void validateGetTodoByIdResponse() {
        assertThat(responseTodo).as("Expected not null").isNotNull();
    }


    @Given("I generate POST request body with generated id")
    public void generatePostRequestBodyWithGeneratedId() {
        Long currentTimeId = System.currentTimeMillis();
        todoRequestBody = new TodoDto()
                .setId(currentTimeId)
                .setText("text" + currentTimeId)
                .setCompleted(false);
    }

    @Given("I generate POST request body with id {long}")
    public void generatePostRequestBody(Long id) {
        todoRequestBody = new TodoDto()
                .setId(id)
                .setText("text" + id.toString())
                .setCompleted(false);
    }

    @Given("I send POST registration request")
    public void sendPostRegistrationRequest() {
        request.get(TodoAppServiceRequests.class).postTodoRequest(todoRequestBody);
    }
}
