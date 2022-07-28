package io.test.automation.cucumberframework.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IndexPage extends AbstractPage {

    public static final String PAGE_URL = "http://automationpractice.multiformis.com/";
    private static final String HEADER_LOGO_ID = "#header_logo";
    private static final String SEARCHBOX_FIELD = "#search_query_top";
    private static final String SEARCH_BTN = "//button[@name='submit_search']";

    public IndexPage() {
        super();
    }

    public IndexPage fillSearchboxField(String searchText) {
        $(SEARCHBOX_FIELD).sendKeys(searchText);
        return this;
    }

    public IndexPage submitSearch() {
        $x(SEARCH_BTN).click();
        return this;
    }
}
