package io.test.automation.cucumberframework.context;

import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j2;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = DefaultContextConfiguration.class)
@Log4j2
public class ContextConfigurationInit {

    @Before(order = 0)
    public void init() {
        log.info("Init testing context..");
    }
}
