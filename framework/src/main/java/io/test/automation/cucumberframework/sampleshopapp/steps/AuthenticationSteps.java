package io.test.automation.cucumberframework.sampleshopapp.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.test.automation.cucumberframework.sampleshopapp.pages.AuthenticationPage;

public class AuthenticationSteps {

    @Given("I fill valid email to input field to create account")
    public void fillValidEmail() {
        String validEmail = Faker.instance().internet().emailAddress();
        new AuthenticationPage().inputEmailCreateAccount(validEmail);
    }

    @Given("I fill invalid email to input field to create account")
    public void fillInvalidEmail() {
        new AuthenticationPage().inputEmailCreateAccount("invalid@mail");
    }

    @Given("I click create account button")
    public void clickCreateAccountButton() {
        new AuthenticationPage().clickCreateAccountBtn();
    }
}
