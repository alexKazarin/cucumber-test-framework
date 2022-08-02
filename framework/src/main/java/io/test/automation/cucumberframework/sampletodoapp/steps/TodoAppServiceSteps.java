package io.test.automation.cucumberframework.sampletodoapp.steps;

import io.test.automation.cucumberframework.sampletodoapp.requests.TodoAppServiceRequests;
import io.test.automation.cucumberframework.general.component.RequestsFactory;
import io.test.automation.cucumberframework.sampletodoapp.models.TodoDto;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class TodoAppServiceSteps {

    private static final String TODO_POST_TEXT = "text";
    private static final String TODO_PUT_TEXT = "update";
    private static final String TODO_USER = "admin";
    private static final String TODO_PWD = "admin";

    private TodoDto todoPostRequestBody;
    private TodoDto todoPutRequestBody;
    private Long todoPutRequestPathId;
    private Long todoDeleteRequestPathId;

    @Autowired
    private RequestsFactory request;

    private String basicToken;
    private Integer limit = null;
    private Integer offset = null;
    private List<TodoDto> responseTodo;

    @Given("I generate valid basic token")
    public void generateValidBasicToken() {
        basicToken = Base64.getEncoder().encodeToString((TODO_USER + ":" + TODO_PWD).getBytes());
    }

    @Given("I set limit {int} for GET todo request")
    public void setLimitForGetTodoRequest(Integer limit) {
        this.limit = limit;
    }

    @Given("I set offset {int} for GET todo request")
    public void setOffsetForGetTodoRequest(Integer offset) {
        this.offset = offset;
    }

    @Given("I send GET todo request")
    public void sendGetTodoRequest() {
        responseTodo = request.get(TodoAppServiceRequests.class).getTodoRequest(limit, offset);
    }

    @Given("I validate GET todo request contains expected todo")
    public void validateGetTodoRequestContainsExpectedTodo() {
        assertThat(responseTodo).as("Expected not null").isNotNull();
        TodoDto foundTodo = responseTodo.stream()
                .filter(todo -> todo.equals(todoPostRequestBody))
                .findFirst()
                .orElse(null);
        assertThat(foundTodo).as("Expected $s not found", todoPostRequestBody).isNotNull();
    }

    @Given("I validate GET todo request contains expected records limit")
    public void validateGetTodoRequestContainsExpectedRecordsLimit() {
        assertThat(responseTodo).as("Expected not null").isNotNull();
        assertThat(responseTodo.size()).as("Number of records not as expected").isEqualTo(limit);
    }

    @Given("I validate GET todo request contains expected records offset")
    public void validateGetTodoRequestContainsExpectedRecordsOffset() {
        Integer searchOffset = offset;
        assertThat(responseTodo).as("Expected response with offset not null").isNotNull();
        TodoDto foundTodo = responseTodo.get(0);
        offset = null;
        sendGetTodoRequest();
        assertThat(responseTodo).as("Expected not null").isNotNull();
        assertThat(responseTodo.get(searchOffset))
                .as("Found todo not as expected").isEqualTo(foundTodo);
    }

    @Given("I generate POST todo request body with generated id")
    public void generatePostTodoRequestBodyWithGeneratedId() {
        Long currentTimeId = System.currentTimeMillis();
        todoPostRequestBody = new TodoDto()
                .setId(currentTimeId)
                .setText(TODO_POST_TEXT + currentTimeId)
                .setCompleted(false);
    }

    @Given("I generate POST todo request body with id {long}")
    public void generatePostRequestBody(Long id) {
        todoPostRequestBody = new TodoDto()
                .setId(id)
                .setText("text" + id.toString())
                .setCompleted(false);
    }

    @Given("I update POST todo request body with generated id")
    public void updatePostTodoRequestBodyWithGeneratedId() {
        todoPostRequestBody.setId(System.currentTimeMillis());
    }

    @Given("I generate PUT todo request body from POST with new text and completed")
    public void generatePutTodoRequestBodyFromPostWithNewTextAndCompleted() {
        todoPutRequestBody = todoPostRequestBody;
        todoPutRequestBody
                .setText(todoPostRequestBody.getText().replace(TODO_POST_TEXT, TODO_PUT_TEXT))
                .setCompleted(!todoPostRequestBody.getCompleted());
    }

    @Given("I update POST todo request body id with null")
    public void updatePostTodoRequestBodyIdWithNull() {
        todoPostRequestBody.setId(null);
    }

    @Given("I update POST todo request body text with null")
    public void updatePostTodoRequestBodyTextWithNull() {
        todoPostRequestBody.setText(null);
    }

    @Given("I update POST todo request body completed with null")
    public void updatePostTodoRequestBodyCompletedWithNull() {
        todoPostRequestBody.setCompleted(null);
    }

    @Given("I send POST todo app request")
    public void sendPostTodoAppRequest() {
        request.get(TodoAppServiceRequests.class).postTodoRequest(todoPostRequestBody);
    }

    @Given("I set PUT request path id from generated PUT body")
    public void setPutTodoAppRequestPathIdFromGeneratedPutBody() {
        assertThat(todoPutRequestBody).as("Please define PUT request body first").isNotNull();
        todoPutRequestPathId = todoPutRequestBody.getId();
    }

    @Given("I set PUT request path id from generated POST body")
    public void setPutTodoAppRequestPathIdFromGeneratedPostBody() {
        assertThat(todoPostRequestBody).as("Please define POST request body first").isNotNull();
        todoPutRequestPathId = todoPostRequestBody.getId();
    }

    @Given("I send PUT todo app request")
    public void sendPutTodoAppRequest() {
        request.get(TodoAppServiceRequests.class).putTodoRequest(todoPutRequestBody, todoPutRequestPathId);
    }

    @Given("I update PUT todo request body id with null")
    public void updatePutTodoRequestBodyIdWithNull() {
        todoPutRequestBody.setId(null);
    }

    @Given("I update PUT todo request body text with null")
    public void updatePutTodoRequestBodyTextWithNull() {
        todoPutRequestBody.setText(null);
    }

    @Given("I update PUT todo request body completed with null")
    public void updatePutTodoRequestBodyCompletedWithNull() {
        todoPutRequestBody.setCompleted(null);
    }

    @Given("I set DELETE request path id from generated POST body")
    public void setDeleteTodoAppRequestPathIdFromGeneratedPostBody() {
        assertThat(todoPostRequestBody).as("Please define POST request body first").isNotNull();
        todoDeleteRequestPathId = todoPostRequestBody.getId();
    }

    @Given("I send DELETE todo app request")
    public void sendDeleteTodoAppRequest() {
        request.get(TodoAppServiceRequests.class).deleteTodoRequest(basicToken, todoDeleteRequestPathId);
    }
}
