package io.test.automation.cucumberframework.general.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.util.Objects.nonNull;

public class AbstractPage {

    protected static final Integer PAGE_LOADED_TIMEOUT = 90;

    public AbstractPage() {
        waitUntilPageLoaded();
    }

    public static void waitUntilPageLoaded() {
        waitUntilPageLoaded(WebDriverRunner.getWebDriver());
    }

    private static void waitUntilPageLoaded(WebDriver wd) {
        new WebDriverWait(wd, Duration.ofSeconds(PAGE_LOADED_TIMEOUT)).until(AbstractPage::isPageLoaded);
    }

    private static boolean isPageLoaded(WebDriver wd) {
        JavascriptExecutor executor = (JavascriptExecutor) wd;
        return nonNull(wd) && "complete".equals(executor.executeScript("return document.readyState;"));
    }
}
