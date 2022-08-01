package io.test.automation.cucumberframework.general.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"io.test.automation"})
public class DefaultContextConfiguration {
}
