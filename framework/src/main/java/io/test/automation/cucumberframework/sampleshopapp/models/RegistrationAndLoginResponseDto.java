package io.test.automation.cucumberframework.sampleshopapp.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegistrationAndLoginResponseDto {

    private Long code;
    private String message;
    private RegistrationResponseDataDto data;
}
