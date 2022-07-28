package io.test.automation.cucumberframework.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import io.cucumber.java.en.Then;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneralUiSteps {

    protected static final Duration ELEMENT_TIMEOUT = Duration.ofMillis(10000);

    private boolean isMessageOnPageDisplayed(String message) {
        try {
            $$(withText(message)).first().shouldBe(Condition.visible, ELEMENT_TIMEOUT);
        }
        catch (ElementNotFound e) {
            return false;
        }
        return true;
    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String message) {
        assertThat(isMessageOnPageDisplayed(message)).as(message).isTrue();
    }

    @Then("I click on a text {string}")
    public void clickOnText(String message) {
        $(withText(message)).shouldBe(Condition.visible).click();
    }
}
