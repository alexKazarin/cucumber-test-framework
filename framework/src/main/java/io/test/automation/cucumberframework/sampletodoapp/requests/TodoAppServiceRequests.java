package io.test.automation.cucumberframework.sampletodoapp.requests;

import io.restassured.common.mapper.TypeRef;
import io.test.automation.cucumberframework.general.requests.AbstractRequests;
import io.test.automation.cucumberframework.sampletodoapp.models.TodoDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class TodoAppServiceRequests extends AbstractRequests {

    private static final Integer SERVICE_PORT = 8088;
    private static final String GET_TODO_URL = "/todos";
    private static final String POST_TODO_URL = "/todos";
    private static final String PUT_TODO_URL = "/todos/{id}";
    private static final String DELETE_TODO_URL = "/todos/{id}";

    private static final String TODO_ID = "id";
    private static final String QUERY_LIMIT = "limit";
    private static final String QUERY_OFFSET = "offset";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC_PREFIX = "basic ";

    public List<TodoDto> getTodoRequest(Integer limit, Integer offset) {
        Map<String, Integer> queryParams = new HashMap<>();
        if (limit != null) {
            queryParams.put(QUERY_LIMIT, limit);
        }
        if (offset != null) {
            queryParams.put(QUERY_OFFSET, offset);
        }
        ExtractableResponse<Response> response = abstractRequest(SERVICE_PORT, GET_TODO_URL)
                .queryParams(queryParams)
                .when().get()
                .then().statusCode(is(responseInfo.checkExpectedOrDefaultCodes(HttpStatus.SC_OK)))
                .extract();
        return getExtractor(response).extractByType(HttpStatus.SC_OK, new TypeRef<List<TodoDto>>() { });
    }

    public void postTodoRequest(TodoDto requestBody) {
        abstractRequest(SERVICE_PORT, POST_TODO_URL)
                .body(requestBody)
                .when().post()
                .then().statusCode(is(in(responseInfo
                        .checkExpectedOrDefaultCodes(HttpStatus.SC_CREATED, HttpStatus.SC_BAD_REQUEST))))
                .extract();
    }

    public void putTodoRequest(TodoDto requestBody, Long id) {
        abstractRequest(SERVICE_PORT, PUT_TODO_URL)
                .pathParam(TODO_ID, id)
                .body(requestBody)
                .when().put()
                .then().statusCode(is(responseInfo.checkExpectedOrDefaultCodes(HttpStatus.SC_OK)))
                .extract();
    }

    public void deleteTodoRequest(String token, Long id) {
        abstractRequest(SERVICE_PORT, DELETE_TODO_URL)
                .header(AUTHORIZATION, BASIC_PREFIX + token)
                .pathParam(TODO_ID, id)
                .when().get()
                .then().statusCode(is(responseInfo.checkExpectedOrDefaultCodes(HttpStatus.SC_NO_CONTENT)))
                .extract();
    }
}
