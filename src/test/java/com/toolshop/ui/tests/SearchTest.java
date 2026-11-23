package com.toolshop.ui.tests;

import com.toolshop.base.BaseTest;
import com.toolshop.ui.pages.HomePage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchTest extends BaseTest {

    @Test
    @DisplayName("Searching for a known tool returns at least one product")
    void searchReturnsResults() {
        HomePage home = new HomePage(page).open().search("hammer");

        assertTrue(home.productCount() > 0,
                "Expected at least one product for 'hammer'");
    }

    @Test
    @DisplayName("Home page lists products on load")
    void homePageShowsProducts() {
        HomePage home = new HomePage(page).open();
        home.products().first().waitFor();

        assertTrue(home.productCount() > 0, "Expected products on the home page");
    }
}
