package io.test.automation.cucumberframework.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequestDto {

    private String email;
    private String password;
}
