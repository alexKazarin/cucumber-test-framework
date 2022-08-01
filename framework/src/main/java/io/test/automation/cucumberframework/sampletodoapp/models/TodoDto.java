package io.test.automation.cucumberframework.sampletodoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoDto {

    private Long id;
    private String text;
    private Boolean completed;
}
