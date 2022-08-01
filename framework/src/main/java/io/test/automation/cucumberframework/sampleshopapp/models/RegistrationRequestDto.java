package io.test.automation.cucumberframework.sampleshopapp.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegistrationRequestDto {

    private String name;
    private String email;
    private String password;
}
