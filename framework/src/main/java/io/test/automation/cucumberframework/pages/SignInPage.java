package io.test.automation.cucumberframework.pages;

public class SignInPage extends AbstractPage {

    public static final String PAGE_HEADER = "//p[text()='My account']";
    private static final String SUCCESS_ALERT = ".alert-success";
    private static final String HOME_BTN = "//a[@title='Home']";

    public SignInPage() {
        super();
    }
}
