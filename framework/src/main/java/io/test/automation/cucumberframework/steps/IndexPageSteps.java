package io.test.automation.cucumberframework.steps;

import io.test.automation.cucumberframework.pages.IndexPage;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;
import static io.test.automation.cucumberframework.pages.AbstractPage.waitUntilPageLoaded;


public class IndexPageSteps {

    @Given("I open main page of automationpractice")
    public void openMainPage() {
        open(IndexPage.PAGE_URL);
        waitUntilPageLoaded();
    }

    @Given("I fill search field {string}")
    public void fillSearchField(String searchText) {
        new IndexPage().fillSearchboxField(searchText);
    }

    @Given("I click search button")
    public void clickSearchButton() {
        new IndexPage().submitSearch();
    }
}
