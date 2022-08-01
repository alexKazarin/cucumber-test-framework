package io.test.automation.cucumberframework.sampleshopapp.pages;

import io.test.automation.cucumberframework.general.pages.AbstractPage;

public class MyAccountPage extends AbstractPage {

    public static final String PAGE_HEADER = "//p[text()='My account']";
    private static final String SUCCESS_ALERT = ".alert-success";
    private static final String HOME_BTN = "//a[@title='Home']";

    public MyAccountPage() {
        super();
    }
}
