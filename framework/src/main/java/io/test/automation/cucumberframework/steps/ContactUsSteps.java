package io.test.automation.cucumberframework.steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.test.automation.cucumberframework.pages.ContactUsPage;

import java.io.File;

import static io.test.automation.cucumberframework.utils.ResourceFileLoader.getResourceAsFile;
import static org.assertj.core.api.Assertions.assertThat;


public class ContactUsSteps {

    private static final String UPLOADED_FILE_FOLDER = "upload";
    private static final String UPLOADED_FILE_NAME = "file.jpg";

    @Then("I select subject heading")
    public void selectSubjectHeading() {
        new ContactUsPage().selectCustomerServiceSubject();
    }

    @Then("I set email address")
    public void setEmailAddress() {
        new ContactUsPage().fillEmailField(Faker.instance().internet().emailAddress());
    }

    @Then("I set message field")
    public void setMessageField() {
        new ContactUsPage().fillMessageField(Faker.instance().ancient().god());
    }

    @Then("I click Send button")
    public void clickSendButton() {
        new ContactUsPage().clickSendBtn();
    }

    @Then("I upload file")
    public void uploadFileOnSendMessageWindow() {
        File tmpFile = getResourceAsFile(UPLOADED_FILE_FOLDER, UPLOADED_FILE_NAME);
        assertThat(tmpFile).as("File should not be null").isNotNull();
        new ContactUsPage().uploadFile(tmpFile);
        tmpFile.deleteOnExit();
    }
}
