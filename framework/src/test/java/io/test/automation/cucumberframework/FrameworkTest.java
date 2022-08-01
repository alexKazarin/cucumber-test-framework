package io.test.automation.cucumberframework;

import io.test.automation.cucumberframework.general.component.RequestsFactory;
import io.test.automation.cucumberframework.general.requests.AbstractRequests;
import org.junit.jupiter.api.Test;

public class FrameworkTest {

    @Test
    public void someUtinTest() {
        RequestsFactory testFactory = new RequestsFactory();
        testFactory.get(AbstractRequests.class).getResponseInfo();
    }
}
