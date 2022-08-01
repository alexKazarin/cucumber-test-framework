package io.test.automation.cucumberframework.general.requests;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.test.automation.cucumberframework.general.component.ResponseInfo;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Accessors(chain = true)
public class AbstractRequests {

    protected ResponseInfo responseInfo = new ResponseInfo();

    protected RequestSpecification abstractRequest(String uri, String path) {
        return RestAssured.given()
                .filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(ContentType.JSON)
                .baseUri(uri)
                .basePath(path)
                .request();
    }
    protected RequestSpecification abstractRequest(Integer port, String path) {
        return RestAssured.given()
                .filters(new AllureRestAssured(), new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(ContentType.JSON)
                .port(port)
                .basePath(path)
                .request();
    }

    protected final class ResponseExtractor {

        private ExtractableResponse<Response> response;
        @Autowired
        private ResponseInfo responseInfo;

        public ResponseExtractor(ExtractableResponse<Response> response,
                                 ResponseInfo responseInfo) {
            this.response = response;
            this.responseInfo = responseInfo;
        }

        /**
         * @param successCode   - a response status code which indicates that response
         *                        can be extracted into a given class.
         * @param clazz         - this is a class of an outgoing object.
         * @return              - an object of a given class.
         */
        public <T> T extract(Integer successCode, Class<T> clazz) {
            if (response.statusCode() == successCode) {
                return response.response().as(clazz);
            }
            responseInfo.setResponse(response);
            return null;
        }

        public <T> T extractByType(Integer successCode, TypeRef<T> typeRef) {
            if (response.statusCode() == successCode) {
                return response.response().as(typeRef);
            }
            responseInfo.setResponse(response);
            return null;
        }
    }

    protected ResponseExtractor getExtractor(ExtractableResponse<Response> response) {
        return new ResponseExtractor(response, responseInfo);
    }
}
