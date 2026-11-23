package com.toolshop.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

/**
 * Shared Playwright lifecycle for every UI test.
 * One browser per test class, a fresh context + page per test.
 * Run headed with:  mvn test -Dheaded=true
 */
public abstract class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    protected static void launchBrowser() {
        playwright = Playwright.create();

        // Tool Shop uses data-test (not data-testid), so getByTestId() must point at it.
        playwright.selectors().setTestIdAttribute("data-test");

        boolean headed = Boolean.parseBoolean(System.getProperty("headed", "false"));
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(!headed));
    }

    @BeforeEach
    protected void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    protected void closeContext() {
        if (context != null) context.close();
    }

    @AfterAll
    protected static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
