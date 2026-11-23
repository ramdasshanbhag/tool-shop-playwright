package com.toolshop.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    private final Locator searchBox;
    private final Locator searchButton;

    public HomePage(Page page) {
        super(page);
        this.searchBox = page.locator("[data-test='search-query']");
        this.searchButton = page.locator("[data-test='search-submit']");
    }

    public void search(String searchText) {
        searchBox.fill(searchText);
        searchButton.click();
    }
}