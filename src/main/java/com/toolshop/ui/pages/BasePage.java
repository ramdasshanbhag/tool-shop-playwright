package com.toolshop.ui.pages;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    protected void click(String selector) {
        locator(selector).click();
    }

    protected void fill(String selector, String text) {
        locator(selector).fill(text);
    }

    protected String getText(String selector) {
        return locator(selector).innerText();
    }

    protected boolean isVisible(String selector) {
        return locator(selector).isVisible();
    }

    public String getTitle() {
        return page.title();
    }
}
