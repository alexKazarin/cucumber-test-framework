package io.test.automation.cucumberframework.pages;

import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class ContactUsPage extends AbstractPage {

    public static final String FILE_UPLOAD_FIELD = "fileUpload";
    public static final String SUBJECT_DROPDOWN = "id_contact";
    public static final String EMAIL_FIELD = "email";
    public static final String MESSAGE_FIELD = "message";
    public static final String SEND_BTN = "submitMessage";

    public ContactUsPage() {
        super();
    }

    public void selectCustomerServiceSubject() {
        $(By.id(SUBJECT_DROPDOWN)).selectOptionByValue("2");
    }

    public void fillEmailField(String emailAddress) {
        $(By.id(EMAIL_FIELD)).setValue(emailAddress);
    }

    public void fillMessageField(String messageField) {
        $(By.id(MESSAGE_FIELD)).setValue(messageField);
    }

    public void uploadFile(File uploadFile) {
        $(By.id(FILE_UPLOAD_FIELD)).uploadFile(uploadFile);
    }

    public void clickSendBtn() {
        $(By.id(SEND_BTN)).click();
    }
}
