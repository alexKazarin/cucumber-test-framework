package io.test.automation.cucumberframework.general.component;

import io.test.automation.cucumberframework.general.requests.AbstractRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class RequestsFactory {

    @Autowired
    private ResponseInfo responseInfo;

    public <T extends AbstractRequests> T get(Class<T> clazz) {
        try {
            return (T) clazz.getDeclaredConstructor().newInstance().setResponseInfo(responseInfo);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
    }
}
