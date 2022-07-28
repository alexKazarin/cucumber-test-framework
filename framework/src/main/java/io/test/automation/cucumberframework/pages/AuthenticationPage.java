package io.test.automation.cucumberframework.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthenticationPage extends AbstractPage {

    public static final String PAGE_HEADER = "//h1[text()='Authentication']";
    private static final String SUCCESS_ALERT = ".alert-success";
    private static final String CREATE_ACCOUNT_INPUT = "email_create";
    private static final String CREATE_ACCOUNT_BTN = "SubmitCreate";

    public AuthenticationPage() {
        super();
    }

    public void inputEmailCreateAccount(String emailAddress) {
        $(By.id(CREATE_ACCOUNT_INPUT)).setValue(emailAddress);
    }

    public void clickCreateAccountBtn() {
        $(By.id(CREATE_ACCOUNT_BTN)).click();
    }
}
