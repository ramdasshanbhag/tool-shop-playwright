package com.toolshop.ui.tests;

import com.toolshop.base.BaseTest;
import com.toolshop.ui.pages.LoginPage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @Test
    @DisplayName("Invalid credentials show an error message")
    void invalidLoginShowsError() {
        LoginPage login = new LoginPage(page).open()
                .login("wrong@example.com", "BadPassword1!");

        assertTrue(login.isErrorVisible(), "Expected a login error to be shown");
        assertTrue(login.errorMessage().toLowerCase().contains("invalid"),
                "Error text should mention invalid credentials, was: " + login.errorMessage());
    }

    @Test
    @DisplayName("Valid demo credentials log the customer in")
    void validLoginRedirectsToAccount() {
        // Demo credentials published by the Tool Shop practice site.
        new LoginPage(page).open()
                .login("customer@practicesoftwaretesting.com", "welcome01");

        page.waitForURL("**/account");
        assertTrue(page.url().contains("/account"));
    }
}
